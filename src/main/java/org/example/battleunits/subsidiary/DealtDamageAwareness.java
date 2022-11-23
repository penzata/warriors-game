package org.example.battleunits.subsidiary;

import org.example.battleunits.units.WarriorUnit;

public interface DealtDamageAwareness extends WarriorUnit {

    default int getDealtDamage(WarriorUnit opponent) {
        int healthBeforeHit = opponent.getHealth();
        WarriorUnit.super.hit(opponent);
        int healthAfterHit = opponent.getHealth();
        return Math.min(healthBeforeHit, healthBeforeHit - healthAfterHit);
    }

}
