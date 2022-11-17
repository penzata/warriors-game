package org.example.fighting;

import org.example.battleunits.Army;
import org.example.battleunits.Defender;
import org.example.battleunits.Knight;
import org.example.battleunits.Warrior;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BattleTest {

    static Stream<Arguments> differentBattleArmies() {
        return Stream.of(
                arguments(new Army(Warrior::new, 1), new Army(Warrior::new, 2), false),
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

    @DisplayName("different battles between two armies")
    @ParameterizedTest(name = "battle{index}:  {0} vs {1} --> attacker army wins? --> {2}")
    @MethodSource({"differentBattleArmies", "fullDefenderBattleArmies"})
    void BattleOneArmyAgainstAnotherWhoWinsOrLoses(Army army1, Army army2, Boolean expectedBattleResult) {
        boolean battleResult = Battle.fight(army1, army2);
        assertEquals(expectedBattleResult, battleResult);
    }

    @Test
    @DisplayName("One army of warrior & 2 knights fights against another army of two warriors and a knight")
    void TwoArmiesOfThreeFight() {
        Army army1 = new Army(Warrior::new, 1)
                .addBattleUnits(Knight::new, 2);
        Army army2 = new Army(Warrior::new, 2)
                .addBattleUnits(Knight::new, 1);

        assertTrue(Battle.fight(army1, army2));

        List<Warrior> listOfArmy1 = army1.getArmy();
        assertEquals(22, listOfArmy1.get(2).getHealth());
    }
}