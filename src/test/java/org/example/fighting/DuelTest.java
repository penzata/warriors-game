package org.example.fighting;

import org.example.battleunits.*;
import org.example.weapons.Weapon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.example.battleunits.CombatUnit.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class DuelTest {

    static Stream<Arguments> differentDuelUnits() {
        return Stream.of(
                arguments(newWarrior(), newKnight(), false),
                arguments(newKnight(), newWarrior(), true),
                arguments(newWarrior(), newWarrior(), true),
                arguments(newKnight(), newKnight(), true),
                arguments(newDefender(), newKnight(), false),
                arguments(newDefender(), newWarrior(), true),
                arguments(newVampire(), newDefender(), false),
                arguments(newWarrior(), newVampire(), true),
                arguments(newLancer(), newVampire(), true));
    }

    @DisplayName("different duels")
    @ParameterizedTest(name = "duel{index}:  {0} vs {1} --> attacker wins? --> {2}")
    @MethodSource("differentDuelUnits")
    void FightBetweenDifferentWarriorUnitsWhoWinsOrLoses(Warrior warrior1, Warrior warrior2, Boolean expectedDuelResult) {
        boolean duelResult = Duel.fight(warrior1, warrior2);

        assertEquals(expectedDuelResult, duelResult);
    }

    @Test
    void WhenWarriorFightsKnight_ThenKnightFightsAnotherWarriorAndLoses() {
        Warrior warrior = newWarrior();
        Warrior secondWarrior = newWarrior();
        Knight knight = newKnight();
        Duel.fight(warrior, knight);

        assertFalse(Duel.fight(knight, secondWarrior));
    }

    @Test
    void WarriorFightsKnightAndLosesAndBothLoseBlood() {
        Warrior warrior = newWarrior();
        Knight knight = newKnight();
        Duel.fight(warrior, knight);

        assertEquals(-6, warrior.getHealth());
        assertEquals(10, knight.getHealth());
    }

    @Test
    void WarriorFightsDefenderAndLoses() {
        Warrior warrior = newWarrior();
        Defender defender = newDefender();

        assertFalse(Duel.fight(warrior, defender));
        assertEquals(-1, warrior.getHealth());
        assertEquals(9, defender.getHealth());
    }

    @Test
    void DefenderFightsVampireAndWins() {
        Defender defender = newDefender();
        Vampire vampire = newVampire();

        assertTrue(Duel.fight(defender, vampire));
        assertEquals(22, defender.getHealth());
        assertEquals(-1, vampire.getHealth());
    }

    @Test
    void LancerFightsWarriorAndWins() {
        Lancer lancer = newLancer();
        Warrior warrior = newWarrior();

        assertTrue(Duel.fight(lancer, warrior));
        assertEquals(10, lancer.getHealth());
        assertEquals(-4, warrior.getHealth());
    }

    @Test
    void DuelWithWeaponsBetweenWarriorAndKnight_AndKnightLoses() {
        CombatUnit warrior = CombatUnit.newWarrior();
        CombatUnit knight = CombatUnit.newKnight();
        warrior.equipWeapon(Weapon.sword());
        knight.equipWeapon(Weapon.katana());
        boolean result = Duel.fight(warrior, knight);

        assertEquals(true, result);
        assertEquals(3, warrior.getHealth());
        assertEquals(-5, knight.getHealth());
    }

}