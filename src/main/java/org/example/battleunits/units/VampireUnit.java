package org.example.battleunits.units;

import org.example.battleunits.Vampire;
import org.example.battleunits.characteristics.Vampirism;

public interface VampireUnit extends WarriorUnit, Vampirism {
    /**
     * @return default Vampire object with default health(40), attack(4) & vampirism(50).
     */
    static Vampire newVampire() {
        return new Vampire();
    }

}
