package org.example.battleunits;

import org.example.battleunits.weapons.Weapon;
import org.example.fighting.Battle;
import org.example.battleunits.weapons.WeaponType;
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
                arguments(WeaponType.SWORD, 55, 8),
                arguments(WeaponType.SHIELD, 70, 5),
                arguments(WeaponType.GREAT_AXE, 35, 11),
                arguments(WeaponType.KATANA, 30, 12),
                arguments(WeaponType.MAGIC_WAND, 80, 9),
                arguments(Weapon.builder().healthStat(-100).attackStat(-100).defenceStat(-100)
                        .vampirismStat(-100).healPowerStat(-100).build(), -50, 0, 0));
    }

}