package org.example.iterators;

import lombok.extern.slf4j.Slf4j;
import org.example.battleunits.Warrior;
import org.example.battleunits.WarriorInArmyDecorator;
import org.example.exceptions.DoesntExistException;

@Slf4j
public class StraightIterate implements InfGenerator<Warrior> {
    private WarriorInArmyDecorator nextUnitInLine;

    public StraightIterate(WarriorInArmyDecorator warriorInFront) {
        this.nextUnitInLine = warriorInFront;
    }


    @Override
    public Warrior next() {
        if (!hasNext()) {
            try {
                throw new DoesntExistException("no more army units left");
            } catch (DoesntExistException e) {
                log.atError().log("insufficient army units funds.");
            }
        }
        WarriorInArmyDecorator res = nextUnitInLine;
        nextUnitInLine = nextUnitInLine.getWarriorBehind();
        return res.unwrap();
    }

    @Override
    public boolean hasNext() {
        while (nextUnitInLine != null && !nextUnitInLine.isAlive()) {
            nextUnitInLine = nextUnitInLine.getWarriorBehind();
        }
        return nextUnitInLine != null;
    }
}
