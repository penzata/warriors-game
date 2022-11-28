package org.example.battleunits;

import org.example.fighting.Battle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HealerTest {

    @Test
    void WarriorFightsWarriorWithHealerAndLoses() {
        Army warrior = new Army(Warrior::new, 1);
        Army warriorWithHealer = new Army(Warrior::new, 1)
                .addBattleUnits(Healer::new, 1);

        assertFalse(Battle.fight(warrior, warriorWithHealer));
    }

    @Test
    void WhenLancerAttacksWarriorUnitWithTwoHealersBehind_ThenHealersChainHealing() {
        Army army1 = new Army(Lancer::new, 1).
                addBattleUnits(Warrior::new, 1);
        Army army2 = new Army(Vampire::new, 1).
                addBattleUnits(Healer::new, 2);
        boolean result = Battle.fight(army1, army2);

        assertTrue(result);
    }

    @Test
    void WarriorVersusHealerAndHealerDoesNotHealHimself() {
        Army warrior = new Army(Warrior::new, 1);
        Army healer = new Army(Healer::new, 1);

        assertTrue(Battle.fight(warrior, healer));
    }

}