package org.example.iterators;

import lombok.extern.slf4j.Slf4j;
import org.example.battleunits.CombatUnit;
import org.example.battleunits.CombatUnitInArmyDecorator;
import org.example.exceptions.DoesntExistException;

@Slf4j
public class AliveUnitIterate implements InfGenerator<CombatUnit> {

    private CombatUnitInArmyDecorator nextAlive;

    public AliveUnitIterate(CombatUnitInArmyDecorator combatUnitInFront) {
        this.nextAlive = combatUnitInFront;
    }


    @Override
    public CombatUnit next() {
        try {
            if (!hasNext()) {
                throw new DoesntExistException("no more army units left");
            }
        } catch (DoesntExistException e) {
            log.atError().log("{}", e.getMessage());
            e.printStackTrace();
        }
        return nextAlive;
    }

    @Override
    public boolean hasNext() {
        while (nextAlive != null) {
            if (nextAlive.isAlive()) {
                return true;
            }
            nextAlive = nextAlive.getCombatUnitBehind();
        }
        return false;
    }
}
