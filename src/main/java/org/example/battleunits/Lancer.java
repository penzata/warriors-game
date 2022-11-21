package org.example.battleunits;

import org.example.battleunits.units.LancerUnit;
import org.jetbrains.annotations.NotNull;

public class Lancer extends Warrior implements LancerUnit {

    /**
     * Constructs default Lancer object with default health(50) & attack(6).
     */
    public Lancer() {

        super(50, 6);
    }

    public Lancer(int health, int attack) {

        super(health, attack);
    }

    public Lancer(@NotNull Lancer lancer) {

        super(lancer);
    }

}


