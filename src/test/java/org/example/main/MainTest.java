package org.example.main;

import org.example.battleunits.Army;
import org.example.battleunits.ArmyUnit;
import org.example.battleunits.CombatUnit;
import org.example.fighting.Battle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class MainTest {

    @Test
    void TrialForBattle21() {
        ArmyUnit army5 = new Army(CombatUnit::newLancer, 7)
                .addBattleUnits(CombatUnit::newVampire, 3)
                .addBattleUnits(CombatUnit::newHealer, 1)
                .addBattleUnits(CombatUnit::newWarrior, 4)
                .addBattleUnits(CombatUnit::newHealer, 1)
                .addBattleUnits(CombatUnit::newDefender, 2);
        ArmyUnit army6 = new Army(CombatUnit::newWarrior, 4)
                .addBattleUnits(CombatUnit::newDefender, 4)
                .addBattleUnits(CombatUnit::newHealer, 1)
                .addBattleUnits(CombatUnit::newVampire, 6)
                .addBattleUnits(CombatUnit::newLancer, 4);
        boolean straightFightResult = Battle.straightFight(army5, army6);

        assertFalse(straightFightResult);
    }
}