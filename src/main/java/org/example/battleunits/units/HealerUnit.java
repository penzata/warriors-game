package org.example.battleunits.units;

import org.example.battleunits.Healer;
import org.example.battleunits.characteristics.Heal;
import org.example.battleunits.subsidiary.ProcessCommandChain;

public interface HealerUnit extends WarriorUnit, Heal, ProcessCommandChain {

    /**
     * @return default Healer object with default health(60) & attack(0).
     */
    static Healer newHealer() {

     return new Healer();
    }
}
