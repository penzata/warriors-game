package org.example.main;

import org.example.battleunits.Army;
import org.example.battleunits.Defender;
import org.example.battleunits.Vampire;
import org.example.battleunits.Warrior;
import org.example.fighting.Battle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    @Test
    void main() {
        Army army3 = new Army(Defender::new, 2)
                .addBattleUnits(Vampire::new, 3)
                .addBattleUnits(Warrior::new, 4);
        Army army4 = new Army(Warrior::new, 4)
                .addBattleUnits(Defender::new, 4)
                .addBattleUnits(Vampire::new, 3);
        boolean result = Battle.fight(army3, army4);

        assertFalse(result);
    }
}