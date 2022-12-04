package org.example.battleunits;


import lombok.extern.slf4j.Slf4j;
import org.example.battleunits.weapons.Weapon;
import org.example.battleunits.weapons.WeaponType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Slf4j
public class Rookie extends WarriorImpl {
    Rookie rookie;

    public Rookie() {
        super(50, 1);
    }

    static Stream<Arguments> equipWeapon() {
        return Stream.of(
                arguments(WeaponType.SWORD, 55, 3),
                arguments(WeaponType.SHIELD, 70, 0),
                arguments(WeaponType.GREAT_AXE, 35, 6),
                arguments(WeaponType.KATANA, 30, 7),
                arguments(WeaponType.MAGIC_WAND, 80, 4),
                arguments(Weapon.builder()
                        .setHealthStat(-100).setAttackStat(-100).setDefenceStat(-100)
                        .setVampirismStat(-100).setHealPowerStat(-100)
                        .build(), -50, 0, 0));
    }

    @BeforeEach
    void init() {
        this.rookie = new Rookie();
    }

    @DisplayName("different weapons equipped by Rookie")
    @ParameterizedTest(name = "equipped {0}")
    @MethodSource({"equipWeapon"})
    void EquipDifferentWeaponsOnRookieAndVerifyItsStats(Weapon weapon, int expectedHealth, int expectedAttack) {
        rookie.equipWeapon(weapon);

        assertEquals(expectedHealth, rookie.getHealth());
        assertEquals(expectedAttack, rookie.getAttack());
    }

}
