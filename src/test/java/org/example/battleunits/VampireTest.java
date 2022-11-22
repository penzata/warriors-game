package org.example.battleunits;

import org.example.battleunits.units.VampireUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    class Rookie extends Warrior {
        @Override
        public int getAttack() {

            return 1;
        }
    }

}