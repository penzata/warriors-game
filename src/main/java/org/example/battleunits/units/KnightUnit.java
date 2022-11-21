package org.example.battleunits.units;

import org.example.battleunits.Knight;

public interface KnightUnit extends WarriorUnit{

    static KnightUnit newKnight() {
        return new Knight();
    }
}
