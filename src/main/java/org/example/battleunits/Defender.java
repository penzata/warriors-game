package org.example.battleunits;

import org.jetbrains.annotations.NotNull;

public class Defender extends Warrior implements HasDefence {
    private int defence;

    /**
     * Constructs default Defender object with default health(60), attack(3) & defence(2).
     */
    public Defender() {
        super(60, 3);
        this.defence = 2;
    }

    Defender(int health, int attack, int defence) {
        super(health, attack);
        this.defence = defence;
    }

    /**
     * @param defender - copy constructor
     */
    Defender(@NotNull Defender defender) {
        super(defender);
        this.defence = defender.defence;
    }

    @Override
    public void receiveDamage(CanAttack damageDealer) {
        super.receiveDamage(() -> Math.max(0, damageDealer.getAttack() - getDefence()));
    }

    @Override
    public int getDefence() {
        return defence;
    }
}
