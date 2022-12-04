package org.example.battleunits.subsidiary;

import org.example.battleunits.CombatUnit;

public interface DealtDamageAwareness extends CombatUnit {

    default int getDealtDamage(CanReceiveDamage opponent) {
        int healthBeforeHit = opponent.getHealth();
        CombatUnit.super.hit(opponent);
        int healthAfterHit = opponent.getHealth();
        return healthBeforeHit - healthAfterHit;
    }
}
