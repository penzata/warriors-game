package org.example.battleunits;

import lombok.extern.slf4j.Slf4j;
import org.example.battleunits.characteristics.Vampirism;
import org.example.battleunits.subsidiary.CombatUnitType;
import org.example.battleunits.weapons.Weapon;

@Slf4j
public class Vampire extends CombatUnitImpl implements Vampirism {
    private static final int DEFAULT_HEALTH = 40;
    private static final int DEFAULT_ATTACK = 4;
    private static final int DEFAULT_VAMPIRISM = 50;
    /**
     * vampirism - vampire's self-healing (restores health by 50% of the dealt damage).
     */
    private int vampirism;

    Vampire() {
        super(DEFAULT_HEALTH, DEFAULT_ATTACK);
        this.vampirism = DEFAULT_VAMPIRISM;
    }

    Vampire (int health, int attack, int vampirism) {
        super(health, attack);
        this.vampirism = vampirism;
    }

    @Override
    public int getVampirism() {
        return Math.max(vampirism + getVampirismStatFromWeapon(), 0);
    }

    private int getVampirismStatFromWeapon() {
        return getWeaponsEquipped().stream()
                .mapToInt(Weapon::getVampirismStat)
                .sum();
    }

    @Override
    public String toString() {
        return super.toString() + "{v:" + getVampirism() + "%}";
    }

    private void setVampirism(int vampirism) {
        this.vampirism = vampirism;
    }

    @Override
    public void hit(CombatUnit opponent) {
        int damageDealt = opponent.receiveDamage(this.getAttack());
        final int PERCENTS = 100;
        int healingPoints = (damageDealt * getVampirism() / PERCENTS);
        vampirism(healingPoints);
    }

    @Override
    public CombatUnitType getCombatUnitType() {
        return CombatUnitType.FIGHTER;
    }

    /**
     * @param selfHealing - vampire's self-healing (restores health by 50% of the dealt damage).
     */
    @Override
    public void vampirism(int selfHealing) {
        heal(selfHealing);
    }
}