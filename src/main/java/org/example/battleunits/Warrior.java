package org.example.battleunits;

import org.jetbrains.annotations.NotNull;

public class Warrior implements BattleUnit {
    private int health;
    private int attack;
    private int initialHealth;

    /**
     * Constructs default Warrior object with default health(50) & attack(5).
     */
    public Warrior() {

        this(50, 5);
        this.initialHealth = this.health;
    }

    Warrior(int health, int attack) {
        this.initialHealth = this.health = health;
        this.attack = attack;
    }

    /**
     * @param warrior - copy constructor
     */
    Warrior(@NotNull Warrior warrior) {
        this.health = warrior.health;
        this.attack = warrior.attack;
        this.initialHealth = warrior.initialHealth;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    @Override
    public int getAttack() {
        return attack;
    }

    void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public void reduceHealth(int damage) {

        setHealth(Math.max(getHealth() - damage, 0));
    }

    @Override
    public int getHealth() {
        return health;
    }

    private void setHealth(int health) {

        this.health = Math.min(initialHealth, health);
    }

    @Override
    public void healthRestored(int healingPoints) {

        setHealth(Math.min(getHealth() + healingPoints, initialHealth));
    }
}