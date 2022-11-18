package org.example.battleunits;

import org.jetbrains.annotations.NotNull;

public class Warrior {
    private int health;
    private int attack;

    /**
     * Constructs default Warrior object with default health(50) & attack(5).
     */
    public Warrior() {
        this(50, 5);
    }

    Warrior(int health, int attack) {
        this.health = health;
        this.attack = attack;
    }

    /**
     * @param warrior - copy constructor
     */
    Warrior(@NotNull Warrior warrior) {
        this.health = warrior.health;
        this.attack = warrior.attack;
    }

    public int getHealth() {
        return health;
    }

    protected void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    protected void setAttack(int attack) {
        this.attack = attack;
    }

    public void hits(Warrior opponent) {
        if (opponent.isAlive()) {
            opponent.takeDamage(getAttack());
        }
    }

    public boolean isAlive() {
        return getHealth() > 0;
    }

    protected void takeDamage(int damage) {

        setHealth(Math.max((getHealth() - damage), 0));
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}