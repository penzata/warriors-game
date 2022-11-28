package org.example.battleunits;

import org.example.fighting.Battle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LancerTest {

    @Test
    void LancerFightsArmyOfTwoAndMakesPiercingDamage() {
        ArmyUnit oneLancerArmy = new Army(CombatUnit::newLancer, 1);
        ArmyUnit army = new Army(CombatUnit::newWarrior, 1)
                .addBattleUnits(() -> new Knight(25, 7), 1);

        assertTrue(Battle.fight(oneLancerArmy, army));
    }

    @Test
    void LancerFightsTwoRookieArmy_AlwaysSetRookieHealthToOne_AndLoses_BreakProgramTest() {
        ArmyUnit lancerArmy = new Army(CombatUnit::newLancer, 1);
        ArmyUnit rookiesArmy = new Army(Rookie::new, 1)
                .addBattleUnits(Rookie::new, 1);

        assertFalse(Battle.fight(lancerArmy, rookiesArmy));
    }

    class Rookie extends Warrior {

        @Override
        public int getHealth() {
            return 1;
        }

        @Override
        public int getAttack() {

            return 1;
        }
    }
}