package org.example.battleunits;

import org.example.weapons.Weapon;

public class WarriorImpl implements Warrior {
    private static int idSequence = 0;
    /**
     * used to keep track on created objects at debugging
     */
    private final int id = ++idSequence;
    private final int initialHealth;
    private int health;
    private int attack;

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

    @Override
    public int getHealth() {
        return health;
    }

    private int getInitialHealth() {
        return initialHealth;
    }

    private void setHealth(int health) {

        this.health = health;
    }

    @Override
    public void reduceHealth(int damage) {
        setHealth(getHealth() - damage);
    }

    @Override
    public int getAttack() {
        return attack;
    }

    private void setAttack(int attack) {
        this.attack = attack;
    }

    void healedBy(int healingPoints) {
        setHealth(Math.min(getHealth() + healingPoints, getInitialHealth()));
    }

}