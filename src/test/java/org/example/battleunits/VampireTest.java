package org.example.battleunits;

import org.example.battleunits.units.ArmyUnit;
import org.example.battleunits.units.KnightUnit;
import org.example.battleunits.units.VampireUnit;
import org.example.battleunits.units.WarriorUnit;
import org.example.fighting.Battle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class VampireTest {
    VampireUnit vampire;
    Rookie rookie;

    @BeforeEach
    void init() {
        vampire = VampireUnit.newVampire();
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
        ArmyUnit army1 = new Army(Vampire::new, 1);
        ArmyUnit army2 = new Army(WarriorUnit::newWarrior, 1)
                .addBattleUnits(KnightUnit::newKnight, 2);

        assertFalse(Battle.fight(army1, army2));
    }

    class Rookie extends Warrior {
        @Override
        public int getAttack() {

            return 1;
        }
    }

}