package org.example.battleunits;

import org.jetbrains.annotations.NotNull;

public class Healer extends Warrior {

    /**
     * Constructs default Healer object with default health(60) & attack(0).
     */
    public Healer() {
        super(60, 0);
    }

    public Healer(int health, int attack) {
        super(health, attack);
    }

    /**
     * @param healer - copy constructor
     */
    public Healer(@NotNull Healer healer) {
        super(healer);
    }
}
