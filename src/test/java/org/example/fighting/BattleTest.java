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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BattleTest {

    static Stream<Arguments> differentBattleArmies() {
        return Stream.of(
                arguments(new ArmyImpl(CombatUnitFactory::createWarrior, 1), new ArmyImpl(CombatUnitFactory::createWarrior, 2), false),
                arguments(new ArmyImpl(CombatUnitFactory::createWarrior, 5), new ArmyImpl(CombatUnitFactory::createWarrior, 7), false),
                arguments(new ArmyImpl(CombatUnitFactory::createWarrior, 2), new ArmyImpl(CombatUnitFactory::createWarrior, 3), false),
                arguments(new ArmyImpl(CombatUnitFactory::createWarrior, 20), new ArmyImpl(CombatUnitFactory::createWarrior, 21), true),
                arguments(new ArmyImpl(CombatUnitFactory::createWarrior, 10), new ArmyImpl(CombatUnitFactory::createWarrior, 11), true),
                arguments(new ArmyImpl(CombatUnitFactory::createWarrior, 11), new ArmyImpl(CombatUnitFactory::createWarrior, 7), true));
    }

    static Stream<Arguments> fullDefenderBattleArmies() {
//battle7
        Army army1 = new ArmyImpl(CombatUnitFactory::createWarrior, 5)
                .addBattleUnits(CombatUnitFactory::createDefender, 9);
        Army army2 = new ArmyImpl(CombatUnitFactory::createWarrior, 4);
//battle8
        Army army3 = new ArmyImpl(CombatUnitFactory::createDefender, 5)
                .addBattleUnits(CombatUnitFactory::createWarrior, 20)
                .addBattleUnits(CombatUnitFactory::createDefender, 4);
        Army army4 = new ArmyImpl(CombatUnitFactory::createWarrior, 21);
//battle9
        Army army5 = new ArmyImpl(CombatUnitFactory::createWarrior, 10)
                .addBattleUnits(CombatUnitFactory::createDefender, 15);
        Army army6 = new ArmyImpl(CombatUnitFactory::createWarrior, 5);
//battle10
        Army army7 = new ArmyImpl(CombatUnitFactory::createDefender, 2)
                .addBattleUnits(CombatUnitFactory::createWarrior, 1)
                .addBattleUnits(CombatUnitFactory::createDefender, 1);
        Army army8 = new ArmyImpl(CombatUnitFactory::createWarrior, 5);

        return Stream.of(
                arguments(army1, army2, true),
                arguments(army3, army4, true),
                arguments(army5, army6, true),
                arguments(army7, army8, false));
    }

    static Stream<Arguments> armiesWithVampires() {
//battle11
        Army army1 = new ArmyImpl(CombatUnitFactory::createDefender, 5)
                .addBattleUnits(CombatUnitFactory::createVampire, 6)
                .addBattleUnits(CombatUnitFactory::createWarrior, 7);
        Army army2 = new ArmyImpl(CombatUnitFactory::createWarrior, 6)
                .addBattleUnits(CombatUnitFactory::createDefender, 6)
                .addBattleUnits(CombatUnitFactory::createVampire, 6);
//battle12
        Army army3 = new ArmyImpl(CombatUnitFactory::createDefender, 2)
                .addBattleUnits(CombatUnitFactory::createVampire, 3)
                .addBattleUnits(CombatUnitFactory::createWarrior, 4);
        Army army4 = new ArmyImpl(CombatUnitFactory::createWarrior, 4)
                .addBattleUnits(CombatUnitFactory::createDefender, 4)
                .addBattleUnits(CombatUnitFactory::createVampire, 3);
//battle13
        Army army5 = new ArmyImpl(CombatUnitFactory::createDefender, 11)
                .addBattleUnits(CombatUnitFactory::createVampire, 3)
                .addBattleUnits(CombatUnitFactory::createWarrior, 4);
        Army army6 = new ArmyImpl(CombatUnitFactory::createWarrior, 4)
                .addBattleUnits(CombatUnitFactory::createDefender, 4)
                .addBattleUnits(CombatUnitFactory::createVampire, 13);
//battle14
        Army army7 = new ArmyImpl(CombatUnitFactory::createDefender, 9)
                .addBattleUnits(CombatUnitFactory::createVampire, 3)
                .addBattleUnits(CombatUnitFactory::createWarrior, 8);
        Army army8 = new ArmyImpl(CombatUnitFactory::createWarrior, 4)
                .addBattleUnits(CombatUnitFactory::createDefender, 4)
                .addBattleUnits(CombatUnitFactory::createVampire, 13);

        return Stream.of(
                arguments(army1, army2, false),
                arguments(army3, army4, false),
                arguments(army5, army6, true),
                arguments(army7, army8, true));
    }

    static Stream<Arguments> armiesWithLancers() {
//battle15
        Army army1 = new ArmyImpl(CombatUnitFactory::createLancer, 5)
                .addBattleUnits(CombatUnitFactory::createVampire, 3)
                .addBattleUnits(CombatUnitFactory::createWarrior, 4)
                .addBattleUnits(CombatUnitFactory::createDefender, 2);
        Army army2 = new ArmyImpl(CombatUnitFactory::createWarrior, 4)
                .addBattleUnits(CombatUnitFactory::createDefender, 4)
                .addBattleUnits(CombatUnitFactory::createVampire, 6)
                .addBattleUnits(CombatUnitFactory::createLancer, 5);
//battle16
        Army army3 = new ArmyImpl(CombatUnitFactory::createLancer, 7)
                .addBattleUnits(CombatUnitFactory::createVampire, 3)
                .addBattleUnits(CombatUnitFactory::createWarrior, 4)
                .addBattleUnits(CombatUnitFactory::createDefender, 2);
        Army army4 = new ArmyImpl(CombatUnitFactory::createWarrior, 4)
                .addBattleUnits(CombatUnitFactory::createDefender, 4)
                .addBattleUnits(CombatUnitFactory::createVampire, 6)
                .addBattleUnits(CombatUnitFactory::createLancer, 4);

        return Stream.of(
                arguments(army1, army2, false),
                arguments(army3, army4, true));
    }

    static Stream<Arguments> armiesWithHealers() {
//battle17
        Army army1 = new ArmyImpl(CombatUnitFactory::createLancer, 7)
                .addBattleUnits(CombatUnitFactory::createVampire, 3)
                .addBattleUnits(CombatUnitFactory::createHealer, 1)
                .addBattleUnits(CombatUnitFactory::createWarrior, 4)
                .addBattleUnits(CombatUnitFactory::createHealer, 1)
                .addBattleUnits(CombatUnitFactory::createDefender, 2);
        Army army2 = new ArmyImpl(CombatUnitFactory::createWarrior, 4)
                .addBattleUnits(CombatUnitFactory::createDefender, 1)
                .addBattleUnits(CombatUnitFactory::createHealer, 1)
                .addBattleUnits(CombatUnitFactory::createVampire, 6)
                .addBattleUnits(CombatUnitFactory::createLancer, 4);
//battle18
        ArmyImpl army3 = new ArmyImpl(CombatUnitFactory::createLancer, 1)
                .addBattleUnits(CombatUnitFactory::createWarrior, 3)
                .addBattleUnits(CombatUnitFactory::createHealer, 1)
                .addBattleUnits(CombatUnitFactory::createWarrior, 4)
                .addBattleUnits(CombatUnitFactory::createHealer, 1)
                .addBattleUnits(CombatUnitFactory::createWarrior, 2);
        ArmyImpl army4 = new ArmyImpl(CombatUnitFactory::createWarrior, 4)
                .addBattleUnits(CombatUnitFactory::createDefender, 4)
                .addBattleUnits(CombatUnitFactory::createHealer, 1)
                .addBattleUnits(CombatUnitFactory::createVampire, 6)
                .addBattleUnits(CombatUnitFactory::createLancer, 4);

        return Stream.of(
                arguments(army1, army2, true),
                arguments(army3, army4, false));
    }

    static Stream<Arguments> straightBattle() {
//straight battle1
        Army army1 = new ArmyImpl(CombatUnitFactory::createLancer, 5)
                .addBattleUnits(CombatUnitFactory::createVampire, 3)
                .addBattleUnits(CombatUnitFactory::createWarrior, 4)
                .addBattleUnits(CombatUnitFactory::createDefender, 2);
        Army army2 = new ArmyImpl(CombatUnitFactory::createWarrior, 4)
                .addBattleUnits(CombatUnitFactory::createDefender, 4)
                .addBattleUnits(CombatUnitFactory::createDefender, 4)
                .addBattleUnits(CombatUnitFactory::createVampire, 6)
                .addBattleUnits(CombatUnitFactory::createLancer, 5);
//straight battle2
        Army army3 = new ArmyImpl(CombatUnitFactory::createLancer, 7)
                .addBattleUnits(CombatUnitFactory::createVampire, 3)
                .addBattleUnits(CombatUnitFactory::createWarrior, 4)
                .addBattleUnits(CombatUnitFactory::createDefender, 2);
        Army army4 = new ArmyImpl(CombatUnitFactory::createWarrior, 4)
                .addBattleUnits(CombatUnitFactory::createDefender, 4)
                .addBattleUnits(CombatUnitFactory::createVampire, 6)
                .addBattleUnits(CombatUnitFactory::createLancer, 4);
//straight battle3
        Army army5 = new ArmyImpl(CombatUnitFactory::createLancer, 7)
                .addBattleUnits(CombatUnitFactory::createVampire, 3)
                .addBattleUnits(CombatUnitFactory::createHealer, 1)
                .addBattleUnits(CombatUnitFactory::createWarrior, 4)
                .addBattleUnits(CombatUnitFactory::createHealer, 1)
                .addBattleUnits(CombatUnitFactory::createDefender, 2);
        Army army6 = new ArmyImpl(CombatUnitFactory::createWarrior, 4)
                .addBattleUnits(CombatUnitFactory::createDefender, 4)
                .addBattleUnits(CombatUnitFactory::createHealer, 1)
                .addBattleUnits(CombatUnitFactory::createVampire, 6)
                .addBattleUnits(CombatUnitFactory::createLancer, 4);
//straight battle4
        Army army7 = new ArmyImpl(CombatUnitFactory::createLancer, 4)
                .addBattleUnits(CombatUnitFactory::createWarrior, 3)
                .addBattleUnits(CombatUnitFactory::createHealer, 1)
                .addBattleUnits(CombatUnitFactory::createWarrior, 4)
                .addBattleUnits(CombatUnitFactory::createHealer, 1)
                .addBattleUnits(CombatUnitFactory::createKnight, 2);
        Army army8 = new ArmyImpl(CombatUnitFactory::createWarrior, 4)
                .addBattleUnits(CombatUnitFactory::createDefender, 4)
                .addBattleUnits(CombatUnitFactory::createHealer, 1)
                .addBattleUnits(CombatUnitFactory::createVampire, 2)
                .addBattleUnits(CombatUnitFactory::createLancer, 4);


        return Stream.of(
                arguments(army1, army2, false),
                arguments(army3, army4, true),
                arguments(army5, army6, false),
                arguments(army7, army8, true));
    }

    static Stream<Arguments> battleWithWeapons() {
//battle1 with weapons
        Army army1 = new ArmyImpl(CombatUnitFactory::createKnight, 1)
                .addBattleUnits(CombatUnitFactory::createLancer, 1);
        army1.equipCombatUnitAtPosition(0, WeaponFactory.MAGIC_WAND);
        army1.equipCombatUnitAtPosition(1, WeaponFactory.GREAT_AXE);
        Army army2 = new ArmyImpl(CombatUnitFactory::createVampire, 1)
                .addBattleUnits(CombatUnitFactory::createHealer, 1);
        army2.equipCombatUnitAtPosition(0, WeaponFactory.MAGIC_WAND);
        army2.equipCombatUnitAtPosition(1, WeaponFactory.GREAT_AXE);
//battle2 with weapons
        Army army3 = new ArmyImpl(CombatUnitFactory::createDefender, 1)
                .addBattleUnits(CombatUnitFactory::createWarrior, 1);
        army3.equipCombatUnitAtPosition(0, WeaponFactory.GREAT_AXE);
        army3.equipCombatUnitAtPosition(1, WeaponFactory.GREAT_AXE);
        Army army4 = new ArmyImpl(CombatUnitFactory::createKnight, 1)
                .addBattleUnits(CombatUnitFactory::createHealer, 1);
        army4.equipCombatUnitAtPosition(0, WeaponFactory.SWORD);
        army4.equipCombatUnitAtPosition(1, WeaponFactory.SWORD);
//battle3 with weapons
        Army army5 = new ArmyImpl(CombatUnitFactory::createDefender, 2);
        army5.equipCombatUnitAtPosition(0, WeaponFactory.KATANA);
        army5.equipCombatUnitAtPosition(1, WeaponFactory.KATANA);
        Army army6 = new ArmyImpl(CombatUnitFactory::createKnight, 1)
                .addBattleUnits(CombatUnitFactory::createVampire, 1);
        army6.equipCombatUnitAtPosition(0, WeaponFactory.KATANA);
        army6.equipCombatUnitAtPosition(1, WeaponFactory.KATANA);
//battle4 with weapons
        Weapon customWeapon1 = WeaponImpl.builder().healthStat(-20).attackStat(6).defenceStat(1)
                .vampirismStat(40).healPowerStat(-2).build();
        Weapon customWeapon2 = WeaponImpl.builder().healthStat(20).attackStat(-2).defenceStat(2)
                .vampirismStat(-55).healPowerStat(3).build();
        Army army7 = new ArmyImpl(CombatUnitFactory::createKnight, 3);
        army7.equipCombatUnitAtPosition(0, customWeapon1);
        army7.equipCombatUnitAtPosition(1, customWeapon1);
        army7.equipCombatUnitAtPosition(2, customWeapon2);
        Army army8 = new ArmyImpl(CombatUnitFactory::createWarrior, 1)
                .addBattleUnits(CombatUnitFactory::createDefender, 2);
        army7.equipCombatUnitAtPosition(0, customWeapon1);
        army7.equipCombatUnitAtPosition(1, customWeapon2);
        army7.equipCombatUnitAtPosition(2, customWeapon2);

        return Stream.of(
                arguments(army1, army2, true),
                arguments(army3, army4, true),
                arguments(army5, army6, false),
                arguments(army7, army8, true));
    }

    static Stream<Arguments> straightBattleWithWeapons() {
//straight battle1 with weapons
        Weapon customWeapon1 = WeaponImpl.builder()
                .healPowerStat(-20).attackStat(1).defenceStat(1)
                .vampirismStat(40).healPowerStat(-2)
                .build();
        Weapon customWeapon2 = WeaponImpl.builder()
                .healthStat(20).attackStat(2).defenceStat(2)
                .vampirismStat(-55).healPowerStat(3)
                .build();
        Army army1 = new ArmyImpl(CombatUnitFactory::createVampire, 1);
        army1.equipCombatUnitAtPosition(0, customWeapon1);
        army1.equipCombatUnitAtPosition(1, customWeapon1);
        army1.equipCombatUnitAtPosition(2, customWeapon2);
        Army army2 = new ArmyImpl(CombatUnitFactory::createWarrior, 1)
                .addBattleUnits(CombatUnitFactory::createDefender, 2);
        army2.equipCombatUnitAtPosition(0, customWeapon1);
        army2.equipCombatUnitAtPosition(1, customWeapon2);
        army2.equipCombatUnitAtPosition(2, customWeapon2);
//straight battle2 with weapons
        Army army3 = new ArmyImpl(CombatUnitFactory::createVampire, 2)
                .addBattleUnits(Rookie::new, 2);
        army3.equipCombatUnitAtPosition(0, WeaponFactory.KATANA);
        army3.equipCombatUnitAtPosition(1, WeaponFactory.KATANA);
        army3.equipCombatUnitAtPosition(2, WeaponFactory.SWORD);
        Army army4 = new ArmyImpl(CombatUnitFactory::createWarrior, 1)
                .addBattleUnits(CombatUnitFactory::createDefender, 2);
        army4.equipCombatUnitAtPosition(0, WeaponFactory.KATANA);
        army4.equipCombatUnitAtPosition(1, WeaponFactory.SWORD);
        army4.equipCombatUnitAtPosition(2, WeaponFactory.SWORD);
//straight battle3 with weapons
        Army army5 = new ArmyImpl(CombatUnitFactory::createVampire, 3);
        army5.equipCombatUnitAtPosition(0, WeaponFactory.GREAT_AXE);
        army5.equipCombatUnitAtPosition(1, WeaponFactory.GREAT_AXE);
        army5.equipCombatUnitAtPosition(2, WeaponFactory.GREAT_AXE);
        Army army6 = new ArmyImpl(CombatUnitFactory::createWarrior, 1)
                .addBattleUnits(CombatUnitFactory::createDefender, 1);
        army6.equipCombatUnitAtPosition(0, WeaponFactory.SWORD);
        army6.equipCombatUnitAtPosition(1, WeaponFactory.SWORD);
//straight battle4 with weapons
        Army army7 = new ArmyImpl(Rookie::new, 3);
        army7.equipCombatUnitAtPosition(0, WeaponFactory.KATANA);
        army7.equipCombatUnitAtPosition(1, WeaponFactory.KATANA);
        army7.equipCombatUnitAtPosition(2, WeaponFactory.KATANA);
        Army army8 = new ArmyImpl(CombatUnitFactory::createDefender, 1)
                .addBattleUnits(CombatUnitFactory::createHealer, 1);
        army8.equipCombatUnitAtPosition(0, WeaponFactory.MAGIC_WAND);
        army8.equipCombatUnitAtPosition(1, WeaponFactory.MAGIC_WAND);

        return Stream.of(
                arguments(army1, army2, false),
                arguments(army3, army4, true),
                arguments(army5, army6, true),
                arguments(army7, army8, false));
    }

    static Stream<Arguments> battleWithWarlord() {
        //battle1 with warlord
        Army army1 = new ArmyImpl(CombatUnitFactory::createWarlord, 1)
                .addBattleUnits(CombatUnitFactory::createWarrior, 2)
                .addBattleUnits(CombatUnitFactory::createLancer, 2)
                .addBattleUnits(CombatUnitFactory::createHealer, 2);
        Army army2 = new ArmyImpl(CombatUnitFactory::createWarlord, 1)
                .addBattleUnits(CombatUnitFactory::createVampire, 1)
                .addBattleUnits(CombatUnitFactory::createHealer, 2)
                .addBattleUnits(CombatUnitFactory::createKnight, 2);
        army1.moveUnits();
        army2.moveUnits();
        //battle2 with warlord
        Army army3 = new ArmyImpl(CombatUnitFactory::createWarrior, 2)
                .addBattleUnits(CombatUnitFactory::createLancer, 2)
                .addBattleUnits(CombatUnitFactory::createDefender, 1)
                .addBattleUnits(CombatUnitFactory::createWarlord, 3);
        Army army4 = new ArmyImpl(CombatUnitFactory::createWarlord, 2)
                .addBattleUnits(CombatUnitFactory::createVampire, 1)
                .addBattleUnits(CombatUnitFactory::createHealer, 5)
                .addBattleUnits(CombatUnitFactory::createKnight, 2);
        army3.moveUnits();
        army4.moveUnits();
        //battle3 with warlord
        Army army5 = new ArmyImpl(CombatUnitFactory::createWarrior, 2)
                .addBattleUnits(CombatUnitFactory::createLancer, 3)
                .addBattleUnits(CombatUnitFactory::createDefender, 1)
                .addBattleUnits(CombatUnitFactory::createWarlord, 4);
        Army army6 = new ArmyImpl(CombatUnitFactory::createWarlord, 1)
                .addBattleUnits(CombatUnitFactory::createVampire, 1)
                .addBattleUnits(Rookie::new, 1)
                .addBattleUnits(CombatUnitFactory::createKnight, 1);
        army5.equipCombatUnitAtPosition(0, WeaponFactory.SWORD);
        army6.equipCombatUnitAtPosition(0, WeaponFactory.SHIELD);
        army5.moveUnits();
        army6.moveUnits();

        return Stream.of(
                arguments(army1, army2, true),
                arguments(army3, army4, false),
                arguments(army5, army6, true));

    }

    static Stream<Arguments> straightBattleWithWarlord() {
        //straight battle1 with warlord
        Army army1 = new ArmyImpl(CombatUnitFactory::createWarrior, 2)
                .addBattleUnits(CombatUnitFactory::createLancer, 3)
                .addBattleUnits(CombatUnitFactory::createDefender, 1)
                .addBattleUnits(CombatUnitFactory::createWarlord, 1);
        Army army2 = new ArmyImpl(CombatUnitFactory::createWarlord, 5)
                .addBattleUnits(CombatUnitFactory::createVampire, 1)
                .addBattleUnits(Rookie::new, 1)
                .addBattleUnits(CombatUnitFactory::createKnight, 1);
        army1.equipCombatUnitAtPosition(0, WeaponFactory.SWORD);
        army2.equipCombatUnitAtPosition(0, WeaponFactory.SHIELD);
        army1.moveUnits();
        army2.moveUnits();

        return Stream.of(
                arguments(army1, army2, false));
    }

    @DisplayName("different battles between two armies")
    @ParameterizedTest(name = "battle{index}:  {0} vs. {1} --> attacker army wins? --> {2}")
    @MethodSource({"differentBattleArmies", "fullDefenderBattleArmies",
            "armiesWithVampires", "armiesWithLancers", "armiesWithHealers"})
    void BattleOneArmyAgainstAnotherWhoWinsOrLoses(ArmyImpl army1, ArmyImpl army2, Boolean expectedBattleResult) {
        boolean battleResult = Battle.fight(army1, army2);

        assertEquals(expectedBattleResult, battleResult);
    }

    @Test
    void LancerAndVampireAttackWarriorAndTwoDefendersAndLoses() {
        Army army1 = new ArmyImpl(CombatUnitFactory::createLancer, 1)
                .addBattleUnits(CombatUnitFactory::createVampire, 1);
        Army army2 = new ArmyImpl(CombatUnitFactory::createWarrior, 1)
                .addBattleUnits(CombatUnitFactory::createDefender, 2);

        assertFalse(Battle.fight(army1, army2));
    }

    @Test
    void TryOutTrialForStraightFight() {
        Army army1 = new ArmyImpl(CombatUnitFactory::createWarrior, 1)
                .addBattleUnits(CombatUnitFactory::createLancer, 1)
                .addBattleUnits(CombatUnitFactory::createHealer, 1);
        Army army2 = new ArmyImpl(CombatUnitFactory::createVampire, 1)
                .addBattleUnits(CombatUnitFactory::createKnight, 1)
                .addBattleUnits(CombatUnitFactory::createWarrior, 1)
                .addBattleUnits(CombatUnitFactory::createLancer, 1)
                .addBattleUnits(CombatUnitFactory::createHealer, 1);

        assertFalse(Battle.straightFight(army1, army2));

    }

    @DisplayName("straight battles between two armies")
    @ParameterizedTest(name = "straight battle{index}:  {0} vs. {1} --> attacker army wins? --> {2}")
    @MethodSource({"straightBattle"})
    void StraightBattle_WhoWinsOrLoses(Army army1, Army army2, Boolean expectedBattleResult) {
        boolean battleResult = Battle.straightFight(army1, army2);

        assertEquals(expectedBattleResult, battleResult);
    }

    @DisplayName("battles between weapon equipped armies")
    @ParameterizedTest(name = "battle{index} with weapons:  {0} vs. {1} --> attacker army wins? --> {2}")
    @MethodSource({"battleWithWeapons"})
    void BattleWithWeapons_WhoWinsOrLoses(ArmyImpl army1, ArmyImpl army2, Boolean expectedBattleResult) {
        boolean battleResult = Battle.straightFight(army1, army2);

        assertEquals(expectedBattleResult, battleResult);
    }

    @DisplayName("straight battles between weapon equipped armies")
    @ParameterizedTest(name = "straight battle{index} with weapons:  {0} vs. {1} --> attacker army wins? --> {2}")
    @MethodSource({"straightBattleWithWeapons"})
    void StraightBattleWithWeapons_WhoWinsOrLoses(Army army1, Army army2, Boolean expectedBattleResult) {
        boolean battleResult = Battle.straightFight(army1, army2);

        assertEquals(expectedBattleResult, battleResult);
    }

    @DisplayName("normal battles between armies with warlord")
    @ParameterizedTest(name = "battle{index} with warlord:  {0} vs. {1} --> attacker army wins? --> {2}")
    @MethodSource({"battleWithWarlord"})
    void BattleWithWarlord_WhoWinsOrLoses(Army army1, Army army2, Boolean expectedBattleResult) {
        boolean battleResult = Battle.fight(army1, army2);

        assertEquals(expectedBattleResult, battleResult);
    }

    @DisplayName("straight battles between armies with warlord")
    @ParameterizedTest(name = "straight battle{index} with warlord:  {0} vs. {1} --> attacker army wins? --> {2}")
    @MethodSource({"straightBattleWithWarlord"})
    void StraightBattleWithWarlord_WhoWinsOrLoses(Army army1, Army army2, Boolean expectedBattleResult) {
        boolean battleResult = Battle.straightFight(army1, army2);

        assertEquals(expectedBattleResult, battleResult);
    }
}