package org.example.battleunits;

import org.example.battleunits.units.ArmyUnit;
import org.example.battleunits.units.LancerUnit;
import org.example.fighting.Battle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LancerTest {

    @Test
    void LancerHitsOneRookieTwoOfThemDie() {
        ArmyUnit lancer = new Army(LancerUnit::newLancer, 1);
        Army rookiesArmy = new Army(Rookie::new, 2);

        assertTrue(Battle.fight(lancer, rookiesArmy));

    }

    class Rookie extends Warrior {

        @Override
        public int getAttack() {

            return 1;
        }
    }
}