package org.example.battleunits;

import org.example.battleunits.characteristics.Attack;
import org.example.battleunits.units.DefenderUnit;
import org.example.battleunits.units.WarriorUnit;
import org.jetbrains.annotations.NotNull;

public class Defender extends Warrior implements DefenderUnit {
    private final int defence;

    /**
     * Constructs default Defender object with default health(60), attack(3) & defence(2).
     */
    public Defender() {
        super(60, 3);
        this.defence = 2;
    }

    @Override
    public void receiveDamage(Attack damageDealer) {
        super.receiveDamage(() -> Math.max(0, damageDealer.getAttack() - getDefence()));
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
    public int getDefence() {
        return this.defence;
    }
}
