package org.example.battleunits;

import org.jetbrains.annotations.NotNull;

public class Knight extends Warrior {

    /**
     * Constructs default Knight object with default health(50) & attack(7).
     */
    public Knight() {

        super(50, 7);
    }

    Knight(int health, int attack) {

        super(health, attack);
    }

    /**
     * @param knight - copy constructor
     */
    Knight(@NotNull Knight knight) {

        super(knight);
    }
}