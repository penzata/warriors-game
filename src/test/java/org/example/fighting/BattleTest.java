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
                arguments(new Army(CombatUnit::newWarrior, 1), new Army(CombatUnit::newWarrior, 2), false),
                arguments(new Army(CombatUnit::newWarrior, 2), new Army(CombatUnit::newWarrior, 3), false),
                arguments(new Army(CombatUnit::newWarrior, 5), new Army(CombatUnit::newWarrior, 7), false),
                arguments(new Army(CombatUnit::newWarrior, 20), new Army(CombatUnit::newWarrior, 21), true),
                arguments(new Army(CombatUnit::newWarrior, 10), new Army(CombatUnit::newWarrior, 11), true),
                arguments(new Army(CombatUnit::newWarrior, 11), new Army(CombatUnit::newWarrior, 7), true));
    }

    static Stream<Arguments> fullDefenderBattleArmies() {
        Army army1 = new Army(CombatUnit::newWarrior, 5)
                .addBattleUnits(CombatUnit::newDefender, 9);
        Army army2 = new Army(CombatUnit::newWarrior, 4);

        Army army3 = new Army(CombatUnit::newDefender, 5)
                .addBattleUnits(CombatUnit::newWarrior, 20)
                .addBattleUnits(CombatUnit::newDefender, 4);
        Army army4 = new Army(CombatUnit::newWarrior, 21);

        Army army5 = new Army(CombatUnit::newWarrior, 10)
                .addBattleUnits(CombatUnit::newDefender, 15);
        Army army6 = new Army(CombatUnit::newWarrior, 5);

        Army army7 = new Army(CombatUnit::newDefender, 2)
                .addBattleUnits(CombatUnit::newWarrior, 1)
                .addBattleUnits(CombatUnit::newDefender, 1);
        Army army8 = new Army(CombatUnit::newWarrior, 5);

        return Stream.of(
                arguments(army1, army2, true),
                arguments(army3, army4, true),
                arguments(army5, army6, true),
                arguments(army7, army8, false));
    }

    static Stream<Arguments> armiesWithVampires() {
        Army army1 = new Army(CombatUnit::newDefender, 5)
                .addBattleUnits(CombatUnit::newVampire, 6)
                .addBattleUnits(CombatUnit::newWarrior, 7);
        Army army2 = new Army(CombatUnit::newWarrior, 6)
                .addBattleUnits(CombatUnit::newDefender, 6)
                .addBattleUnits(CombatUnit::newVampire, 6);

        Army army3 = new Army(CombatUnit::newDefender, 2)
                .addBattleUnits(CombatUnit::newVampire, 3)
                .addBattleUnits(CombatUnit::newWarrior, 4);
        Army army4 = new Army(CombatUnit::newWarrior, 4)
                .addBattleUnits(CombatUnit::newDefender, 4)
                .addBattleUnits(CombatUnit::newVampire, 3);

        Army army5 = new Army(CombatUnit::newDefender, 11)
                .addBattleUnits(CombatUnit::newVampire, 3)
                .addBattleUnits(CombatUnit::newWarrior, 4);
        Army army6 = new Army(CombatUnit::newWarrior, 4)
                .addBattleUnits(CombatUnit::newDefender, 4)
                .addBattleUnits(CombatUnit::newVampire, 13);

        Army army7 = new Army(CombatUnit::newDefender, 9)
                .addBattleUnits(CombatUnit::newVampire, 3)
                .addBattleUnits(CombatUnit::newWarrior, 8);
        Army army8 = new Army(CombatUnit::newWarrior, 4)
                .addBattleUnits(CombatUnit::newDefender, 4)
                .addBattleUnits(CombatUnit::newVampire, 13);

        return Stream.of(
                arguments(army1, army2, false),
                arguments(army3, army4, false),
                arguments(army5, army6, true),
                arguments(army7, army8, true));
    }

    static Stream<Arguments> armiesWithLancers() {
        ArmyUnit army1 = new Army(CombatUnit::newLancer, 5)
                .addBattleUnits(CombatUnit::newVampire, 3)
                .addBattleUnits(CombatUnit::newWarrior, 4)
                .addBattleUnits(CombatUnit::newDefender, 2);
        ArmyUnit army2 = new Army(CombatUnit::newWarrior, 4)
                .addBattleUnits(CombatUnit::newDefender, 4)
                .addBattleUnits(CombatUnit::newVampire, 6)
                .addBattleUnits(CombatUnit::newLancer, 5);

        ArmyUnit army3 = new Army(CombatUnit::newLancer, 7)
                .addBattleUnits(CombatUnit::newVampire, 3)
                .addBattleUnits(CombatUnit::newWarrior, 4)
                .addBattleUnits(CombatUnit::newDefender, 2);
        ArmyUnit army4 = new Army(CombatUnit::newWarrior, 4)
                .addBattleUnits(CombatUnit::newDefender, 4)
                .addBattleUnits(CombatUnit::newVampire, 6)
                .addBattleUnits(CombatUnit::newLancer, 4);

        return Stream.of(
                arguments(army1, army2, false),
                arguments(army3, army4, true));
    }

    static Stream<Arguments> armiesWithHealers() {
        ArmyUnit army1 = new Army(CombatUnit::newLancer, 7)
                .addBattleUnits(CombatUnit::newVampire, 3)
                .addBattleUnits(CombatUnit::newHealer, 1)
                .addBattleUnits(CombatUnit::newWarrior, 4)
                .addBattleUnits(CombatUnit::newHealer, 1)
                .addBattleUnits(CombatUnit::newDefender, 2);
        ArmyUnit army2 = new Army(CombatUnit::newWarrior, 4)
                .addBattleUnits(CombatUnit::newDefender, 1)
                .addBattleUnits(CombatUnit::newHealer, 1)
                .addBattleUnits(CombatUnit::newVampire, 6)
                .addBattleUnits(CombatUnit::newLancer, 4);

        Army army3 = new Army(CombatUnit::newLancer, 1)
                .addBattleUnits(CombatUnit::newWarrior, 3)
                .addBattleUnits(CombatUnit::newHealer, 1)
                .addBattleUnits(CombatUnit::newWarrior, 4)
                .addBattleUnits(CombatUnit::newHealer, 1)
                .addBattleUnits(CombatUnit::newWarrior, 2);
        Army army4 = new Army(CombatUnit::newWarrior, 4)
                .addBattleUnits(CombatUnit::newDefender, 4)
                .addBattleUnits(CombatUnit::newHealer, 1)
                .addBattleUnits(CombatUnit::newVampire, 6)
                .addBattleUnits(CombatUnit::newLancer, 4);

        return Stream.of(
                arguments(army1, army2, true),
                arguments(army3, army4, false));
    }

    static Stream<Arguments> straightBattle() {
        ArmyUnit army1 = new Army(CombatUnit::newLancer, 5)
                .addBattleUnits(CombatUnit::newVampire, 3)
                .addBattleUnits(CombatUnit::newWarrior, 4)
                .addBattleUnits(CombatUnit::newDefender, 2);
        ArmyUnit army2 = new Army(CombatUnit::newWarrior, 4)
                .addBattleUnits(CombatUnit::newDefender, 4)
                .addBattleUnits(CombatUnit::newDefender, 4)
                .addBattleUnits(CombatUnit::newVampire, 6)
                .addBattleUnits(CombatUnit::newLancer, 5);

        ArmyUnit army3 = new Army(CombatUnit::newLancer, 7)
                .addBattleUnits(CombatUnit::newVampire, 3)
                .addBattleUnits(CombatUnit::newWarrior, 4)
                .addBattleUnits(CombatUnit::newDefender, 2);
        ArmyUnit army4 = new Army(CombatUnit::newWarrior, 4)
                .addBattleUnits(CombatUnit::newDefender, 4)
                .addBattleUnits(CombatUnit::newVampire, 6)
                .addBattleUnits(CombatUnit::newLancer, 4);

        ArmyUnit army5 = new Army(CombatUnit::newLancer, 7)
                .addBattleUnits(CombatUnit::newVampire, 3)
                .addBattleUnits(CombatUnit::newHealer, 1)
                .addBattleUnits(CombatUnit::newWarrior, 4)
                .addBattleUnits(CombatUnit::newHealer, 1)
                .addBattleUnits(CombatUnit::newDefender, 2);
        ArmyUnit army6 = new Army(CombatUnit::newWarrior, 4)
                .addBattleUnits(CombatUnit::newDefender, 4)
                .addBattleUnits(CombatUnit::newHealer, 1)
                .addBattleUnits(CombatUnit::newVampire, 6)
                .addBattleUnits(CombatUnit::newLancer, 4);

        ArmyUnit army7 = new Army(CombatUnit::newLancer, 4)
                .addBattleUnits(CombatUnit::newWarrior, 3)
                .addBattleUnits(CombatUnit::newHealer, 1)
                .addBattleUnits(CombatUnit::newWarrior, 4)
                .addBattleUnits(CombatUnit::newHealer, 1)
                .addBattleUnits(CombatUnit::newKnight, 2);
        ArmyUnit army8 = new Army(CombatUnit::newWarrior, 4)
                .addBattleUnits(CombatUnit::newDefender, 4)
                .addBattleUnits(CombatUnit::newHealer, 1)
                .addBattleUnits(CombatUnit::newVampire, 2)
                .addBattleUnits(CombatUnit::newLancer, 4);


        return Stream.of(
                arguments(army1, army2, false),
                arguments(army3, army4, true),
                arguments(army5, army6, false),
                arguments(army7, army8, true));
    }

    static Stream<Arguments> battleWithWeapons() {
        ArmyUnit army1 = new Army(CombatUnit::newKnight, 1)
                .addBattleUnits(CombatUnit::newLancer, 1);
        army1.equipWarriorAtPosition(0, Weapon.magicWand());
        army1.equipWarriorAtPosition(1, Weapon.greatAxe());
        ArmyUnit army2 = new Army(CombatUnit::newVampire, 1)
                .addBattleUnits(CombatUnit::newHealer, 1);
        army2.equipWarriorAtPosition(0, Weapon.magicWand());
        army2.equipWarriorAtPosition(1, Weapon.greatAxe());

        ArmyUnit army3 = new Army(CombatUnit::newDefender, 1)
                .addBattleUnits(CombatUnit::newWarrior, 1);
        army3.equipWarriorAtPosition(0, Weapon.greatAxe());
        army3.equipWarriorAtPosition(1, Weapon.greatAxe());
        ArmyUnit army4 = new Army(CombatUnit::newKnight, 1)
                .addBattleUnits(CombatUnit::newHealer, 1);
        army4.equipWarriorAtPosition(0, Weapon.sword());
        army4.equipWarriorAtPosition(1, Weapon.sword());

        ArmyUnit army5 = new Army(CombatUnit::newDefender, 2);
        army5.equipWarriorAtPosition(0, Weapon.katana());
        army5.equipWarriorAtPosition(1, Weapon.katana());
        ArmyUnit army6 = new Army(CombatUnit::newKnight, 1)
                .addBattleUnits(CombatUnit::newVampire, 1);
        army6.equipWarriorAtPosition(0, Weapon.katana());
        army6.equipWarriorAtPosition(1, Weapon.katana());

        CustomWeapon customWeapon1 = new CustomWeapon(-20, 6, 1, 40, -2);
        CustomWeapon customWeapon2 = new CustomWeapon(20, -2, 2, -55, 3);
        ArmyUnit army7 = new Army(CombatUnit::newKnight, 3);
        army7.equipWarriorAtPosition(0, customWeapon1);
        army7.equipWarriorAtPosition(1, customWeapon1);
        army7.equipWarriorAtPosition(2, customWeapon2);
        ArmyUnit army8 = new Army(CombatUnit::newWarrior, 1)
                .addBattleUnits(CombatUnit::newDefender, 2);
        army7.equipWarriorAtPosition(0, customWeapon1);
        army7.equipWarriorAtPosition(1, customWeapon2);
        army7.equipWarriorAtPosition(2, customWeapon2);

        return Stream.of(
                arguments(army1, army2, true),
                arguments(army3, army4, true),
                arguments(army5, army6, false),
                arguments(army7, army8, true));
    }

    @DisplayName("different battles between two armies")
    @ParameterizedTest(name = "battle{index}:  {0} vs {1} --> attacker army wins? --> {2}")
    @MethodSource({"differentBattleArmies", "fullDefenderBattleArmies",
            "armiesWithVampires", "armiesWithLancers", "armiesWithHealers"})
    void BattleOneArmyAgainstAnotherWhoWinsOrLoses(Army army1, Army army2, Boolean expectedBattleResult) {
        boolean battleResult = Battle.fight(army1, army2);
        assertEquals(expectedBattleResult, battleResult);
    }

    @Test
    void LancerAndVampireAttackWarriorAndTwoDefendersAndLoses() {
        ArmyUnit army1 = new Army(CombatUnit::newLancer, 1)
                .addBattleUnits(CombatUnit::newVampire, 1);
        ArmyUnit army2 = new Army(CombatUnit::newWarrior, 1)
                .addBattleUnits(CombatUnit::newDefender, 2);

        assertFalse(Battle.fight(army1, army2));
    }

    @Test
    void TryOutTrialForStraightFight() {
        ArmyUnit army1 = new Army(CombatUnit::newWarrior, 1)
                .addBattleUnits(CombatUnit::newLancer, 1)
                .addBattleUnits(CombatUnit::newHealer, 1);
        ArmyUnit army2 = new Army(CombatUnit::newVampire, 1)
                .addBattleUnits(CombatUnit::newKnight, 1)
                .addBattleUnits(CombatUnit::newWarrior, 1)
                .addBattleUnits(CombatUnit::newLancer, 1);

        assertFalse(Battle.straightFight(army1, army2));

    }

    @DisplayName("straight battles between two armies")
    @ParameterizedTest(name = "straight battle{index}:  {0} vs {1} --> attacker army wins? --> {2}")
    @MethodSource({"straightBattle"})
    void StraightBattle_WhoWinsOrLoses(Army army1, Army army2, Boolean expectedBattleResult) {
        boolean battleResult = Battle.straightFight(army1, army2);
        assertEquals(expectedBattleResult, battleResult);
    }

    @DisplayName("battles between weapon equipped armies")
    @ParameterizedTest(name = "battle{index} with weapons:  {0} vs {1} --> attacker army wins? --> {2}")
    @MethodSource({"battleWithWeapons"})
    void BattleWithWeapons_WhoWinsOrLoses(Army army1, Army army2, Boolean expectedBattleResult) {
        boolean battleResult = Battle.straightFight(army1, army2);
        assertEquals(expectedBattleResult, battleResult);
    }

    @DisplayName("straight battles between weapon equipped armies")
    @ParameterizedTest(name = "straight battle{index} with weapons:  {0} vs {1} --> attacker army wins? --> {2}")
    @MethodSource({"straightBattleWithWeapons"})
    void StraightBattleWithWeapons_WhoWinsOrLoses(Army army1, Army army2, Boolean expectedBattleResult) {
        boolean battleResult = Battle.straightFight(army1, army2);
        assertEquals(expectedBattleResult, battleResult);
    }

    static Stream<Arguments> straightBattleWithWeapons() {
        CustomWeapon customWeapon1 = new CustomWeapon(-20, 1, 1, 40, -2);
        CustomWeapon customWeapon2 = new CustomWeapon(20, 2, 2, -55, 3);
        ArmyUnit army1 = new Army(CombatUnit::newVampire, 1);
        army1.equipWarriorAtPosition(0, customWeapon1);
        army1.equipWarriorAtPosition(1, customWeapon1);
        army1.equipWarriorAtPosition(2, customWeapon2);
        ArmyUnit army2 = new Army(CombatUnit::newWarrior, 1)
                .addBattleUnits(CombatUnit::newDefender, 2);
        army2.equipWarriorAtPosition(0, customWeapon1);
        army2.equipWarriorAtPosition(1, customWeapon2);
        army2.equipWarriorAtPosition(2, customWeapon2);

        ArmyUnit army3 = new Army(CombatUnit::newVampire, 2)
                .addBattleUnits(Rookie::new, 2);
        army3.equipWarriorAtPosition(0, Weapon.katana());
        army3.equipWarriorAtPosition(1, Weapon.katana());
        army3.equipWarriorAtPosition(2, Weapon.shield());
        ArmyUnit army4 = new Army(CombatUnit::newWarrior, 1)
                .addBattleUnits(CombatUnit::newDefender, 2);
        army4.equipWarriorAtPosition(0, Weapon.katana());
        army4.equipWarriorAtPosition(1, Weapon.shield());
        army4.equipWarriorAtPosition(2, Weapon.shield());

        ArmyUnit army5 = new Army(CombatUnit::newVampire, 3);
        army5.equipWarriorAtPosition(0, Weapon.greatAxe());
        army5.equipWarriorAtPosition(1, Weapon.greatAxe());
        army5.equipWarriorAtPosition(2, Weapon.greatAxe());
        ArmyUnit army6 = new Army(CombatUnit::newWarrior, 1)
                .addBattleUnits(CombatUnit::newDefender, 1);
        army6.equipWarriorAtPosition(0, Weapon.sword());
        army6.equipWarriorAtPosition(1, Weapon.sword());

        ArmyUnit army7 = new Army(Rookie::new, 3);
        army7.equipWarriorAtPosition(0, Weapon.katana());
        army7.equipWarriorAtPosition(1, Weapon.katana());
        army7.equipWarriorAtPosition(2, Weapon.katana());
        ArmyUnit army8 = new Army(CombatUnit::newDefender, 1)
                .addBattleUnits(CombatUnit::newHealer, 1);
        army8.equipWarriorAtPosition(0, Weapon.magicWand());
        army8.equipWarriorAtPosition(1, Weapon.magicWand());

        return Stream.of(
                arguments(army1, army2, false),
                arguments(army3, army4, true),
                arguments(army5, army6, true),
                arguments(army7, army8, false));
    }

    static class Rookie extends Warrior {

        @Override
        public int getAttack() {
            return 1;
        }
    }

}