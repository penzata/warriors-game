package org.example.battleunits.units;

import org.example.battleunits.Warrior;
import org.example.battleunits.characteristics.Attack;
import org.example.battleunits.characteristics.Health;

public interface WarriorUnit extends Attack, Health {

    /**
     * @return default Warrior object with default health(50) & attack(5).
     */
    static Warrior newWarrior() {
        return new Warrior();
    }

    default void hit(WarriorUnit opponent) {

        opponent.receiveDamage(this);
    }

    default void receiveDamage(Attack damageDealer) {

        reduceHealth(damageDealer.getAttack());
    }

}
