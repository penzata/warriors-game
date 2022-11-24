package org.example.battleunits.characteristics;

import org.example.battleunits.units.WarriorUnit;

public interface Heal {

    default void heal(WarriorUnit warriorUnit, int healingPoints) {
        warriorUnit.healedByMedKit(healingPoints);
    }

    int getHealthPointsFromMedKit();

    int getMedKitsPerHealer();
}
