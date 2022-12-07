package org.example.battleunits;

import org.example.battleunits.weapons.Weapon;

import java.util.ArrayList;
import java.util.List;

public abstract class CombatUnitImpl implements CombatUnit {

    private static int idSequence = 0;
    /**
     * used to keep track on created objects at debugging
     */
    private final int id = ++idSequence;
    private final int maxHealth;
    private int health;
    private int attack;
    private List<Weapon> weapons = new ArrayList<>();

    CombatUnitImpl(int health, int attack) {
        this.maxHealth = health;
        this.health = health;
        this.attack = attack;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName().replace("Impl", "") +
                " #%02d".formatted(id) +
                "{hp:" + getHealth() +
                ", att:" + getAttack() + "}";
    }

    @Override
    public int getHealth() {
        return health + getHealthStatFromWeapon();
    }

    private void setHealth(int health) {

        this.health = Math.min(health, maxHealth);
    }

    @Override
    public int getAttack() {

        return Math.max(attack + getAttackStatFromWeapon(), 0);
    }

    private int getHealthStatFromWeapon() {
        return weapons.stream()
                .mapToInt(Weapon::getHealthStat).sum();
    }

    private int getAttackStatFromWeapon() {
        return weapons.stream()
                .mapToInt(Weapon::getAttackStat).sum();
    }

    private void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public int getMaxHealth() {
        return maxHealth + getHealthStatFromWeapon();
    }

    @Override
    public void heal(int healingPoints) {
        setHealth(Math.min(health + healingPoints, maxHealth));
    }

    @Override
    public void hit(CombatUnit opponent) {
        opponent.receiveDamage(this.getAttack());
    }

    @Override
    public int receiveDamage(int damage) {
        return reduceHealth(damage);
    }

    @Override
    public int reduceHealth(int damageReceived) {
        setHealth(health - damageReceived);
        return damageReceived;
    }

    @Override
    public CombatUnit equipWeapon(Weapon weapon) {
        if (isAlive()) {
            weapons.add(weapon);
        }
        return this;
    }

    @Override
    public List<Weapon> getWeapons() {
        return weapons;
    }

}