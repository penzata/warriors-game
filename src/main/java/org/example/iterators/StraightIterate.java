package org.example.iterators;

import lombok.extern.slf4j.Slf4j;
import org.example.battleunits.Army;
import org.example.battleunits.CombatUnit;
import org.example.battleunits.CombatUnitInArmyDecorator;
import org.example.exceptions.DoesntExistException;

@Slf4j
public class StraightIterate implements InfGenerator<CombatUnit> {
    private CombatUnitInArmyDecorator nextUnitInLine;

    public StraightIterate(CombatUnitInArmyDecorator combatUnitInFront) {
        this.nextUnitInLine = combatUnitInFront;
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
        CombatUnitInArmyDecorator res = nextUnitInLine;
        nextUnitInLine = nextUnitInLine.getCombatUnitBehind();
        return res.unwrap();
    }

    @Override
    public boolean hasNext() {
        while (nextUnitInLine != null && !nextUnitInLine.isAlive()) {

            nextUnitInLine = nextUnitInLine.getCombatUnitBehind();
        }
        return nextUnitInLine != null;
    }
}
