package org.example.battleunits;

import org.example.battleunits.characteristics.Defence;
import org.example.battleunits.subsidiary.CombatUnitType;
import org.example.battleunits.weapons.Weapon;

public class Defender extends CombatUnitImpl implements Defence {
    private static final int DEFAULT_HEALTH = 60;
    private static final int DEFAULT_ATTACK = 3;
    private static final int DEFAULT_DEFENCE = 2;
    private int defence;

    Defender() {
        super(DEFAULT_HEALTH, DEFAULT_ATTACK);
        this.defence = DEFAULT_DEFENCE;
    }

    Defender (int health, int attack, int defence) {
        super(health, attack);
        this.defence = defence;
    }

    @Override
    public int getDefence() {

        return Math.max(defence + getDefenceStatFromWeapon(), 0);
    }

    private int getDefenceStatFromWeapon() {
        return getWeapons().stream()
                .mapToInt(Weapon::getDefenceStat)
                .sum();
    }
    
    @Override
    public String toString() {
        return super.toString() + "{d:" + getDefence() + "}";
    }

    private void setDefence(int defence) {
        this.defence = defence;
    }

    @Override
    public int receiveDamage(int damage) {

        return reduceHealth(Math.max(0, damage - getDefence()));
    }

    @Override
    public CombatUnitType getCombatUnitType() {

        return CombatUnitType.FIGHTER;
    }

}
