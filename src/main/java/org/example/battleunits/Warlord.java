package org.example.battleunits;

import org.example.battleunits.characteristics.Defence;
import org.example.battleunits.subsidiary.CombatUnitType;
import org.example.iterators.InfGenerator;

public interface Warlord extends Warrior, Defence {
    /**
     * @return Warlord object with default health(100), attack(4) & defence(2).
     */
    static Warlord create() {
        return new WarlordImpl();
    }

    static CombatUnitType classify(Warrior warrior) {
        return warrior.getCombatType();
    }

    Iterable<Warrior> rearrangeArmy(InfGenerator<Warrior> army);
}
