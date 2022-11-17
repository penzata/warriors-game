package org.example.battleunits;

import org.jetbrains.annotations.NotNull;

public class Defender extends Warrior {
    private int defence;

    /**
     * Constructs default Defender object with default health(60), attack(3) & defence(2).
     */
    public Defender() {
        super(60, 3);
        this.defence = 2;
    }

    protected Defender(int health, int attack, int defence) {
        super(health, attack);
        this.defence = defence;
    }

    /**
     * @param defender - copy constructor
     */
    Defender(@NotNull Defender defender) {
        super(defender);
        this.defence = getDefence();
    }

    public int getDefence() {
        return defence;
    }

    protected void setDefence(int defence) {
        this.defence = defence;
    }

    @Override
    protected void takeDamage(int damage) {
        if (damage > defence) {
            setHealth(getHealth() - (damage - defence));
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "h=" + getHealth() +
                ", a=" + getAttack() +
                ", d=" + defence +
                '}';
    }
}
