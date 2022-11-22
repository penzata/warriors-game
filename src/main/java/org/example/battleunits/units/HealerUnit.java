package org.example.battleunits.units;

import org.example.battleunits.Healer;

public interface HealerUnit extends WarriorUnit{

    /**
     * @return default Healer object with default health(60) & attack(0).
     */
    static Healer newHealer() {

     return new Healer();
    }
}
