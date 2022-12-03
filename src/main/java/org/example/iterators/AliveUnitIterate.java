package org.example.iterators;

import lombok.extern.slf4j.Slf4j;
import org.example.battleunits.Warrior;
import org.example.battleunits.WarriorInArmyDecorator;
import org.example.exceptions.DoesntExistException;

@Slf4j
public class AliveUnitIterate implements InfGenerator<Warrior> {

    private WarriorInArmyDecorator nextAlive;

    public AliveUnitIterate(WarriorInArmyDecorator warriorInFront) {
        this.nextAlive = warriorInFront;
    }


    @Override
    public Warrior next() {
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
            nextAlive = nextAlive.getWarriorBehind();
        }
        return false;
    }
}
