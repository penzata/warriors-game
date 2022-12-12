package org.example.battleunits.characteristics;

import org.example.battleunits.CombatUnit;

public interface HealPower {
    int getHealPower();

    int getMedKit();

    void healCombatUnit(CombatUnit commandSender);
}
