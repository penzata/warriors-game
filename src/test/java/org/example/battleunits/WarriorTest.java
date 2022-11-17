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
        warrior.setHealth(0);
        assertFalse(warrior.isAlive());

        warrior.setHealth(-1);
        assertFalse(warrior.isAlive());

        warrior.setHealth(10);
        assertTrue(warrior.isAlive());
    }

    @Test
    void takeDamage() {
        warrior.takeDamage(7);
        int currentHealth = warrior.getHealth();

        assertEquals(43, currentHealth);
    }

    @Test
    void hits() {
        Warrior opponentWarrior = new Warrior();
        warrior.hits(opponentWarrior);

        assertEquals(45, opponentWarrior.getHealth());

    }
}