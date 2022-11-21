package org.example.battleunits.units;

import org.example.battleunits.Warrior;
import org.example.battleunits.characteristics.Attack;
import org.example.battleunits.characteristics.Health;

public interface WarriorUnit extends Attack, Health {
    default int hit(WarriorUnit opponent) {
        int healthBeforeHit = opponent.getHealth();
        opponent.receiveDamage(this);
        int healthAfterHit = opponent.getHealth();
        return Math.min(healthBeforeHit, healthBeforeHit - healthAfterHit);
    }

    default void receiveDamage(Attack damageDealer) {

        reduceHealth(damageDealer.getAttack());
    }

    static WarriorUnit newWarrior() {
        return new Warrior();
    }

}
