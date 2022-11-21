package org.example.battleunits.units;

import org.example.battleunits.Vampire;
import org.example.battleunits.characteristics.Vampirism;

public interface VampireUnit extends WarriorUnit, Vampirism {
    @Override
    default int hit(WarriorUnit opponent) {
       int damageDealt =  WarriorUnit.super.hit(opponent);
        final int PERCENTS = 100;
        int healingPoints = damageDealt * getVampirism() / PERCENTS;
        healthRestored(healingPoints);
        return damageDealt;
    }

    static VampireUnit newVampire() {
        return new Vampire();
    }
}
