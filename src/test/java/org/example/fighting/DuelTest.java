package org.example.fighting;

import org.example.battleunits.*;
import org.example.battleunits.weapons.Weapon;
import org.example.battleunits.weapons.WeaponType;
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
                arguments(Warrior.create(), Knight.create(), false),
                arguments(Knight.create(), Warrior.create(), true),
                arguments(Warrior.create(), Warrior.create(), true),
                arguments(Knight.create(), Knight.create(), true),
                arguments(Defender.create(), Knight.create(), false),
                arguments(Defender.create(), Defender.create(), true),
                arguments(Vampire.create(), Defender.create(), false),
                arguments(Defender.create(), Vampire.create(), true),
                arguments(Lancer.create(), Vampire.create(), true));
    }

    static Stream<Arguments> duelWithWeapons() {
        return Stream.of(
                arguments(Warrior.create(), Weapon.builder().healthStat(-10).attackStat(5).vampirismStat(40).build(),
                        Vampire.create(), WeaponType.SWORD, true),
                arguments(Defender.create(), WeaponType.SHIELD, Lancer.create(), WeaponType.GREAT_AXE, false),
                arguments(Healer.create(), WeaponType.MAGIC_WAND, Knight.create(), WeaponType.KATANA, false));
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
        Warrior warrior = Warrior.create();
        Warrior secondWarrior = Warrior.create();
        Knight knight = Knight.create();
        Duel.fight(warrior, knight);

        assertFalse(Duel.fight(knight, secondWarrior));
    }

    @Test
    void WarriorFightsKnightAndLosesAndBothLoseBlood() {
        Warrior warrior = Warrior.create();
        Knight knight = Knight.create();
        Duel.fight(warrior, knight);

        assertEquals(-6, warrior.getHealth());
        assertEquals(10, knight.getHealth());
    }

    @Test
    void WarriorFightsDefenderAndLoses() {
        Warrior warrior = Warrior.create();
        Defender defender = Defender.create();

        assertFalse(Duel.fight(warrior, defender));
        assertEquals(-1, warrior.getHealth());
        assertEquals(9, defender.getHealth());
    }

    @Test
    void DefenderFightsVampireAndWins() {
        Defender defender = Defender.create();
        Vampire vampire = Vampire.create();

        assertTrue(Duel.fight(defender, vampire));
        assertEquals(22, defender.getHealth());
        assertEquals(-1, vampire.getHealth());
    }

    @Test
    void LancerFightsWarriorAndWins() {
        Lancer lancer = Lancer.create();
        Warrior warrior = Warrior.create();

        assertTrue(Duel.fight(lancer, warrior));
        assertEquals(10, lancer.getHealth());
        assertEquals(-4, warrior.getHealth());
    }

    @Test
    void DuelWithWeaponsBetweenWarriorAndKnight_AndKnightLoses() {
        Warrior warrior = Warrior.create();
        Knight knight = Knight.create();
        warrior.equipWeapon(WeaponType.SWORD);
        knight.equipWeapon(WeaponType.KATANA);
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
        Defender defender = Defender.create();
        defender.equipWeapon(WeaponType.SHIELD).equipWeapon(WeaponType.MAGIC_WAND);
        Vampire vampire = Vampire.create();
        vampire.equipWeapon(WeaponType.SHIELD).equipWeapon(WeaponType.KATANA);

        assertFalse(Duel.fight(defender, vampire));
    }

}