package org.example.battleunits;

import org.example.battleunits.subsidiary.CombatUnitType;

public class Warrior extends CombatUnitImpl {
    private static final int DEFAULT_HEALTH = 50;
    private static final int DEFAULT_ATTACK = 5;

    Warrior() {
        super(DEFAULT_HEALTH, DEFAULT_ATTACK);
    }

    Warrior (int health, int attack) {
        super(health, attack);
    }

    @Override
    public CombatUnitType getCombatUnitType() {

        return CombatUnitType.FIGHTER;
    }

}
