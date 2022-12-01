package org.example.battleunits;

import org.example.weapons.Weapon;

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
                "{hp:" + getHealth() + "}";
    }

    private int getHealthBonusFromWeapon() {
        return weapons.stream()
                .mapToInt(Weapon::getHealthStat).sum();
    }

    @Override
    public int getHealth() {
        return health + getHealthBonusFromWeapon();
    }

    private void setHealth(int health) {

        this.health = Math.min(health, initialHealth);
    }

    @Override
    public void reduceHealth(int damage) {
        setHealth(health - damage);
    }

    private int getInitialHealth() {

        return initialHealth + getHealthBonusFromWeapon();
    }

    @Override
    public Warrior equipWeapon(Weapon weapon) {
        weapons.add(weapon);
        return this;
    }


    @Override
    public int getAttack() {

        return attack + weapons.stream()
                .mapToInt(Weapon::getAttackStat).sum();
    }

    private void setAttack(int attack) {
        this.attack = attack;
    }

    void healedBy(int healingPoints) {
        setHealth(Math.min(health + healingPoints, initialHealth));
    }

}