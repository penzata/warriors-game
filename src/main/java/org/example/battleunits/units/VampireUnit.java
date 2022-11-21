package org.example.battleunits.units;

import org.example.battleunits.characteristics.Vampirism;

public interface VampireUnit extends WarriorUnit, Vampirism {
    @Override
    default void hit(WarriorUnit opponent) {
        int healthBeforeHit = opponent.getHealth();
        WarriorUnit.super.hit(opponent);
        int healthAfterHit = opponent.getHealth();
        int damageDealt = Math.min(healthBeforeHit, healthBeforeHit - healthAfterHit);
        final int PERCENTS = 100;
        int healingPoints = damageDealt * getVampirism() / PERCENTS;
        healthRestored(healingPoints);
    }
}
