package org.example.battleunits;

import org.example.battleunits.subsidiary.CombatUnitType;
import org.example.battleunits.weapons.Weapon;

import java.util.Deque;
import java.util.List;

public interface CombatUnit {

    int getMaxHealth();

    default boolean isAlive() {
        return getHealth() > 0;
    }

    int getHealth();

    void heal(int healingPoints);

    void hit(CombatUnit opponent);

    int receiveDamage(int damage);

    int reduceHealth(int damageReceived);

    int getAttack();

    CombatUnit equipWeapon(Weapon weapon);

    Deque<Weapon> getWeaponsEquipped();

    CombatUnitType getCombatUnitType();
}
