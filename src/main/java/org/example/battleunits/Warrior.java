package org.example.battleunits;

import org.example.battleunits.units.WarriorUnit;
import org.jetbrains.annotations.NotNull;

public class Warrior implements WarriorUnit {
    private final int initialHealth;
    private int health;
    private int attack;

    /**
     * Constructs default Warrior object with default health(50) & attack(5).
     */
    public Warrior() {

        this(50, 5);
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
        return getClass().getSimpleName() + " h:" + getHealth();
    }

    @Override
    public int getHealth() {
        return health;
    }

    private void setHealth(int health) {

        this.health = Math.min(initialHealth, health);
    }

    protected void healedBy(int healthPointsFromMedKit) {
        setHealth(Math.min(getHealth() + healthPointsFromMedKit, initialHealth));
    }

    @Override
    public int getAttack() {
        return attack;
    }

    private void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public void reduceHealth(int damage) {

        setHealth(Math.max(getHealth() - damage, 0));
    }

}