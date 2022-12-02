package org.example.battleunits;

import org.example.battleunits.characteristics.Defence;

public interface Warlord extends Warrior, Defence {
    /**
     * @return Warlord object with default health(100), attack(4) & defence(2).
     */
    static Warlord create() {
        return new WarlordImpl();
    }

}