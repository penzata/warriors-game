package org.example.battleunits;

public interface Knight extends Warrior {
    /**
     * @return Knight object with default health(50) & attack(7).
     */
    static Warrior create() {
        return new KnightImpl();
    }

}