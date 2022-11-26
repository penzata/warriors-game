package org.example.main;

import org.example.battleunits.Army;
import org.example.battleunits.units.*;
import org.example.fighting.Battle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    @Test
    void TrialForBattle21() {
        ArmyUnit army5 = new Army(LancerUnit::newLancer, 7)
                .addBattleUnits(VampireUnit::newVampire, 3)
                .addBattleUnits(HealerUnit::newHealer, 1)
                .addBattleUnits(WarriorUnit::newWarrior, 4)
                .addBattleUnits(HealerUnit::newHealer, 1)
                .addBattleUnits(DefenderUnit::newDefender, 2);
        ArmyUnit army6 = new Army(WarriorUnit::newWarrior, 4)
                .addBattleUnits(DefenderUnit::newDefender, 4)
                .addBattleUnits(HealerUnit::newHealer, 1)
                .addBattleUnits(VampireUnit::newVampire, 6)
                .addBattleUnits(LancerUnit::newLancer, 4);
       boolean result = Battle.straightFight(army5, army6);

       assertTrue(result);

    }
}