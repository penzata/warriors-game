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
        initialHealth = this.health;
    }

    Warrior(int health, int attack) {
        initialHealth = this.health = health;
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

        this.health = Math.min(initialHealth, health);
    }

    @Override
    public void reduceHealthBasedOnDamage(int damage) {

        setHealth(Math.max(getHealth() - damage, 0));
    }

    @Override
    public void healthRestored(int healingPoints) {

        setHealth(Math.min(getHealth() + healingPoints, initialHealth));
    }
}