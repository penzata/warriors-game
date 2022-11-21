package org.example.battleunits.units;

import org.example.battleunits.Knight;

public interface KnightUnit extends WarriorUnit{

    /**
     * @return default Knight object with default health(50) & attack(7).
     */
    static Knight newKnight() {
        return new Knight();
    }
}
