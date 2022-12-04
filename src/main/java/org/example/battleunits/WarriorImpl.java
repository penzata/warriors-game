package org.example.battleunits;

import org.example.battleunits.subsidiary.CombatUnitType;
import org.example.battleunits.weapons.Weapon;

import java.util.ArrayList;
import java.util.Collection;

public class WarriorImpl implements Warrior {

    private static int idSequence = 0;
    /**
     * used to keep track on created objects at debugging
     */
    private final int id = ++idSequence;
    private final int initialHealth;
    private int health;
    private int attack;
    private Collection<Weapon> weapons = new ArrayList<>();

    WarriorImpl() {
        this(50, 5);
    }

    WarriorImpl(int health, int attack) {
        this.initialHealth = this.health = health;
        this.attack = attack;
    }

    /**
     * @param warrior - copy constructor
     */
    WarriorImpl(WarriorImpl warrior) {
        this.health = warrior.health;
        this.attack = warrior.attack;
        this.initialHealth = warrior.initialHealth;
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
        return health + healthBonusFromWeapon();
    }

    @Override
    public void healedBy(int healingPoints) {
        setHealth(Math.min(health + healingPoints, initialHealth));
    }

    private void setHealth(int health) {

        this.health = Math.min(health, initialHealth);
    }

    @Override
    public int getAttack() {

        return Math.max(attack + attackBonusFromWeapon(), 0);
    }

    @Override
    public int healthBonusFromWeapon() {
        return weapons.stream()
                .mapToInt(Weapon::getHealthStat).sum();
    }

    private void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public int attackBonusFromWeapon() {
        return weapons.stream()
                .mapToInt(Weapon::getAttackStat).sum();
    }

    @Override
    public int defenceBonusFromWeapon() {
        return weapons.stream()
                .mapToInt(Weapon::getDefenceStat).sum();
    }

    @Override
    public int vampirismBonusFromWeapon() {
        return weapons.stream()
                .mapToInt(Weapon::getVampirismStat).sum();
    }

    @Override
    public int healPowerBonusFromWeapon() {
        return weapons.stream()
                .mapToInt(Weapon::getHealPowerStat).sum();
    }

    @Override
    public int piercingAttackBonusFromWeapon() {
        return weapons.stream()
                .mapToInt(Weapon::getPiercingAttackStat).sum();
    }

    @Override
    public void reduceHealth(int damage) {
        setHealth(health - damage);
    }

    @Override
    public int getInitialHealth() {
        return initialHealth + healthBonusFromWeapon();
    }

    @Override
    public Warrior equipWeapon(Weapon weapon) {
        if (isAlive()) {
            weapons.add(weapon);
        }
        return this;
    }

    @Override
    public CombatUnitType getCombatUnitType() {

        return CombatUnitType.FIGHTER;
    }

}