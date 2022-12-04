package org.example.battleunits;

import org.example.battleunits.subsidiary.CombatUnitType;

public class LancerImpl extends WarriorImpl implements Lancer {
    /**
     * piercing damage to unit behind (second unit) - 50% of the dealt damage to the first enemy unit.
     */
    private int piercingDamage;


    LancerImpl() {
        super(50, 6);
        this.piercingDamage = 50;
    }

    LancerImpl(int health, int attack, int piercingDamage) {
        super(health, attack);
        this.piercingDamage = piercingDamage;
    }

    /**
     * @param lancer - copy constructor
     */
    LancerImpl(LancerImpl lancer) {
        super(lancer);
        this.piercingDamage = lancer.piercingDamage;
    }

    @Override
    public int getPiercingAttack() {
        return Math.max(piercingDamage + piercingAttackBonusFromWeapon(), 0);
    }

    @Override
    public String toString() {
        return super.toString() + "{pierce:" + getPiercingAttack() + "%}";
    }

    @Override
    public CombatUnitType getCombatUnitType() {
        return CombatUnitType.LANCER;
    }

    private void setPiercingAttack(int piercingDamage) {
        this.piercingDamage = piercingDamage;
    }
}