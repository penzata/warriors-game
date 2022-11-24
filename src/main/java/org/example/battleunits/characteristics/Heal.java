package org.example.battleunits.characteristics;

import org.example.battleunits.units.WarriorUnit;

public interface Heal {

    void heal(WarriorUnit warriorUnit);

    int getHealthPointsFromMedKit();

    int getMedKitsPerHealer();
}
