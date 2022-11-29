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
    private Warrior lancer;

    @BeforeEach
    void init() {
        lancer = Lancer.create();
    }

    @Test
    void LancerFightsArmyOfTwoAndMakesPiercingDamage() {
        Army oneLancerArmy = new ArmyImpl(Lancer::create, 1);
        Army army = new ArmyImpl(Warrior::create, 1)
                .addBattleUnits(() -> new KnightImpl(25, 7), 1);

        assertTrue(Battle.fight(oneLancerArmy, army));
    }

/*    @Test
    void LancerFightsTwoRookieArmy_AlwaysSetRookieHealthToOne_AndLoses_BreakProgramTest() {
        Army lancerArmy = new ArmyImpl(Lancer::create, 1);
        Army rookiesArmy = new ArmyImpl(Rookie::new, 1)
                .addBattleUnits(Rookie::new, 1);

        assertFalse(Battle.fight(lancerArmy, rookiesArmy));
    }*/

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