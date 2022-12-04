package org.example.battleunits;

import org.example.battleunits.characteristics.Attack;
import org.example.battleunits.characteristics.Health;
import org.example.battleunits.subsidiary.CanBeHealed;
import org.example.battleunits.subsidiary.CanReceiveDamage;
import org.example.battleunits.subsidiary.CombatUnitType;
import org.example.battleunits.weapons.EquippedWeaponBonusStats;
import org.example.battleunits.weapons.Weapon;

public interface Warrior extends Attack, Health, CanReceiveDamage, CanBeHealed, EquippedWeaponBonusStats, CombatUnit {

    /**
     * @return Warrior object with default health(50) & attack(5).
     */
    static Warrior create() {
        return new WarriorImpl();
    }

    default void hit(CanReceiveDamage opponent) {

        opponent.receiveDamage(this);
    }

    default Warrior equipWeapon(Weapon weapon) {
        return this;
    }

    CombatUnitType getCombatUnitType();
}
