package org.example.battleunits;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefenderTest {

    private Defender defender;

    @BeforeEach
    void init() {
        defender = new Defender();
    }

//    @Test
//    void takeDamage() {
//        defender.receiveDamage(10);
//
//        assertEquals(52, defender.getHealth());
//    }

    @Test
    void takeDamageFromRookie() {
        Rookie rookie = new Rookie();
        rookie.hit(defender);

        assertEquals(60, defender.getHealth());
    }


    class Rookie extends Warrior {
        @Override
        public int getAttack() {

            return 1;
        }
    }
}