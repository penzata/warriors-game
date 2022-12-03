package org.example.battleunits;

import org.example.battleunits.subsidiary.CanReceiveDamage;
import org.example.battleunits.subsidiary.CombatUnitType;
import org.example.battleunits.weapons.EquippedWeaponBonusStats;
import org.example.battleunits.weapons.Weapon;

public interface Warrior extends CanReceiveDamage, EquippedWeaponBonusStats {

    /**
     * @return Warrior object with default health(50) & attack(5).
     */
    static Warrior create() {
        return new WarriorImpl();
    }

    default void hit(Warrior opponent) {

        opponent.receiveDamage(this);
    }

    default Warrior equipWeapon(Weapon weapon) {
        return this;
    }

    CombatUnitType getCombatType();
}
