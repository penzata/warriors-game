package org.example.iterators;

import org.example.battleunits.CombatUnitInArmyDecorator;
import org.example.battleunits.CombatUnit;
import org.example.exceptions.DoesntExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Iterator;

public class StraightIterate implements InfGenerator<CombatUnit> {
    private static final Logger LOGGER = LoggerFactory.getLogger(StraightIterate.class);
    private Collection<CombatUnit> army;
    private Iterator<CombatUnit> itr;
    private CombatUnit nextUnitInLine;

    public StraightIterate(Collection<CombatUnit> army) {
        this.army = army;
        this.itr = army.iterator();
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
