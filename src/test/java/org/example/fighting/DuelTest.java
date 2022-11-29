package org.example.fighting;

import org.example.battleunits.*;
import org.example.battleunits.KnightImpl;
import org.example.weapons.CustomWeapon;
import org.example.weapons.Weapon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.example.weapons.Weapon.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class DuelTest {

    static Stream<Arguments> differentDuelUnits() {
        return Stream.of(
                arguments(Warrior.create(), newKnight(), false),
                arguments(newKnight(), create(), true),
                arguments(create(), create(), true),
                arguments(newKnight(), newKnight(), true),
                arguments(newDefender(), newKnight(), false),
                arguments(newDefender(), create(), true),
                arguments(newVampire(), newDefender(), false),
                arguments(create(), newVampire(), true),
                arguments(newLancer(), newVampire(), true));
    }

    static Stream<Arguments> duelWithWeapons() {
        return Stream.of(
                arguments(create(), new CustomWeapon(-10, 5, 0, 40, 0),
                        newVampire(), sword(), true),
                arguments(newDefender(), shield(), newLancer(), greatAxe(), false),
                arguments(newHealer(), magicWand(), newKnight(), katana(), false));
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
        Warrior warrior = create();
        Warrior secondWarrior = create();
        KnightImpl knight = newKnight();
        Duel.fight(warrior, knight);

        assertFalse(Duel.fight(knight, secondWarrior));
    }

    @Test
    void WarriorFightsKnightAndLosesAndBothLoseBlood() {
        Warrior warrior = create();
        KnightImpl knight = newKnight();
        Duel.fight(warrior, knight);

        assertEquals(-6, warrior.getHealth());
        assertEquals(10, knight.getHealth());
    }

    @Test
    void WarriorFightsDefenderAndLoses() {
        Warrior warrior = create();
        Warrior defender = newDefender();

        assertFalse(Duel.fight(warrior, defender));
        assertEquals(-1, warrior.getHealth());
        assertEquals(9, defender.getHealth());
    }

    @Test
    void DefenderFightsVampireAndWins() {
        Warrior defender = newDefender();
        Warrior vampire = newVampire();

        assertTrue(Duel.fight(defender, vampire));
        assertEquals(22, defender.getHealth());
        assertEquals(-1, vampire.getHealth());
    }

    @Test
    void LancerFightsWarriorAndWins() {
        Warrior lancer = newLancer();
        Warrior warrior = create();

        assertTrue(Duel.fight(lancer, warrior));
        assertEquals(10, lancer.getHealth());
        assertEquals(-4, warrior.getHealth());
    }

    @Test
    void DuelWithWeaponsBetweenWarriorAndKnight_AndKnightLoses() {
        Warrior warrior = Warrior.create();
        Warrior knight = Warrior.newKnight();
        warrior.equipWeapon(Weapon.sword());
        knight.equipWeapon(Weapon.katana());
        boolean result = Duel.fight(warrior, knight);

        assertEquals(true, result);
        assertEquals(3, warrior.getHealth());
        assertEquals(-5, knight.getHealth());
    }

    @DisplayName("different duels with weapons")
    @ParameterizedTest(name = "duel{index} with weapons:  {0} with {1} vs {2} with {3} --> attacker wins? --> {4}")
    @MethodSource("duelWithWeapons")
    void FightBetweenDifferentCombatUnitsEquippedWithWeaponsAndWhoWinsOrLoses(Warrior warrior1, Weapon weapon1,
                                                                              Warrior warrior2, Weapon weapon2,
                                                                              Boolean expectedDuelResult) {
        warrior1.equipWeapon(weapon1);
        warrior2.equipWeapon(weapon2);
        boolean duelResult = Duel.fight(warrior1, warrior2);

        assertEquals(expectedDuelResult, duelResult);
    }

    @Test
    void DefenderWithShieldAndMagicWandFightsVampireWithShieldAndKatanaAndLoses() {
        Warrior defender = newDefender();
        defender.equipWeapon(shield()).equipWeapon(magicWand());
        Warrior vampire = newVampire();
        vampire.equipWeapon(shield()).equipWeapon(katana());

        assertFalse(Duel.fight(defender, vampire));
    }

}