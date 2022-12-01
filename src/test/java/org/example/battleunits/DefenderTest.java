package org.example.battleunits;

import org.example.weapons.Weapon;
import org.example.weapons.WeaponType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class DefenderTest {

    private DefenderImpl defender;

    @BeforeEach
    void init() {
        defender = new DefenderImpl();
    }

    @Test
    void takeDamage() {
        defender.receiveDamage(() -> 10);

        assertEquals(52, defender.getHealth());
    }

    @Test
    void receiveDamageFromRookie() {
        Rookie rookie = new Rookie();
        rookie.hit(defender);

        assertEquals(60, defender.getHealth());
    }

    @DisplayName("different weapons equipped by Defender")
    @ParameterizedTest(name = "equipped {0}")
    @MethodSource({"equipWeapon"})
    void EquipDifferentWeaponsOnWarriorAndVerifyItsStats (Weapon weapon, int expectedHealth,
                                                          int expectedAttack, int expectedDefence) {
        defender.equipWeapon(weapon);

        assertEquals(expectedHealth, defender.getHealth());
        assertEquals(expectedAttack, defender.getAttack());
        assertEquals(expectedDefence, defender.getDefence());
    }

    static Stream<Arguments> equipWeapon() {
        return Stream.of(
                arguments(WeaponType.SWORD, 65, 5, 2),
                arguments(WeaponType.SHIELD, 80, 2, 4),
                arguments(WeaponType.GREAT_AXE, 45, 8, 0),
                arguments(WeaponType.KATANA, 40, 9, 0),
                arguments(WeaponType.MAGIC_WAND, 90, 6, 2),
                arguments(Weapon.builder().healthStat(-100).attackStat(-100).defenceStat(-100)
                        .vampirismStat(-100).healPowerStat(-100).build(), 0, 0, 0));
    }
}