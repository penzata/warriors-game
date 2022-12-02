package org.example.battleunits.subsidiary;

import org.example.battleunits.Warrior;

@FunctionalInterface
public interface CombatUnitBehind {

    Warrior getWarriorBehind();
}
