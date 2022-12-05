package org.example.fighting;

import org.example.battleunits.Army;
import org.example.battleunits.ArmyImpl;
import org.example.battleunits.CombatUnit;
import org.example.battleunits.Rookie;
import org.example.battleunits.weapons.Weapon;
import org.example.battleunits.weapons.WeaponType;
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
                arguments(new ArmyImpl(CombatUnit::createWarrior, 1), new ArmyImpl(CombatUnit::createWarrior, 2), false),
                arguments(new ArmyImpl(CombatUnit::createWarrior, 5), new ArmyImpl(CombatUnit::createWarrior, 7), false),
                arguments(new ArmyImpl(CombatUnit::createWarrior, 2), new ArmyImpl(CombatUnit::createWarrior, 3), false),
                arguments(new ArmyImpl(CombatUnit::createWarrior, 20), new ArmyImpl(CombatUnit::createWarrior, 21), true),
                arguments(new ArmyImpl(CombatUnit::createWarrior, 10), new ArmyImpl(CombatUnit::createWarrior, 11), true),
                arguments(new ArmyImpl(CombatUnit::createWarrior, 11), new ArmyImpl(CombatUnit::createWarrior, 7), true));
    }

    static Stream<Arguments> fullDefenderBattleArmies() {
//battle7
        Army army1 = new ArmyImpl(CombatUnit::createWarrior, 5)
                .addBattleUnits(CombatUnit::createDefender, 9);
        Army army2 = new ArmyImpl(CombatUnit::createWarrior, 4);
//battle8
        Army army3 = new ArmyImpl(CombatUnit::createDefender, 5)
                .addBattleUnits(CombatUnit::createWarrior, 20)
                .addBattleUnits(CombatUnit::createDefender, 4);
        Army army4 = new ArmyImpl(CombatUnit::createWarrior, 21);
//battle9
        Army army5 = new ArmyImpl(CombatUnit::createWarrior, 10)
                .addBattleUnits(CombatUnit::createDefender, 15);
        Army army6 = new ArmyImpl(CombatUnit::createWarrior, 5);
//battle10
        Army army7 = new ArmyImpl(CombatUnit::createDefender, 2)
                .addBattleUnits(CombatUnit::createWarrior, 1)
                .addBattleUnits(CombatUnit::createDefender, 1);
        Army army8 = new ArmyImpl(CombatUnit::createWarrior, 5);

        return Stream.of(
                arguments(army1, army2, true),
                arguments(army3, army4, true),
                arguments(army5, army6, true),
                arguments(army7, army8, false));
    }

    static Stream<Arguments> armiesWithVampires() {
//battle11
        Army army1 = new ArmyImpl(CombatUnit::createDefender, 5)
                .addBattleUnits(CombatUnit::createVampire, 6)
                .addBattleUnits(CombatUnit::createWarrior, 7);
        Army army2 = new ArmyImpl(CombatUnit::createWarrior, 6)
                .addBattleUnits(CombatUnit::createDefender, 6)
                .addBattleUnits(CombatUnit::createVampire, 6);
//battle12
        Army army3 = new ArmyImpl(CombatUnit::createDefender, 2)
                .addBattleUnits(CombatUnit::createVampire, 3)
                .addBattleUnits(CombatUnit::createWarrior, 4);
        Army army4 = new ArmyImpl(CombatUnit::createWarrior, 4)
                .addBattleUnits(CombatUnit::createDefender, 4)
                .addBattleUnits(CombatUnit::createVampire, 3);
//battle13
        Army army5 = new ArmyImpl(CombatUnit::createDefender, 11)
                .addBattleUnits(CombatUnit::createVampire, 3)
                .addBattleUnits(CombatUnit::createWarrior, 4);
        Army army6 = new ArmyImpl(CombatUnit::createWarrior, 4)
                .addBattleUnits(CombatUnit::createDefender, 4)
                .addBattleUnits(CombatUnit::createVampire, 13);
//battle14
        Army army7 = new ArmyImpl(CombatUnit::createDefender, 9)
                .addBattleUnits(CombatUnit::createVampire, 3)
                .addBattleUnits(CombatUnit::createWarrior, 8);
        Army army8 = new ArmyImpl(CombatUnit::createWarrior, 4)
                .addBattleUnits(CombatUnit::createDefender, 4)
                .addBattleUnits(CombatUnit::createVampire, 13);

        return Stream.of(
                arguments(army1, army2, false),
                arguments(army3, army4, false),
                arguments(army5, army6, true),
                arguments(army7, army8, true));
    }

    static Stream<Arguments> armiesWithLancers() {
//battle15
        Army army1 = new ArmyImpl(CombatUnit::createLancer, 5)
                .addBattleUnits(CombatUnit::createVampire, 3)
                .addBattleUnits(CombatUnit::createWarrior, 4)
                .addBattleUnits(CombatUnit::createDefender, 2);
        Army army2 = new ArmyImpl(CombatUnit::createWarrior, 4)
                .addBattleUnits(CombatUnit::createDefender, 4)
                .addBattleUnits(CombatUnit::createVampire, 6)
                .addBattleUnits(CombatUnit::createLancer, 5);
//battle16
        Army army3 = new ArmyImpl(CombatUnit::createLancer, 7)
                .addBattleUnits(CombatUnit::createVampire, 3)
                .addBattleUnits(CombatUnit::createWarrior, 4)
                .addBattleUnits(CombatUnit::createDefender, 2);
        Army army4 = new ArmyImpl(CombatUnit::createWarrior, 4)
                .addBattleUnits(CombatUnit::createDefender, 4)
                .addBattleUnits(CombatUnit::createVampire, 6)
                .addBattleUnits(CombatUnit::createLancer, 4);

        return Stream.of(
                arguments(army1, army2, false),
                arguments(army3, army4, true));
    }

    static Stream<Arguments> armiesWithHealers() {
//battle17
        Army army1 = new ArmyImpl(CombatUnit::createLancer, 7)
                .addBattleUnits(CombatUnit::createVampire, 3)
                .addBattleUnits(CombatUnit::createHealer, 1)
                .addBattleUnits(CombatUnit::createWarrior, 4)
                .addBattleUnits(CombatUnit::createHealer, 1)
                .addBattleUnits(CombatUnit::createDefender, 2);
        Army army2 = new ArmyImpl(CombatUnit::createWarrior, 4)
                .addBattleUnits(CombatUnit::createDefender, 1)
                .addBattleUnits(CombatUnit::createHealer, 1)
                .addBattleUnits(CombatUnit::createVampire, 6)
                .addBattleUnits(CombatUnit::createLancer, 4);
//battle18
        ArmyImpl army3 = new ArmyImpl(CombatUnit::createLancer, 1)
                .addBattleUnits(CombatUnit::createWarrior, 3)
                .addBattleUnits(CombatUnit::createHealer, 1)
                .addBattleUnits(CombatUnit::createWarrior, 4)
                .addBattleUnits(CombatUnit::createHealer, 1)
                .addBattleUnits(CombatUnit::createWarrior, 2);
        ArmyImpl army4 = new ArmyImpl(CombatUnit::createWarrior, 4)
                .addBattleUnits(CombatUnit::createDefender, 4)
                .addBattleUnits(CombatUnit::createHealer, 1)
                .addBattleUnits(CombatUnit::createVampire, 6)
                .addBattleUnits(CombatUnit::createLancer, 4);

        return Stream.of(
                arguments(army1, army2, true),
                arguments(army3, army4, false));
    }

    static Stream<Arguments> straightBattle() {
//straight battle1
        Army army1 = new ArmyImpl(CombatUnit::createLancer, 5)
                .addBattleUnits(CombatUnit::createVampire, 3)
                .addBattleUnits(CombatUnit::createWarrior, 4)
                .addBattleUnits(CombatUnit::createDefender, 2);
        Army army2 = new ArmyImpl(CombatUnit::createWarrior, 4)
                .addBattleUnits(CombatUnit::createDefender, 4)
                .addBattleUnits(CombatUnit::createDefender, 4)
                .addBattleUnits(CombatUnit::createVampire, 6)
                .addBattleUnits(CombatUnit::createLancer, 5);
//straight battle2
        Army army3 = new ArmyImpl(CombatUnit::createLancer, 7)
                .addBattleUnits(CombatUnit::createVampire, 3)
                .addBattleUnits(CombatUnit::createWarrior, 4)
                .addBattleUnits(CombatUnit::createDefender, 2);
        Army army4 = new ArmyImpl(CombatUnit::createWarrior, 4)
                .addBattleUnits(CombatUnit::createDefender, 4)
                .addBattleUnits(CombatUnit::createVampire, 6)
                .addBattleUnits(CombatUnit::createLancer, 4);
//straight battle3
        Army army5 = new ArmyImpl(CombatUnit::createLancer, 7)
                .addBattleUnits(CombatUnit::createVampire, 3)
                .addBattleUnits(CombatUnit::createHealer, 1)
                .addBattleUnits(CombatUnit::createWarrior, 4)
                .addBattleUnits(CombatUnit::createHealer, 1)
                .addBattleUnits(CombatUnit::createDefender, 2);
        Army army6 = new ArmyImpl(CombatUnit::createWarrior, 4)
                .addBattleUnits(CombatUnit::createDefender, 4)
                .addBattleUnits(CombatUnit::createHealer, 1)
                .addBattleUnits(CombatUnit::createVampire, 6)
                .addBattleUnits(CombatUnit::createLancer, 4);
//straight battle4
        Army army7 = new ArmyImpl(CombatUnit::createLancer, 4)
                .addBattleUnits(CombatUnit::createWarrior, 3)
                .addBattleUnits(CombatUnit::createHealer, 1)
                .addBattleUnits(CombatUnit::createWarrior, 4)
                .addBattleUnits(CombatUnit::createHealer, 1)
                .addBattleUnits(CombatUnit::createKnight, 2);
        Army army8 = new ArmyImpl(CombatUnit::createWarrior, 4)
                .addBattleUnits(CombatUnit::createDefender, 4)
                .addBattleUnits(CombatUnit::createHealer, 1)
                .addBattleUnits(CombatUnit::createVampire, 2)
                .addBattleUnits(CombatUnit::createLancer, 4);


        return Stream.of(
                arguments(army1, army2, false),
                arguments(army3, army4, true),
                arguments(army5, army6, false),
                arguments(army7, army8, true));
    }

    static Stream<Arguments> battleWithWeapons() {
//battle1 with weapons
        Army army1 = new ArmyImpl(CombatUnit::createKnight, 1)
                .addBattleUnits(CombatUnit::createLancer, 1);
        army1.equipCombatUnitAtPosition(0, WeaponType.MAGIC_WAND);
        army1.equipCombatUnitAtPosition(1, WeaponType.GREAT_AXE);
        Army army2 = new ArmyImpl(CombatUnit::createVampire, 1)
                .addBattleUnits(CombatUnit::createHealer, 1);
        army2.equipCombatUnitAtPosition(0, WeaponType.MAGIC_WAND);
        army2.equipCombatUnitAtPosition(1, WeaponType.GREAT_AXE);
//battle2 with weapons
        Army army3 = new ArmyImpl(CombatUnit::createDefender, 1)
                .addBattleUnits(CombatUnit::createWarrior, 1);
        army3.equipCombatUnitAtPosition(0, WeaponType.GREAT_AXE);
        army3.equipCombatUnitAtPosition(1, WeaponType.GREAT_AXE);
        Army army4 = new ArmyImpl(CombatUnit::createKnight, 1)
                .addBattleUnits(CombatUnit::createHealer, 1);
        army4.equipCombatUnitAtPosition(0, WeaponType.SWORD);
        army4.equipCombatUnitAtPosition(1, WeaponType.SWORD);
//battle3 with weapons
        Army army5 = new ArmyImpl(CombatUnit::createDefender, 2);
        army5.equipCombatUnitAtPosition(0, WeaponType.KATANA);
        army5.equipCombatUnitAtPosition(1, WeaponType.KATANA);
        Army army6 = new ArmyImpl(CombatUnit::createKnight, 1)
                .addBattleUnits(CombatUnit::createVampire, 1);
        army6.equipCombatUnitAtPosition(0, WeaponType.KATANA);
        army6.equipCombatUnitAtPosition(1, WeaponType.KATANA);
//battle4 with weapons
        Weapon customWeapon1 = Weapon.builder().setHealthStat(-20).setAttackStat(6).setDefenceStat(1)
                .setVampirismStat(40).setHealPowerStat(-2).build();
        Weapon customWeapon2 = Weapon.builder().setHealthStat(20).setAttackStat(-2).setDefenceStat(2)
                .setVampirismStat(-55).setHealPowerStat(3).build();
        Army army7 = new ArmyImpl(CombatUnit::createKnight, 3);
        army7.equipCombatUnitAtPosition(0, customWeapon1);
        army7.equipCombatUnitAtPosition(1, customWeapon1);
        army7.equipCombatUnitAtPosition(2, customWeapon2);
        Army army8 = new ArmyImpl(CombatUnit::createWarrior, 1)
                .addBattleUnits(CombatUnit::createDefender, 2);
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
        Weapon customWeapon1 = Weapon.builder()
                .setHealthStat(-20).setAttackStat(1).setDefenceStat(1)
                .setVampirismStat(40).setHealPowerStat(-2)
                .build();
        Weapon customWeapon2 = Weapon.builder()
                .setHealthStat(20).setAttackStat(2).setDefenceStat(2)
                .setVampirismStat(-55).setHealPowerStat(3)
                .build();
        Army army1 = new ArmyImpl(CombatUnit::createVampire, 1);
        army1.equipCombatUnitAtPosition(0, customWeapon1);
        army1.equipCombatUnitAtPosition(1, customWeapon1);
        army1.equipCombatUnitAtPosition(2, customWeapon2);
        Army army2 = new ArmyImpl(CombatUnit::createWarrior, 1)
                .addBattleUnits(CombatUnit::createDefender, 2);
        army2.equipCombatUnitAtPosition(0, customWeapon1);
        army2.equipCombatUnitAtPosition(1, customWeapon2);
        army2.equipCombatUnitAtPosition(2, customWeapon2);
//straight battle2 with weapons
        Army army3 = new ArmyImpl(CombatUnit::createVampire, 2)
                .addBattleUnits(Rookie::new, 2);
        army3.equipCombatUnitAtPosition(0, WeaponType.KATANA);
        army3.equipCombatUnitAtPosition(1, WeaponType.KATANA);
        army3.equipCombatUnitAtPosition(2, WeaponType.SWORD);
        Army army4 = new ArmyImpl(CombatUnit::createWarrior, 1)
                .addBattleUnits(CombatUnit::createDefender, 2);
        army4.equipCombatUnitAtPosition(0, WeaponType.KATANA);
        army4.equipCombatUnitAtPosition(1, WeaponType.SWORD);
        army4.equipCombatUnitAtPosition(2, WeaponType.SWORD);
//straight battle3 with weapons
        Army army5 = new ArmyImpl(CombatUnit::createVampire, 3);
        army5.equipCombatUnitAtPosition(0, WeaponType.GREAT_AXE);
        army5.equipCombatUnitAtPosition(1, WeaponType.GREAT_AXE);
        army5.equipCombatUnitAtPosition(2, WeaponType.GREAT_AXE);
        Army army6 = new ArmyImpl(CombatUnit::createWarrior, 1)
                .addBattleUnits(CombatUnit::createDefender, 1);
        army6.equipCombatUnitAtPosition(0, WeaponType.SWORD);
        army6.equipCombatUnitAtPosition(1, WeaponType.SWORD);
//straight battle4 with weapons
        Army army7 = new ArmyImpl(Rookie::new, 3);
        army7.equipCombatUnitAtPosition(0, WeaponType.KATANA);
        army7.equipCombatUnitAtPosition(1, WeaponType.KATANA);
        army7.equipCombatUnitAtPosition(2, WeaponType.KATANA);
        Army army8 = new ArmyImpl(CombatUnit::createDefender, 1)
                .addBattleUnits(CombatUnit::createHealer, 1);
        army8.equipCombatUnitAtPosition(0, WeaponType.MAGIC_WAND);
        army8.equipCombatUnitAtPosition(1, WeaponType.MAGIC_WAND);

        return Stream.of(
                arguments(army1, army2, false),
                arguments(army3, army4, true),
                arguments(army5, army6, true),
                arguments(army7, army8, false));
    }

    static Stream<Arguments> battleWithWarlord() {
        //battle1 with warlord
        Army army1 = new ArmyImpl(CombatUnit::createWarlord, 1)
                .addBattleUnits(CombatUnit::createWarrior, 2)
                .addBattleUnits(CombatUnit::createLancer, 2)
                .addBattleUnits(CombatUnit::createHealer, 2);
        Army army2 = new ArmyImpl(CombatUnit::createWarlord, 1)
                .addBattleUnits(CombatUnit::createVampire, 1)
                .addBattleUnits(CombatUnit::createHealer, 2)
                .addBattleUnits(CombatUnit::createKnight, 2);
        army1.moveUnits();
        army2.moveUnits();
        //battle2 with warlord
        Army army3 = new ArmyImpl(CombatUnit::createWarrior, 2)
                .addBattleUnits(CombatUnit::createLancer, 2)
                .addBattleUnits(CombatUnit::createDefender, 1)
                .addBattleUnits(CombatUnit::createWarlord, 3);
        Army army4 = new ArmyImpl(CombatUnit::createWarlord, 2)
                .addBattleUnits(CombatUnit::createVampire, 1)
                .addBattleUnits(CombatUnit::createHealer, 5)
                .addBattleUnits(CombatUnit::createKnight, 2);
        army3.moveUnits();
        army4.moveUnits();
        //battle3 with warlord
        Army army5 = new ArmyImpl(CombatUnit::createWarrior, 2)
                .addBattleUnits(CombatUnit::createLancer, 3)
                .addBattleUnits(CombatUnit::createDefender, 1)
                .addBattleUnits(CombatUnit::createWarlord, 4);
        Army army6 = new ArmyImpl(CombatUnit::createWarlord, 1)
                .addBattleUnits(CombatUnit::createVampire, 1)
                .addBattleUnits(Rookie::new, 1)
                .addBattleUnits(CombatUnit::createKnight, 1);
        army5.equipCombatUnitAtPosition(0, WeaponType.SWORD);
        army6.equipCombatUnitAtPosition(0, WeaponType.SHIELD);
        army5.moveUnits();
        army6.moveUnits();

        return Stream.of(
                arguments(army1, army2, true),
                arguments(army3, army4, false),
                arguments(army5, army6, true));

    }

    static Stream<Arguments> straightBattleWithWarlord() {
        //straight battle1 with warlord
        Army army1 = new ArmyImpl(CombatUnit::createWarrior, 2)
                .addBattleUnits(CombatUnit::createLancer, 3)
                .addBattleUnits(CombatUnit::createDefender, 1)
                .addBattleUnits(CombatUnit::createWarlord, 1);
        Army army2 = new ArmyImpl(CombatUnit::createWarlord, 5)
                .addBattleUnits(CombatUnit::createVampire, 1)
                .addBattleUnits(Rookie::new, 1)
                .addBattleUnits(CombatUnit::createKnight, 1);
        army1.equipCombatUnitAtPosition(0, WeaponType.SWORD);
        army2.equipCombatUnitAtPosition(0, WeaponType.SHIELD);
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
        Army army1 = new ArmyImpl(CombatUnit::createLancer, 1)
                .addBattleUnits(CombatUnit::createVampire, 1);
        Army army2 = new ArmyImpl(CombatUnit::createWarrior, 1)
                .addBattleUnits(CombatUnit::createDefender, 2);

        assertFalse(Battle.fight(army1, army2));
    }

    @Test
    void TryOutTrialForStraightFight() {
        Army army1 = new ArmyImpl(CombatUnit::createWarrior, 1)
                .addBattleUnits(CombatUnit::createLancer, 1)
                .addBattleUnits(CombatUnit::createHealer, 1);
        Army army2 = new ArmyImpl(CombatUnit::createVampire, 1)
                .addBattleUnits(CombatUnit::createKnight, 1)
                .addBattleUnits(CombatUnit::createWarrior, 1)
                .addBattleUnits(CombatUnit::createLancer, 1)
                .addBattleUnits(CombatUnit::createHealer, 1);

        assertFalse(Battle.straightFight(army1, army2));

    }

    @DisplayName("straight battles between two armies")
    @ParameterizedTest(name = "straight battle{index}:  {0} vs. {1} --> attacker army wins? --> {2}")
    @MethodSource({"straightBattle"})
    void StraightBattle_WhoWinsOrLoses(ArmyImpl army1, ArmyImpl army2, Boolean expectedBattleResult) {
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
    void StraightBattleWithWeapons_WhoWinsOrLoses(ArmyImpl army1, ArmyImpl army2, Boolean expectedBattleResult) {
        boolean battleResult = Battle.straightFight(army1, army2);

        assertEquals(expectedBattleResult, battleResult);
    }

    @DisplayName("normal battles between armies with warlord")
    @ParameterizedTest(name = "battle{index} with warlord:  {0} vs. {1} --> attacker army wins? --> {2}")
    @MethodSource({"battleWithWarlord"})
    void BattleWithWarlord_WhoWinsOrLoses(ArmyImpl army1, ArmyImpl army2, Boolean expectedBattleResult) {
        boolean battleResult = Battle.fight(army1, army2);

        assertEquals(expectedBattleResult, battleResult);
    }

    @DisplayName("straight battles between armies with warlord")
    @ParameterizedTest(name = "straight battle{index} with warlord:  {0} vs. {1} --> attacker army wins? --> {2}")
    @MethodSource({"straightBattleWithWarlord"})
    void StraightBattleWithWarlord_WhoWinsOrLoses(ArmyImpl army1, ArmyImpl army2, Boolean expectedBattleResult) {
        boolean battleResult = Battle.straightFight(army1, army2);

        assertEquals(expectedBattleResult, battleResult);
    }
}