package org.example.battleunits;

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
        int currentHealth = warrior.getHealth();

        assertEquals(43, currentHealth);
    }

    @Test
    void hits() {
        Warrior opponentWarrior = new Warrior();
        warrior.hit(opponentWarrior);

        assertEquals(45, opponentWarrior.getHealth());
    }

}