package org.example.fighting;

import org.example.battleunits.*;
import org.example.battleunits.units.*;
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
                arguments(new Army(WarriorUnit::newWarrior, 1), new Army(Warrior::new, 2), false),
                arguments(new Army(Warrior::new, 2), new Army(Warrior::new, 3), false),
                arguments(new Army(Warrior::new, 5), new Army(Warrior::new, 7), false),
                arguments(new Army(Warrior::new, 20), new Army(Warrior::new, 21), true),
                arguments(new Army(Warrior::new, 10), new Army(Warrior::new, 11), true),
                arguments(new Army(Warrior::new, 11), new Army(Warrior::new, 7), true));
    }

    static Stream<Arguments> fullDefenderBattleArmies() {
        Army army1 = new Army(Warrior::new, 5)
                .addBattleUnits(Defender::new, 9);
        Army army2 = new Army(Warrior::new, 4);

        Army army3 = new Army(Defender::new, 5)
                .addBattleUnits(Warrior::new, 20)
                .addBattleUnits(Defender::new, 4);
        Army army4 = new Army(Warrior::new, 21);

        Army army5 = new Army(Warrior::new, 10)
                .addBattleUnits(Defender::new, 15);
        Army army6 = new Army(Warrior::new, 5);

        Army army7 = new Army(Defender::new, 2)
                .addBattleUnits(Warrior::new, 1)
                .addBattleUnits(Defender::new, 1);
        Army army8 = new Army(Warrior::new, 5);

        return Stream.of(
                arguments(army1, army2, true),
                arguments(army3, army4, true),
                arguments(army5, army6, true),
                arguments(army7, army8, false));
    }

    static Stream<Arguments> armiesWithVampires() {
        Army army1 = new Army(Defender::new, 5)
                .addBattleUnits(Vampire::new, 6)
                .addBattleUnits(Warrior::new, 7);
        Army army2 = new Army(Warrior::new, 6)
                .addBattleUnits(Defender::new, 6)
                .addBattleUnits(Vampire::new, 6);

        Army army3 = new Army(Defender::new, 2)
                .addBattleUnits(Vampire::new, 3)
                .addBattleUnits(Warrior::new, 4);
        Army army4 = new Army(Warrior::new, 4)
                .addBattleUnits(Defender::new, 4)
                .addBattleUnits(Vampire::new, 3);

        Army army5 = new Army(Defender::new, 11)
                .addBattleUnits(Vampire::new, 3)
                .addBattleUnits(Warrior::new, 4);
        Army army6 = new Army(Warrior::new, 4)
                .addBattleUnits(Defender::new, 4)
                .addBattleUnits(Vampire::new, 13);

        Army army7 = new Army(Defender::new, 9)
                .addBattleUnits(Vampire::new, 3)
                .addBattleUnits(Warrior::new, 8);
        Army army8 = new Army(Warrior::new, 4)
                .addBattleUnits(Defender::new, 4)
                .addBattleUnits(Vampire::new, 13);

        return Stream.of(
                arguments(army1, army2, false),
                arguments(army3, army4, false),
                arguments(army5, army6, true),
                arguments(army7, army8, true));
    }

    static Stream<Arguments> armiesWithLancers() {
        ArmyUnit army1 = new Army(Lancer::new, 5)
                .addBattleUnits(Vampire::new, 3)
                .addBattleUnits(Warrior::new, 4)
                .addBattleUnits(Defender::new, 2);
        ArmyUnit army2 = new Army(Warrior::new, 4)
                .addBattleUnits(Defender::new, 4)
                .addBattleUnits(Vampire::new, 6)
                .addBattleUnits(Lancer::new, 5);

        ArmyUnit army3 = new Army(Lancer::new, 7)
                .addBattleUnits(Vampire::new, 3)
                .addBattleUnits(Warrior::new, 4)
                .addBattleUnits(Defender::new, 2);
        ArmyUnit army4 = new Army(Warrior::new, 4)
                .addBattleUnits(Defender::new, 4)
                .addBattleUnits(Vampire::new, 6)
                .addBattleUnits(Lancer::new, 4);

        return Stream.of(
                arguments(army1, army2, false),
                arguments(army3, army4, true));
    }

    static Stream<Arguments> armiesWithHealers() {
        ArmyUnit army1 = new Army(LancerUnit::newLancer, 7)
                .addBattleUnits(VampireUnit::newVampire, 3)
                .addBattleUnits(HealerUnit::newHealer, 1)
                .addBattleUnits(WarriorUnit::newWarrior, 4)
                .addBattleUnits(HealerUnit::newHealer, 1)
                .addBattleUnits(DefenderUnit::newDefender, 2);
        ArmyUnit army2 = new Army(Warrior::new, 4)
                .addBattleUnits(Defender::new, 1)
                .addBattleUnits(Healer::new, 1)
                .addBattleUnits(Vampire::new, 6)
                .addBattleUnits(Lancer::new, 4);

        Army army3 = new Army(Lancer::new, 1)
                .addBattleUnits(Warrior::new, 3)
                .addBattleUnits(Healer::new, 1)
                .addBattleUnits(Warrior::new, 4)
                .addBattleUnits(Healer::new, 1)
                .addBattleUnits(Knight::new, 2);
        Army army4 = new Army(Warrior::new,  4)
                .addBattleUnits(Defender::new, 4)
                .addBattleUnits(Healer::new, 1)
                .addBattleUnits(Vampire::new, 6)
                .addBattleUnits(Lancer::new, 4);

        return Stream.of(
                arguments(army1, army2, true),
                arguments(army3, army4, false));
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
        ArmyUnit army1 = new Army(Lancer::new, 1)
                .addBattleUnits(VampireUnit::newVampire, 1);
        ArmyUnit army2 = new Army(WarriorUnit::newWarrior, 1)
                .addBattleUnits(DefenderUnit::newDefender, 2);

        assertFalse(Battle.fight(army1, army2));
    }

/*    @Test
    void stuff() {
        ArmyUnit army1 = new Army(Warrior::new, 1)
                .addBattleUnits(Knight::new, 1)
                .addBattleUnits(Vampire:: new, 1)
                .addBattleUnits(Lancer::new, 1);
        ArmyUnit army2 = new Army(Vampire::new, 1)
                .addBattleUnits(Warrior::new, 1);
        Battle.straightFight(army1, army2);
    }*/

}