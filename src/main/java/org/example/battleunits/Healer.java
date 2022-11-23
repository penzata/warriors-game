package org.example.battleunits;

import org.example.battleunits.units.HealerUnit;
import org.jetbrains.annotations.NotNull;

public class Healer extends Warrior implements HealerUnit {
    private int medKits = 20;

    /**
     * Constructs default Healer object with default health(60) & attack(0).
     */
    public Healer() {
        super(60, 0);
    }

    public Healer(int health, int attack) {
        super(health, attack);
    }

    @Override
    public void increaseHealth() {
        if (medKits > 0) {
            super.increaseHealth();
            medKits--;
        }
    }

        /**
         * @param healer - copy constructor
         */
    public Healer(@NotNull Healer healer) {
            super(healer);
        }
    }
