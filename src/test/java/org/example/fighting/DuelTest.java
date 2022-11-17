package org.example.fighting;

import org.example.battleunits.Defender;
import org.example.battleunits.Knight;
import org.example.battleunits.Warrior;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class DuelTest {

    static Stream<Arguments> differentDuelUnits() {
        return Stream.of(
                arguments(new Warrior(), new Knight(), false),
                arguments(new Knight(), new Warrior(), true),
                arguments(new Warrior(), new Warrior(), true),
                arguments(new Knight(), new Knight(), true));
    }

    @DisplayName("different duels between warrior & knight")
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

        assertEquals(-6, warrior.getHealth());
        assertEquals(10, knight.getHealth());
    }

    @Test
    void WarriorFightsDefenderAndLoses() {
        Warrior warrior = new Warrior();
        Defender defender = new Defender();

        assertFalse(Duel.fight(warrior, defender));
        assertEquals(-1, warrior.getHealth());
        assertEquals(9, defender.getHealth());
    }
}