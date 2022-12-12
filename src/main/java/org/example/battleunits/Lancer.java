package org.example.battleunits;

import org.example.battleunits.characteristics.PiercingAttack;
import org.example.battleunits.subsidiary.CombatUnitBehind;
import org.example.battleunits.subsidiary.CombatUnitType;
import org.example.battleunits.weapons.Weapon;

public class Lancer extends CombatUnitImpl implements PiercingAttack {
    private static final int DEFAULT_HEALTH = 50;
    private static final int DEFAULT_ATTACK = 6;
    private static final int DEFAULT_PIERCING_DAMAGE = 50;
    /**
     * piercing damage to unit behind (second unit) - 50% of the dealt damage to the first enemy unit.
     */
    private int piercingDamage;

    Lancer() {
        super(DEFAULT_HEALTH, DEFAULT_ATTACK);
        this.piercingDamage = DEFAULT_PIERCING_DAMAGE;
    }

    Lancer (int health, int attack, int piercingDamage) {
        super(health, attack);
        this.piercingDamage = piercingDamage;
    }

    @Override
    public int getPiercingAttack() {
        return Math.max(piercingDamage + getPiercingAttackStatFromWeapon(), 0);
    }

    private int getPiercingAttackStatFromWeapon() {
        return getWeaponsEquipped().stream()
                .mapToInt(Weapon::getPiercingAttackStat)
                .sum();
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

    @Override
    public void hit(CombatUnit opponent) {
        int damageDealt = opponent.receiveDamage(this.getAttack());
        if (opponent instanceof CombatUnitBehind opponentBehind) {
            CombatUnit nextOpponent = opponentBehind.getCombatUnitBehind();
            if (nextOpponent != null) {
                final int PERCENTS = 100;
                int reducedDamage = (damageDealt * getPiercingAttack() / PERCENTS);
                nextOpponent.receiveDamage(reducedDamage);
            }
        }
    }
}