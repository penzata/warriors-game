package org.example.battleunits.subsidiary;

import org.example.battleunits.Warrior;

public interface DealtDamageAwareness extends Warrior {

    default int getDealtDamage(CanReceiveDamage opponent) {
        int healthBeforeHit = opponent.getHealth();
        Warrior.super.hit(opponent);
        int healthAfterHit = opponent.getHealth();
        return healthBeforeHit - healthAfterHit;
    }
}
