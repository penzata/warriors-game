package org.example.fighting;

import org.example.battleunits.*;
import org.example.weapons.CustomWeapon;
import org.example.weapons.Weapon;
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
                arguments(new ArmyImpl(Warrior::create, 1), new ArmyImpl(Warrior::create, 2), false),
                arguments(new ArmyImpl(Warrior::create, 2), new ArmyImpl(Warrior::create, 3), false),
                arguments(new ArmyImpl(Warrior::create, 5), new ArmyImpl(Warrior::create, 7), false),
                arguments(new ArmyImpl(Warrior::create, 20), new ArmyImpl(Warrior::create, 21), true),
                arguments(new ArmyImpl(Warrior::create, 10), new ArmyImpl(Warrior::create, 11), true),
                arguments(new ArmyImpl(Warrior::create, 11), new ArmyImpl(Warrior::create, 7), true));
    }

    static Stream<Arguments> fullDefenderBattleArmies() {
//battle7
        ArmyImpl army1 = new ArmyImpl(Warrior::create, 5)
                .addBattleUnits(Defender::create, 9);
        ArmyImpl army2 = new ArmyImpl(Warrior::create, 4);
//battle8
        ArmyImpl army3 = new ArmyImpl(Defender::create, 5)
                .addBattleUnits(Warrior::create, 20)
                .addBattleUnits(Defender::create, 4);
        ArmyImpl army4 = new ArmyImpl(Warrior::create, 21);
//battle9
        ArmyImpl army5 = new ArmyImpl(Warrior::create, 10)
                .addBattleUnits(Defender::create, 15);
        ArmyImpl army6 = new ArmyImpl(Warrior::create, 5);
//battle10
        ArmyImpl army7 = new ArmyImpl(Defender::create, 2)
                .addBattleUnits(Warrior::create, 1)
                .addBattleUnits(Defender::create, 1);
        ArmyImpl army8 = new ArmyImpl(Warrior::create, 5);

        return Stream.of(
                arguments(army1, army2, true),
                arguments(army3, army4, true),
                arguments(army5, army6, true),
                arguments(army7, army8, false));
    }

    static Stream<Arguments> armiesWithVampires() {
//battle11
        ArmyImpl army1 = new ArmyImpl(Defender::create, 5)
                .addBattleUnits(Vampire::create, 6)
                .addBattleUnits(Warrior::create, 7);
        ArmyImpl army2 = new ArmyImpl(Warrior::create, 6)
                .addBattleUnits(Defender::create, 6)
                .addBattleUnits(Vampire::create, 6);
//battle12
        ArmyImpl army3 = new ArmyImpl(Defender::create, 2)
                .addBattleUnits(Vampire::create, 3)
                .addBattleUnits(Warrior::create, 4);
        ArmyImpl army4 = new ArmyImpl(Warrior::create, 4)
                .addBattleUnits(Defender::create, 4)
                .addBattleUnits(Vampire::create, 3);
//battle13
        ArmyImpl army5 = new ArmyImpl(Defender::create, 11)
                .addBattleUnits(Vampire::create, 3)
                .addBattleUnits(Warrior::create, 4);
        ArmyImpl army6 = new ArmyImpl(Warrior::create, 4)
                .addBattleUnits(Defender::create, 4)
                .addBattleUnits(Vampire::create, 13);
//battle14
        ArmyImpl army7 = new ArmyImpl(Defender::create, 9)
                .addBattleUnits(Vampire::create, 3)
                .addBattleUnits(Warrior::create, 8);
        ArmyImpl army8 = new ArmyImpl(Warrior::create, 4)
                .addBattleUnits(Defender::create, 4)
                .addBattleUnits(Vampire::create, 13);

        return Stream.of(
                arguments(army1, army2, false),
                arguments(army3, army4, false),
                arguments(army5, army6, true),
                arguments(army7, army8, true));
    }

    static Stream<Arguments> armiesWithLancers() {
//battle15
        Army army1 = new ArmyImpl(Lancer::create, 5)
                .addBattleUnits(Vampire::create, 3)
                .addBattleUnits(Warrior::create, 4)
                .addBattleUnits(Defender::create, 2);
        Army army2 = new ArmyImpl(Warrior::create, 4)
                .addBattleUnits(Defender::create, 4)
                .addBattleUnits(Vampire::create, 6)
                .addBattleUnits(Lancer::create, 5);
//battle16
        Army army3 = new ArmyImpl(Lancer::create, 7)
                .addBattleUnits(Vampire::create, 3)
                .addBattleUnits(Warrior::create, 4)
                .addBattleUnits(Defender::create, 2);
        Army army4 = new ArmyImpl(Warrior::create, 4)
                .addBattleUnits(Defender::create, 4)
                .addBattleUnits(Vampire::create, 6)
                .addBattleUnits(Lancer::create, 4);

        return Stream.of(
                arguments(army1, army2, false),
                arguments(army3, army4, true));
    }

    static Stream<Arguments> armiesWithHealers() {
//battle17
        Army army1 = new ArmyImpl(Lancer::create, 7)
                .addBattleUnits(Vampire::create, 3)
                .addBattleUnits(Healer::create, 1)
                .addBattleUnits(Warrior::create, 4)
                .addBattleUnits(Healer::create, 1)
                .addBattleUnits(Defender::create, 2);
        Army army2 = new ArmyImpl(Warrior::create, 4)
                .addBattleUnits(Defender::create, 1)
                .addBattleUnits(Healer::create, 1)
                .addBattleUnits(Vampire::create, 6)
                .addBattleUnits(Lancer::create, 4);
//battle18
        ArmyImpl army3 = new ArmyImpl(Lancer::create, 1)
                .addBattleUnits(Warrior::create, 3)
                .addBattleUnits(Healer::create, 1)
                .addBattleUnits(Warrior::create, 4)
                .addBattleUnits(Healer::create, 1)
                .addBattleUnits(Warrior::create, 2);
        ArmyImpl army4 = new ArmyImpl(Warrior::create, 4)
                .addBattleUnits(Defender::create, 4)
                .addBattleUnits(Healer::create, 1)
                .addBattleUnits(Vampire::create, 6)
                .addBattleUnits(Lancer::create, 4);

        return Stream.of(
                arguments(army1, army2, true),
                arguments(army3, army4, false));
    }

    static Stream<Arguments> straightBattle() {
//straight battle1
        Army army1 = new ArmyImpl(Lancer::create, 5)
                .addBattleUnits(Vampire::create, 3)
                .addBattleUnits(Warrior::create, 4)
                .addBattleUnits(Defender::create, 2);
        Army army2 = new ArmyImpl(Warrior::create, 4)
                .addBattleUnits(Defender::create, 4)
                .addBattleUnits(Defender::create, 4)
                .addBattleUnits(Vampire::create, 6)
                .addBattleUnits(Lancer::create, 5);
//straight battle2
        Army army3 = new ArmyImpl(Lancer::create, 7)
                .addBattleUnits(Vampire::create, 3)
                .addBattleUnits(Warrior::create, 4)
                .addBattleUnits(Defender::create, 2);
        Army army4 = new ArmyImpl(Warrior::create, 4)
                .addBattleUnits(Defender::create, 4)
                .addBattleUnits(Vampire::create, 6)
                .addBattleUnits(Lancer::create, 4);
//straight battle3
        Army army5 = new ArmyImpl(Lancer::create, 7)
                .addBattleUnits(Vampire::create, 3)
                .addBattleUnits(Healer::create, 1)
                .addBattleUnits(Warrior::create, 4)
                .addBattleUnits(Healer::create, 1)
                .addBattleUnits(Defender::create, 2);
        Army army6 = new ArmyImpl(Warrior::create, 4)
                .addBattleUnits(Defender::create, 4)
                .addBattleUnits(Healer::create, 1)
                .addBattleUnits(Vampire::create, 6)
                .addBattleUnits(Lancer::create, 4);
//straight battle4
        Army army7 = new ArmyImpl(Lancer::create, 4)
                .addBattleUnits(Warrior::create, 3)
                .addBattleUnits(Healer::create, 1)
                .addBattleUnits(Warrior::create, 4)
                .addBattleUnits(Healer::create, 1)
                .addBattleUnits(Knight::create, 2);
        Army army8 = new ArmyImpl(Warrior::create, 4)
                .addBattleUnits(Defender::create, 4)
                .addBattleUnits(Healer::create, 1)
                .addBattleUnits(Vampire::create, 2)
                .addBattleUnits(Lancer::create, 4);


        return Stream.of(
                arguments(army1, army2, false),
                arguments(army3, army4, true),
                arguments(army5, army6, false),
                arguments(army7, army8, true));
    }

    static Stream<Arguments> battleWithWeapons() {
//battle1 with weapons
        Army army1 = new ArmyImpl(Knight::create, 1)
                .addBattleUnits(Lancer::create, 1);
        army1.equipWarriorAtPosition(0, Weapon.magicWand());
        army1.equipWarriorAtPosition(1, Weapon.greatAxe());
        Army army2 = new ArmyImpl(Vampire::create, 1)
                .addBattleUnits(Healer::create, 1);
        army2.equipWarriorAtPosition(0, Weapon.magicWand());
        army2.equipWarriorAtPosition(1, Weapon.greatAxe());
//battle2 with weapons
        Army army3 = new ArmyImpl(Defender::create, 1)
                .addBattleUnits(Warrior::create, 1);
        army3.equipWarriorAtPosition(0, Weapon.greatAxe());
        army3.equipWarriorAtPosition(1, Weapon.greatAxe());
        Army army4 = new ArmyImpl(Knight::create, 1)
                .addBattleUnits(Healer::create, 1);
        army4.equipWarriorAtPosition(0, Weapon.sword());
        army4.equipWarriorAtPosition(1, Weapon.sword());
//battle3 with weapons
        Army army5 = new ArmyImpl(Defender::create, 2);
        army5.equipWarriorAtPosition(0, Weapon.katana());
        army5.equipWarriorAtPosition(1, Weapon.katana());
        Army army6 = new ArmyImpl(Knight::create, 1)
                .addBattleUnits(Vampire::create, 1);
        army6.equipWarriorAtPosition(0, Weapon.katana());
        army6.equipWarriorAtPosition(1, Weapon.katana());
//battle4 with weapons
        CustomWeapon customWeapon1 = new CustomWeapon(-20, 6, 1, 40, -2);
        CustomWeapon customWeapon2 = new CustomWeapon(20, -2, 2, -55, 3);
        Army army7 = new ArmyImpl(Knight::create, 3);
        army7.equipWarriorAtPosition(0, customWeapon1);
        army7.equipWarriorAtPosition(1, customWeapon1);
        army7.equipWarriorAtPosition(2, customWeapon2);
        Army army8 = new ArmyImpl(Warrior::create, 1)
                .addBattleUnits(Defender::create, 2);
        army7.equipWarriorAtPosition(0, customWeapon1);
        army7.equipWarriorAtPosition(1, customWeapon2);
        army7.equipWarriorAtPosition(2, customWeapon2);

        return Stream.of(
                arguments(army1, army2, true),
                arguments(army3, army4, true),
                arguments(army5, army6, false),
                arguments(army7, army8, true));
    }

    static Stream<Arguments> straightBattleWithWeapons() {
//straight battle1 with weapons
        CustomWeapon customWeapon1 = new CustomWeapon(-20, 1, 1, 40, -2);
        CustomWeapon customWeapon2 = new CustomWeapon(20, 2, 2, -55, 3);
        Army army1 = new ArmyImpl(Vampire::create, 1);
        army1.equipWarriorAtPosition(0, customWeapon1);
        army1.equipWarriorAtPosition(1, customWeapon1);
        army1.equipWarriorAtPosition(2, customWeapon2);
        Army army2 = new ArmyImpl(Warrior::create, 1)
                .addBattleUnits(Defender::create, 2);
        army2.equipWarriorAtPosition(0, customWeapon1);
        army2.equipWarriorAtPosition(1, customWeapon2);
        army2.equipWarriorAtPosition(2, customWeapon2);
//straight battle2 with weapons
        Army army3 = new ArmyImpl(Vampire::create, 2)
                .addBattleUnits(Rookie::new, 2);
        army3.equipWarriorAtPosition(0, Weapon.katana());
        army3.equipWarriorAtPosition(1, Weapon.katana());
        army3.equipWarriorAtPosition(2, Weapon.shield());
        Army army4 = new ArmyImpl(Warrior::create, 1)
                .addBattleUnits(Defender::create, 2);
        army4.equipWarriorAtPosition(0, Weapon.katana());
        army4.equipWarriorAtPosition(1, Weapon.shield());
        army4.equipWarriorAtPosition(2, Weapon.shield());
//straight battle3 with weapons
        Army army5 = new ArmyImpl(Vampire::create, 3);
        army5.equipWarriorAtPosition(0, Weapon.greatAxe());
        army5.equipWarriorAtPosition(1, Weapon.greatAxe());
        army5.equipWarriorAtPosition(2, Weapon.greatAxe());
        Army army6 = new ArmyImpl(Warrior::create, 1)
                .addBattleUnits(Defender::create, 1);
        army6.equipWarriorAtPosition(0, Weapon.sword());
        army6.equipWarriorAtPosition(1, Weapon.sword());
//straight battle4 with weapons
        Army army7 = new ArmyImpl(Rookie::new, 3);
        army7.equipWarriorAtPosition(0, Weapon.katana());
        army7.equipWarriorAtPosition(1, Weapon.katana());
        army7.equipWarriorAtPosition(2, Weapon.katana());
        Army army8 = new ArmyImpl(Defender::create, 1)
                .addBattleUnits(Healer::create, 1);
        army8.equipWarriorAtPosition(0, Weapon.magicWand());
        army8.equipWarriorAtPosition(1, Weapon.magicWand());

        return Stream.of(
                arguments(army1, army2, false),
                arguments(army3, army4, true),
                arguments(army5, army6, true),
                arguments(army7, army8, false));
    }

    @DisplayName("different battles between two armies")
    @ParameterizedTest(name = "battle{index}:  {0} vs {1} --> attacker army wins? --> {2}")
    @MethodSource({"differentBattleArmies", "fullDefenderBattleArmies",
            "armiesWithVampires", "armiesWithLancers", "armiesWithHealers"})
    void BattleOneArmyAgainstAnotherWhoWinsOrLoses(ArmyImpl army1, ArmyImpl army2, Boolean expectedBattleResult) {
        boolean battleResult = Battle.fight(army1, army2);
        assertEquals(expectedBattleResult, battleResult);
    }

    @Test
    void LancerAndVampireAttackWarriorAndTwoDefendersAndLoses() {
        Army army1 = new ArmyImpl(Lancer::create, 1)
                .addBattleUnits(Vampire::create, 1);
        Army army2 = new ArmyImpl(Warrior::create, 1)
                .addBattleUnits(Defender::create, 2);

        assertFalse(Battle.fight(army1, army2));
    }

    @Test
    void TryOutTrialForStraightFight() {
        Army army1 = new ArmyImpl(Warrior::create, 1)
                .addBattleUnits(Lancer::create, 1)
                .addBattleUnits(Healer::create, 1);
        Army army2 = new ArmyImpl(Vampire::create, 1)
                .addBattleUnits(Knight::create, 1)
                .addBattleUnits(Warrior::create, 1)
                .addBattleUnits(Lancer::create, 1)
                .addBattleUnits(Healer::create, 1);
        System.out.println(army2);

        assertFalse(Battle.straightFight(army1, army2));

    }

    @DisplayName("straight battles between two armies")
    @ParameterizedTest(name = "straight battle{index}:  {0} vs {1} --> attacker army wins? --> {2}")
    @MethodSource({"straightBattle"})
    void StraightBattle_WhoWinsOrLoses(ArmyImpl army1, ArmyImpl army2, Boolean expectedBattleResult) {
        boolean battleResult = Battle.straightFight(army1, army2);
        assertEquals(expectedBattleResult, battleResult);
    }

    @DisplayName("battles between weapon equipped armies")
    @ParameterizedTest(name = "battle{index} with weapons:  {0} vs {1} --> attacker army wins? --> {2}")
    @MethodSource({"battleWithWeapons"})
    void BattleWithWeapons_WhoWinsOrLoses(ArmyImpl army1, ArmyImpl army2, Boolean expectedBattleResult) {
        boolean battleResult = Battle.straightFight(army1, army2);
        assertEquals(expectedBattleResult, battleResult);
    }

    @DisplayName("straight battles between weapon equipped armies")
    @ParameterizedTest(name = "straight battle{index} with weapons:  {0} vs {1} --> attacker army wins? --> {2}")
    @MethodSource({"straightBattleWithWeapons"})
    void StraightBattleWithWeapons_WhoWinsOrLoses(ArmyImpl army1, ArmyImpl army2, Boolean expectedBattleResult) {
        boolean battleResult = Battle.straightFight(army1, army2);
        assertEquals(expectedBattleResult, battleResult);
    }

}