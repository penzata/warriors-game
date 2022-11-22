package org.example.battleunits.units;

import org.example.battleunits.Lancer;

public interface LancerUnit extends WarriorUnit {

    /**
     * @return default Lancer object with default health(50) & attack(6).
     */
    static Lancer newLancer() {
        return new Lancer();
    }



}
