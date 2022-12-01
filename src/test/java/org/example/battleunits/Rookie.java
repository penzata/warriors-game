package org.example.battleunits;


import org.example.weapons.Weapon;
import org.example.weapons.WeaponType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Rookie extends WarriorImpl {

    public Rookie() {
        super(50, 1);
    }

    @DisplayName("different weapons equipped by Rookie")
    @ParameterizedTest(name = "equipped {0}")
    @MethodSource({"equipWeapon"})
    void EquipDifferentWeaponsOnRookieAndVerifyItsStats(Weapon weapon, int expectedHealth, int expectedAttack) {
        Rookie rookie = new Rookie();
        rookie.equipWeapon(weapon);

        assertEquals(expectedHealth, rookie.getHealth());
        assertEquals(expectedAttack, rookie.getAttack());
    }

    static Stream<Arguments> equipWeapon() {
        return Stream.of(
                arguments(WeaponType.SWORD, 55, 3),
                arguments(WeaponType.SHIELD, 70, 0),
                arguments(WeaponType.GREAT_AXE, 35, 6),
                arguments(WeaponType.KATANA, 30, 7),
                arguments(WeaponType.MAGIC_WAND, 80, 4),
                arguments(Weapon.builder().healthStat(-100).attackStat(-100).defenceStat(-100)
                        .vampirismStat(-100).healPowerStat(-100).build(), -50, 0, 0));
    }

}
