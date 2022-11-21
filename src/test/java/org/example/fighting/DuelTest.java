package org.example.fighting;

import org.example.battleunits.Defender;
import org.example.battleunits.Knight;
import org.example.battleunits.Vampire;
import org.example.battleunits.Warrior;
import org.example.battleunits.units.DefenderUnit;
import org.example.battleunits.units.KnightUnit;
import org.example.battleunits.units.VampireUnit;
import org.example.battleunits.units.WarriorUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class DuelTest {

    static Stream<Arguments> differentDuelUnits() {
        return Stream.of(
                arguments(WarriorUnit.newWarrior(), KnightUnit.newKnight(), false),
                arguments(KnightUnit.newKnight(), WarriorUnit.newWarrior(), true),
                arguments(WarriorUnit.newWarrior(), WarriorUnit.newWarrior(), true),
                arguments(KnightUnit.newKnight(), KnightUnit.newKnight(), true),
                arguments(DefenderUnit.newDefender(), KnightUnit.newKnight(), false),
                arguments(DefenderUnit.newDefender(), WarriorUnit.newWarrior(), true),
                arguments(VampireUnit.newVampire(), DefenderUnit.newDefender(), false),
                arguments(WarriorUnit.newWarrior(), VampireUnit.newVampire(), true));
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
        Warrior warrior = WarriorUnit.newWarrior();
        Warrior secondWarrior = WarriorUnit.newWarrior();
        Knight knight = KnightUnit.newKnight();
        Duel.fight(warrior, knight);

        assertFalse(Duel.fight(knight, secondWarrior));
    }

    @Test
    void WarriorFightsKnightAndLosesAndBothLoseBlood() {
        Warrior warrior = WarriorUnit.newWarrior();
        Knight knight = KnightUnit.newKnight();
        Duel.fight(warrior, knight);

        assertEquals(0, warrior.getHealth());
        assertEquals(10, knight.getHealth());
    }

    @Test
    void WarriorFightsDefenderAndLoses() {
        Warrior warrior = WarriorUnit.newWarrior();
        Defender defender = DefenderUnit.newDefender();

        assertFalse(Duel.fight(warrior, defender));
        assertEquals(0, warrior.getHealth());
        assertEquals(9, defender.getHealth());
    }

    @Test
    void DefenderFightsVampireAndWins() {
        Defender defender = DefenderUnit.newDefender();
        Vampire vampire = VampireUnit.newVampire();

        assertTrue(Duel.fight(defender, vampire));
        assertEquals(22, defender.getHealth());
        assertEquals(0, vampire.getHealth());
    }
}