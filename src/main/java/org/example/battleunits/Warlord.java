package org.example.battleunits;

import org.example.battleunits.characteristics.Defence;
import org.example.battleunits.subsidiary.CombatUnitType;
import org.example.iterators.InfGenerator;

public interface Warlord extends CombatUnit, Defence {

    static CombatUnitType classify(CombatUnit combatUnit) {
        return combatUnit.getCombatUnitType();
    }

    Iterable<CombatUnit> rearrangeArmy(InfGenerator<CombatUnit> army);
}
