package org.example.battleunits.units;

import org.example.battleunits.Warrior;
import org.example.battleunits.characteristics.Attack;
import org.example.battleunits.characteristics.Health;

public interface WarriorUnit extends Attack, Health {
    /**
     * @param opponent - to be hit.
     * @return the damage dealt to the opponent.
     */
    default int hit(WarriorUnit opponent) {
        int healthBeforeHit = opponent.getHealth();
        opponent.receiveDamage(this);
        int healthAfterHit = opponent.getHealth();

        return Math.min(healthBeforeHit, healthBeforeHit - healthAfterHit);
    }

    default void receiveDamage(Attack damageDealer) {

        reduceHealth(damageDealer.getAttack());
    }

    /**
     * @return default Warrior object with default health(50) & attack(5).
     */
    static Warrior newWarrior() {
        return new Warrior();
    }

}
