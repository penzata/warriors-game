package org.example.battleunits;

import org.example.battleunits.units.ArmyUnit;
import org.example.battleunits.units.KnightUnit;
import org.example.battleunits.units.LancerUnit;
import org.example.battleunits.units.WarriorUnit;
import org.example.fighting.Battle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LancerTest {

    @Test
    void LancerFightsArmyOfTwoAndMakesPiercingDamage() {
        ArmyUnit lancer = new Army(LancerUnit::newLancer, 1);
        ArmyUnit rookiesArmy = new Army(WarriorUnit::newWarrior, 1)
                .addBattleUnits(KnightUnit::newKnight, 1);


        assertFalse(Battle.fight(lancer, rookiesArmy));

    }

    class Rookie extends Warrior {

        @Override
        public int getAttack() {

            return 1;
        }
    }
}