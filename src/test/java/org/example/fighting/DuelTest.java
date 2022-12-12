package org.example.fighting;

import org.example.battleunits.*;
import org.example.battleunits.weapons.Weapon;
import org.example.battleunits.weapons.WeaponFactory;
import org.example.battleunits.weapons.WeaponImpl;
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
                arguments(CombatUnitFactory.createWarrior(), CombatUnitFactory.createKnight(), false),
                arguments(CombatUnitFactory.createKnight(), CombatUnitFactory.createWarrior(), true),
                arguments(CombatUnitFactory.createWarrior(), CombatUnitFactory.createWarrior(), true),
                arguments(CombatUnitFactory.createKnight(), CombatUnitFactory.createKnight(), true),
                arguments(CombatUnitFactory.createDefender(), CombatUnitFactory.createKnight(), false),
                arguments(CombatUnitFactory.createDefender(), CombatUnitFactory.createDefender(), true),
                arguments(CombatUnitFactory.createVampire(), CombatUnitFactory.createDefender(), false),
                arguments(CombatUnitFactory.createDefender(), CombatUnitFactory.createVampire(), true),
                arguments(CombatUnitFactory.createLancer(), CombatUnitFactory.createVampire(), true));
    }

    static Stream<Arguments> duelWithWeapons() {
        return Stream.of(
                arguments(CombatUnitFactory.createWarrior(), WeaponImpl.builder()
                                .healPowerStat(-10).attackStat(5).vampirismStat(40)
                                .build(),
                        CombatUnitFactory.createVampire(), WeaponFactory.SWORD, true),
                arguments(CombatUnitFactory.createDefender(), WeaponFactory.SHIELD, CombatUnitFactory.createLancer(), WeaponFactory.GREAT_AXE, false),
                arguments(CombatUnitFactory.createHealer(), WeaponFactory.MAGIC_WAND, CombatUnitFactory.createKnight(), WeaponFactory.KATANA, false));
    }

    @DisplayName("different duels")
    @ParameterizedTest(name = "duel{index}:  {0} vs {1} --> attacker wins? --> {2}")
    @MethodSource("differentDuelUnits")
    void FightBetweenDifferentWarriorUnitsWhoWinsOrLoses(CombatUnit warrior1, CombatUnit warrior2, Boolean expectedDuelResult) {
        boolean duelResult = Duel.fight(warrior1, warrior2);

        assertEquals(expectedDuelResult, duelResult);
    }

    @Test
    void WhenWarriorFightsKnight_ThenKnightFightsAnotherWarriorAndLoses() {
        CombatUnit warrior = CombatUnitFactory.createWarrior();
        CombatUnit secondWarrior = CombatUnitFactory.createWarrior();
        CombatUnit knight = CombatUnitFactory.createKnight();
        Duel.fight(warrior, knight);

        assertFalse(Duel.fight(knight, secondWarrior));
    }

    @Test
    void WarriorFightsKnightAndLosesAndBothLoseBlood() {
        CombatUnit warrior = CombatUnitFactory.createWarrior();
        CombatUnit knight = CombatUnitFactory.createKnight();
        Duel.fight(warrior, knight);

        assertEquals(-6, warrior.getHealth());
        assertEquals(10, knight.getHealth());
    }

    @Test
    void WarriorFightsDefenderAndLoses() {
        CombatUnit warrior = CombatUnitFactory.createWarrior();
        CombatUnit defender = CombatUnitFactory.createDefender();

        assertFalse(Duel.fight(warrior, defender));
        assertEquals(-1, warrior.getHealth());
        assertEquals(9, defender.getHealth());
    }

    @Test
    void DefenderFightsVampireAndWins() {
        CombatUnit defender = CombatUnitFactory.createDefender();
        CombatUnit vampire = CombatUnitFactory.createVampire();

        assertTrue(Duel.fight(defender, vampire));
        assertEquals(22, defender.getHealth());
        assertEquals(-1, vampire.getHealth());
    }

    @Test
    void LancerFightsWarriorAndWins() {
        CombatUnit lancer = CombatUnitFactory.createLancer();
        CombatUnit warrior = CombatUnitFactory.createWarrior();

        assertTrue(Duel.fight(lancer, warrior));
        assertEquals(10, lancer.getHealth());
        assertEquals(-4, warrior.getHealth());
    }

    @DisplayName("different duels with weapons")
    @ParameterizedTest(name = "duel{index} with weapons:  {0} with {1} vs {2} with {3} --> attacker wins? --> {4}")
    @MethodSource("duelWithWeapons")
    void FightBetweenDifferentCombatUnitsEquippedWithWeaponsAndWhoWinsOrLoses(CombatUnit warrior1, Weapon weapon1,
                                                                              CombatUnit warrior2, Weapon weapon2,
                                                                              Boolean expectedDuelResult) {
        warrior1.equipWeapon(weapon1);
        warrior2.equipWeapon(weapon2);
        boolean duelResult = Duel.fight(warrior1, warrior2);

        assertEquals(expectedDuelResult, duelResult);
    }

    @Test
    void DuelWithWeaponsBetweenWarriorAndKnight_AndKnightLoses() {
        CombatUnit warrior = CombatUnitFactory.createWarrior();
        CombatUnit knight = CombatUnitFactory.createKnight();
        warrior.equipWeapon(WeaponFactory.SWORD);
        knight.equipWeapon(WeaponFactory.KATANA);
        boolean result = Duel.fight(warrior, knight);

        assertTrue(result);
        assertEquals(3, warrior.getHealth());
        assertEquals(-5, knight.getHealth());
    }

}