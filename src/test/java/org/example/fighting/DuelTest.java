package org.example.fighting;

import org.example.battleunits.*;
import org.example.battleunits.units.DefenderUnit;
import org.example.battleunits.units.WarriorUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class DuelTest {
    static Logger log = LoggerFactory.getLogger(DuelTest.class);

    static Stream<Arguments> differentDuelUnits() {
        return Stream.of(
                arguments(new Warrior(), new Knight(), false),
                arguments(new Knight(), new Warrior(), true),
                arguments(new Warrior(), new Warrior(), true),
                arguments(new Knight(), new Knight(), true),
                arguments(new Defender(), new Knight(), false),
                arguments(new Defender(), new Warrior(), true),
                arguments(new Vampire(), new Defender(), false),
                arguments(new Warrior(), new Vampire(), true));
    }

    @DisplayName("different duels")
    @ParameterizedTest(name = "duel{index}:  {0} vs {1} --> attacker wins? --> {2}")
    @MethodSource("differentDuelUnits")
    void FightBetweenWarriorAndKnightOrViceVersaAndWhoWinsOrLoses(Warrior warrior1, Warrior warrior2, Boolean expectedDuelResult) {
        boolean duelResult = Duel.fight(warrior1, warrior2);

        assertEquals(expectedDuelResult, duelResult);
    }

    @Test
    void WhenWarriorFightsKnight_ThenKnightFightsAnotherWarriorAndLoses() {
        Warrior warrior = new Warrior();
        Warrior secondWarrior = new Warrior();
        Knight knight = new Knight();
        Duel.fight(warrior, knight);

        assertFalse(Duel.fight(knight, secondWarrior));
    }

    @Test
    void WarriorFightsKnightAndLosesAndBothLoseBlood() {
        Warrior warrior = new Warrior();
        Knight knight = new Knight();
        Duel.fight(warrior, knight);

        assertEquals(0, warrior.getHealth());
        assertEquals(10, knight.getHealth());
    }

    @Test
    void WarriorFightsDefenderAndLoses() {
        Warrior warrior = WarriorUnit.newWarrior();
        System.out.println(warrior);
        Defender defender = DefenderUnit.newDefender();
        System.out.println(defender);

        assertFalse(Duel.fight(warrior, defender));
        assertEquals(0, warrior.getHealth());
        assertEquals(9, defender.getHealth());
    }

    @Test
    void DefenderFightsVampireAndWins() {
        Defender defender = new Defender();
        Vampire vampire = new Vampire();

        assertTrue(Duel.fight(defender, vampire));
        assertEquals(22, defender.getHealth());
        assertEquals(0, vampire.getHealth());
    }
}