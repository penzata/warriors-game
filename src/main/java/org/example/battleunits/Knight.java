package org.example.battleunits;


import org.example.battleunits.subsidiary.CombatUnitType;

public class Knight extends CombatUnitImpl {
    private static final int DEFAULT_HEALTH = 50;
    private static final int DEFAULT_ATTACK = 7;

    Knight() {
        super(DEFAULT_HEALTH, DEFAULT_ATTACK);
    }

    Knight (int health, int attack) {
        super(health, attack);
    }

    @Override
    public CombatUnitType getCombatUnitType() {

        return CombatUnitType.FIGHTER;
    }

}