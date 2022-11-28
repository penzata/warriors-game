package org.example.battleunits;

import org.example.weapons.CustomWeapon;
import org.example.weapons.Weapon;
import org.example.fighting.Battle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class LancerTest {
    private CombatUnit lancer;

    @BeforeEach
    void init() {
        lancer = CombatUnit.newLancer();
    }

    @Test
    void LancerFightsArmyOfTwoAndMakesPiercingDamage() {
        ArmyUnit oneLancerArmy = new Army(CombatUnit::newLancer, 1);
        ArmyUnit army = new Army(CombatUnit::newWarrior, 1)
                .addBattleUnits(() -> new Knight(25, 7), 1);

        assertTrue(Battle.fight(oneLancerArmy, army));
    }

    @Test
    void LancerFightsTwoRookieArmy_AlwaysSetRookieHealthToOne_AndLoses_BreakProgramTest() {
        ArmyUnit lancerArmy = new Army(CombatUnit::newLancer, 1);
        ArmyUnit rookiesArmy = new Army(Rookie::new, 1)
                .addBattleUnits(Rookie::new, 1);

        assertFalse(Battle.fight(lancerArmy, rookiesArmy));
    }

    class Rookie extends Warrior {

        @Override
        public int getHealth() {
            return 1;
        }

        @Override
        public int getAttack() {

            return 1;
        }
    }

    @DisplayName("different weapons equipped by Lancer")
    @ParameterizedTest(name = "equipped {0}")
    @MethodSource({"equipWeapon"})
    void EquipDifferentWeaponsOnWarriorAndVerifyItsStats (Weapon weapon, int expectedHealth, int expectedAttack) {
        lancer.equipWeapon(weapon);

        assertEquals(expectedHealth, lancer.getHealth());
        assertEquals(expectedAttack, lancer.getAttack());
    }

    static Stream<Arguments> equipWeapon() {
        return Stream.of(
                arguments(Weapon.sword(), 55, 8),
                arguments(Weapon.shield(), 70, 5),
                arguments(Weapon.greatAxe(), 35, 11),
                arguments(Weapon.katana(), 30, 12),
                arguments(Weapon.magicWand(), 80, 9),
                arguments(new CustomWeapon(-100, -100, -100, -100, -100), 0, 0));
    }
}