package org.example.battleunits;

import org.example.weapons.Weapon;
import org.example.weapons.WeaponType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class KnightTest {
    private Knight knight;

    @BeforeEach
    void init() {
        knight = Knight.create();
    }

    @DisplayName("different weapons equipped by Knight")
    @ParameterizedTest(name = "equipped {0}")
    @MethodSource({"equipWeapon"})
    void EquipDifferentWeaponsOnWarriorAndVerifyItsStats (Weapon weapon, int expectedHealth, int expectedAttack) {
        knight.equipWeapon(weapon);

        assertEquals(expectedHealth, knight.getHealth());
        assertEquals(expectedAttack, knight.getAttack());
    }

    static Stream<Arguments> equipWeapon() {
        return Stream.of(
                arguments(WeaponType.SWORD, 55, 9),
                arguments(WeaponType.SHIELD, 70, 6),
                arguments(WeaponType.GREAT_AXE, 35, 12),
                arguments(WeaponType.KATANA, 30, 13),
                arguments(WeaponType.MAGIC_WAND, 80, 100),
                        arguments(Weapon.builder().healthStat(-100).attackStat(-100).defenceStat(-100)
                                .vampirismStat(-100).healPowerStat(-100).build(), 0, 0, 0));
    }
}