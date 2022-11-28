package org.example.battleunits;

import org.example.fighting.Battle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.example.battleunits.CombatUnit.newVampire;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class VampireTest {
    CombatUnit vampire;
    Rookie rookie;

    @BeforeEach
    void init() {
        vampire = newVampire();
        rookie = new Rookie();
    }

    @Test
    void receiveDamageFromRookie() {
        rookie.hit(vampire);
        assertEquals(39, vampire.getHealth());

        vampire.hit(rookie);
        assertEquals(40, vampire.getHealth());
    }

    @Test
    void OneVampireArmyAttacksWarriorAndKnightAndLoses() {
        ArmyUnit army1 = new Army(CombatUnit::newVampire, 1);
        ArmyUnit army2 = new Army(CombatUnit::newWarrior, 1)
                .addBattleUnits(CombatUnit::newKnight, 2);

        assertFalse(Battle.fight(army1, army2));
    }

    class Rookie extends Warrior {
        @Override
        public int getAttack() {

            return 1;
        }
    }

}