package org.example.battleunits.characteristics;

import org.example.battleunits.Army;

public interface Heal {

    void heal(Army.ArmyWarriorUnitDecorator warriorUnit);

    int getHealthPointsFromMedKit();
}
