package org.example.battleunits;

import org.example.fighting.Battle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealerTest {

    @Test
    void WarriorFightsWarriorWithHealerAndLoses() {
        Army warrior = new Army(Warrior::new, 1);
        Army warriorWithHealer = new Army(Warrior::new, 1)
                .addBattleUnits(Healer::new, 1);

        assertFalse(Battle.fight(warrior, warriorWithHealer));
    }

}