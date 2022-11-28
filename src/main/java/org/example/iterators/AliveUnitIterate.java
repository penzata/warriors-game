package org.example.iterators;

import org.example.battleunits.CombatUnit;
import org.example.exceptions.DoesntExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Iterator;

public class AliveUnitIterate implements InfGenerator<CombatUnit> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AliveUnitIterate.class);
    private Collection<CombatUnit> army;
    private Iterator<CombatUnit> it;
    private CombatUnit hitter;

    public AliveUnitIterate(Collection<CombatUnit> army) {
        this.army = army;
        this.it = army.iterator();
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
        return hitter;
    }

    @Override
    public boolean hasNext() {
        if (hitter != null && hitter.isAlive()) {
            return true;
        }
        while (it.hasNext()) {
            hitter = it.next();
            if (hitter.isAlive()) {
                return true;
            }
        }
        return false;
    }
}
