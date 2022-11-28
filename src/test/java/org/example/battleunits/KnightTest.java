package org.example.battleunits;

import org.example.battleunits.weapons.CustomWeapon;
import org.example.battleunits.weapons.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class KnightTest {
    private CombatUnit knight;

    @BeforeEach
    void init() {
        knight = CombatUnit.newKnight();
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
                arguments(Weapon.sword(), 55, 9),
                arguments(Weapon.shield(), 70, 6),
                arguments(Weapon.greatAxe(), 35, 12),
                arguments(Weapon.katana(), 30, 13),
                arguments(Weapon.magicWand(), 80, 10),
                arguments(new CustomWeapon(-100, -100, -100, -100, -100), 0, 0));
    }

}