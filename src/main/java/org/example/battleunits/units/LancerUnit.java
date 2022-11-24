package org.example.battleunits.units;

import org.example.battleunits.Lancer;
import org.example.battleunits.characteristics.PiercingAttack;

public interface LancerUnit extends WarriorUnit, PiercingAttack {

    /**
     * @return default Lancer object with default health(50) & attack(6).
     */
    static Lancer newLancer() {
        return new Lancer();
    }



}
