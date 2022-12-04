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
                arguments(CombatUnit.createWarrior(), CombatUnit.createKnight(), false),
                arguments(CombatUnit.createKnight(), CombatUnit.createWarrior(), true),
                arguments(CombatUnit.createWarrior(), CombatUnit.createWarrior(), true),
                arguments(CombatUnit.createKnight(), CombatUnit.createKnight(), true),
                arguments(CombatUnit.createDefender(), CombatUnit.createKnight(), false),
                arguments(CombatUnit.createDefender(), CombatUnit.createDefender(), true),
                arguments(CombatUnit.createVampire(), CombatUnit.createDefender(), false),
                arguments(CombatUnit.createDefender(), CombatUnit.createVampire(), true),
                arguments(CombatUnit.createLancer(), CombatUnit.createVampire(), true));
    }

    static Stream<Arguments> duelWithWeapons() {
        return Stream.of(
                arguments(CombatUnit.createWarrior(), Weapon.builder()
                                .setHealthStat(-10).setAttackStat(5).setVampirismStat(40)
                                .build(),
                        CombatUnit.createVampire(), WeaponType.SWORD, true),
                arguments(CombatUnit.createDefender(), WeaponType.SHIELD, CombatUnit.createLancer(), WeaponType.GREAT_AXE, false),
                arguments(CombatUnit.createHealer(), WeaponType.MAGIC_WAND, CombatUnit.createKnight(), WeaponType.KATANA, false));
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
        Warrior warrior = CombatUnit.createWarrior();
        Warrior secondWarrior = CombatUnit.createWarrior();
        Knight knight = CombatUnit.createKnight();
        Duel.fight(warrior, knight);

        assertFalse(Duel.fight(knight, secondWarrior));
    }

    @Test
    void WarriorFightsKnightAndLosesAndBothLoseBlood() {
        Warrior warrior = CombatUnit.createWarrior();
        Knight knight = CombatUnit.createKnight();
        Duel.fight(warrior, knight);

        assertEquals(-6, warrior.getHealth());
        assertEquals(10, knight.getHealth());
    }

    @Test
    void WarriorFightsDefenderAndLoses() {
        Warrior warrior = CombatUnit.createWarrior();
        Defender defender = CombatUnit.createDefender();

        assertFalse(Duel.fight(warrior, defender));
        assertEquals(-1, warrior.getHealth());
        assertEquals(9, defender.getHealth());
    }

    @Test
    void DefenderFightsVampireAndWins() {
        Defender defender = CombatUnit.createDefender();
        Vampire vampire = CombatUnit.createVampire();

        assertTrue(Duel.fight(defender, vampire));
        assertEquals(22, defender.getHealth());
        assertEquals(-1, vampire.getHealth());
    }

    @Test
    void LancerFightsWarriorAndWins() {
        Lancer lancer = CombatUnit.createLancer();
        Warrior warrior = CombatUnit.createWarrior();

        assertTrue(Duel.fight(lancer, warrior));
        assertEquals(10, lancer.getHealth());
        assertEquals(-4, warrior.getHealth());
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
    void DuelWithWeaponsBetweenWarriorAndKnight_AndKnightLoses() {
        Warrior warrior = CombatUnit.createWarrior();
        Knight knight = CombatUnit.createKnight();
        warrior.equipWeapon(WeaponType.SWORD);
        knight.equipWeapon(WeaponType.KATANA);
        boolean result = Duel.fight(warrior, knight);

        assertTrue(result);
        assertEquals(3, warrior.getHealth());
        assertEquals(-5, knight.getHealth());
    }

}