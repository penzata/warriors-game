package org.example.battleunits;

import org.example.fighting.Battle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarriorTest {
    private Warrior warrior;

    @BeforeEach
    void init() {
        warrior = new Warrior();
    }

    @Test
    void isAlive() {
        Warrior warrior1 = new Warrior(0, 5);
        assertFalse(warrior1.isAlive());

        Warrior warrior2 = new Warrior(-1, 5);
        assertFalse(warrior2.isAlive());

        Warrior warrior3 = new Warrior(1, 5);
        assertTrue(warrior3.isAlive());
    }

    @Test
    void takeDamage() {
        Knight damageDealer = new Knight();
        warrior.receiveDamage(damageDealer);

        assertEquals(43, warrior.getHealth());
    }

    @Test
    void hit() {
        Warrior opponentWarrior = new Warrior();
        warrior.hit(opponentWarrior);

        assertEquals(45, opponentWarrior.getHealth());
    }

    @Test
    void stuff() {
        Army arm1 = new Army(Warrior::new, 2);
        Army arm2 = new Army(Warrior::new, 3);
        Battle.fight(arm1, arm2);
    }

}