package org.example.subsidiary;

import org.example.battleunits.Warrior;

@FunctionalInterface
public interface CombatUnitBehind {

    Warrior getWarriorBehind();
}
