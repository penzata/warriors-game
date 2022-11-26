package org.example.main;

import org.example.battleunits.Army;
import org.example.battleunits.units.*;
import org.example.fighting.Battle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class MainTest {

    @Test
    void TryOutTrialForStraightFight() {
        ArmyUnit army1 = new Army(WarriorUnit::newWarrior, 1)
                .addBattleUnits(LancerUnit::newLancer, 1)
                .addBattleUnits(HealerUnit::newHealer, 1);
        ArmyUnit army2 = new Army(VampireUnit::newVampire, 1)
                .addBattleUnits(KnightUnit::newKnight, 1)
                .addBattleUnits(WarriorUnit::newWarrior, 1)
                .addBattleUnits(LancerUnit::newLancer, 1);

        assertFalse(Battle.straightFight(army1, army2));

    }

}