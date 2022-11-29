package org.example.battleunits;

import org.example.characteristics.Attack;
import org.example.characteristics.Health;
import org.example.weapons.Weapon;

public interface Warrior extends Attack, Health {

    /**
     * @return Warrior object with default health(50) & attack(5).
     */
    static Warrior create() {
        return new WarriorImpl();
    }

    default void hit(Warrior opponent) {

        opponent.receiveDamage(this);
    }

    default void receiveDamage(Attack damageDealer) {
    }

    Warrior equipWeapon(Weapon weapon);
}
