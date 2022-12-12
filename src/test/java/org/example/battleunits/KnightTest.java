package org.example.battleunits;

import lombok.extern.slf4j.Slf4j;
import org.example.battleunits.weapons.Weapon;
import org.example.battleunits.weapons.WeaponFactory;
import org.example.battleunits.weapons.WeaponImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Slf4j
class KnightTest {
    private CombatUnit knight;

    static Stream<Arguments> equipWeapon() {
        return Stream.of(
                arguments(WeaponFactory.SWORD, 55, 9),
                arguments(WeaponFactory.SHIELD, 70, 6),
                arguments(WeaponFactory.GREAT_AXE, 35, 12),
                arguments(WeaponFactory.KATANA, 30, 13),
                arguments(WeaponFactory.MAGIC_WAND, 80, 10),
                arguments(WeaponImpl.builder()
                        .healthStat(-100)
                        .attackStat(-100)
                        .defenceStat(-100)
                        .vampirismStat(-100)
                        .healPowerStat(-100)
                        .build(), -50, 0, 0));
    }

    @BeforeEach
    void init() {
        knight = CombatUnitFactory.createKnight();
    }

    @DisplayName("different weapons equipped by Knight")
    @ParameterizedTest(name = "equipped {0}")
    @MethodSource({"equipWeapon"})
    void EquipDifferentWeaponsOnWarriorAndVerifyItsStats(Weapon weapon, int expectedHealth, int expectedAttack) {
        knight.equipWeapon(weapon);

        assertEquals(expectedHealth, knight.getHealth());
        assertEquals(expectedAttack, knight.getAttack());
    }

    @Test
    void EquippingWeaponsProperly() {
        CombatUnit knight = CombatUnitFactory.createKnight();

        knight.equipWeapon(WeaponFactory.SHIELD).equipWeapon(WeaponFactory.SHIELD);

        log.atDebug().log("{} with two shields.", knight);
        assertEquals(90, knight.getHealth());
        assertEquals(5, knight.getAttack());
    }
}