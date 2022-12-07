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
class DefenderTest {

    private Defender defender;

    static Stream<Arguments> equipWeapon() {
        return Stream.of(
                arguments(WeaponFactory.SWORD, 65, 5, 2),
                arguments(WeaponFactory.SHIELD, 80, 2, 4),
                arguments(WeaponFactory.GREAT_AXE, 45, 8, 0),
                arguments(WeaponFactory.KATANA, 40, 9, 0),
                arguments(WeaponFactory.MAGIC_WAND, 90, 6, 2),
                arguments(WeaponImpl.builder()
                        .healthStat(-100).attackStat(-100).defenceStat(-100)
                        .vampirismStat(-100).healPowerStat(-100)
                        .build(), -40, 0, 0));
    }

    @BeforeEach
    void init() {
        defender = new Defender();
    }

    @Test
    void takeDamage() {
        defender.receiveDamage(10);

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
    void EquipDifferentWeaponsOnWarriorAndVerifyItsStats(Weapon weapon, int expectedHealth,
                                                         int expectedAttack, int expectedDefence) {
        defender.equipWeapon(weapon);

        assertEquals(expectedHealth, defender.getHealth());
        assertEquals(expectedAttack, defender.getAttack());
        assertEquals(expectedDefence, defender.getDefence());
    }

    @Test
    void EquippingWeaponsProperly() {
        CombatUnit knight = CombatUnitFactory.createKnight();
        Defender defender = new Defender();

        knight.equipWeapon(WeaponFactory.SHIELD).equipWeapon(WeaponFactory.SHIELD);
        defender.equipWeapon(WeaponFactory.SHIELD).equipWeapon(WeaponFactory.KATANA);

        log.atDebug().log("{} with two shields.", knight);
        assertEquals(90, knight.getHealth());
        assertEquals(5, knight.getAttack());
        log.atDebug().log("{} with shield and katana.", defender);
        assertEquals(60, defender.getHealth());
        assertEquals(8, defender.getAttack());
        assertEquals(0, defender.getDefence());
    }
}