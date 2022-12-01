package org.example.battleunits;

import org.example.characteristics.Attack;
import org.example.characteristics.Health;
import org.example.weapons.EquippedWeaponBonusStats;
import org.example.weapons.Weapon;

public interface Warrior extends Attack, Health, EquippedWeaponBonusStats {

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

        reduceHealth(damageDealer.getAttack());
    }

    void reduceHealth(int damage);

    default Warrior equipWeapon(Weapon weapon) {
        return this;
    }
}
