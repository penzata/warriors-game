package org.example.iterators;

import org.example.battleunits.CombatUnit;
import org.example.battleunits.CombatUnitInArmyDecorator;
import org.example.exceptions.DoesntExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class StraightIterate implements InfGenerator<CombatUnit> {
    private static final Logger LOGGER = LoggerFactory.getLogger(StraightIterate.class);
    private Map<Integer, CombatUnit> army;
    private Iterator<CombatUnit> itr;
    private CombatUnit nextUnitInLine;

    public StraightIterate(Map<Integer, CombatUnit> army) {
        this.army = army;
        this.itr = army.values().iterator();
    }

    @Override
    public CombatUnit next() {
        if (!hasNext()) {
            try {
                throw new DoesntExistException("no more army units left");
            } catch (DoesntExistException e) {
                LOGGER.error("insufficient army units funds.");
            }
        }
        nextUnitInLine = itr.next();
        return ((CombatUnitInArmyDecorator) nextUnitInLine).unwrap();
    }

    @Override
    public boolean hasNext() {
        return itr.hasNext();
    }
}
