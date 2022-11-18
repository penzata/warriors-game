package org.example.battleunits;

import org.jetbrains.annotations.NotNull;

public class Warrior implements BattleUnit {
    private int health;
    private int attack;

    private int initHealth;

    /**
     * Constructs default Warrior object with default health(50) & attack(5).
     */
    public Warrior() {

        this(50, 5);
        initHealth = this.health;
    }

    Warrior(int health, int attack) {
        initHealth = this.health = health;
        this.attack = attack;
    }

    /**
     * @param warrior - copy constructor
     */
    Warrior(@NotNull Warrior warrior) {
        this.health = warrior.health;
        this.attack = warrior.attack;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {

        Math.min(this.health = health, initHealth);
    }

    @Override
    public void reduceHealthBasedOnDamage(int damage) {

        setHealth(Math.max(getHealth() - damage, 0));
    }
}