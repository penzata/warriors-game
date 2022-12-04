package org.example.battleunits;

import org.example.battleunits.weapons.Weapon;
import org.example.battleunits.weapons.WeaponType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class WarriorTest {
    private WarriorImpl warrior;

    @BeforeEach
    void init() {
        warrior = new WarriorImpl();
    }

    @Test
    void isAlive() {
        WarriorImpl warrior1 = new WarriorImpl(0, 5);
        assertFalse(warrior1.isAlive());

        WarriorImpl warrior2 = new WarriorImpl(-1, 5);
        assertFalse(warrior2.isAlive());

        WarriorImpl warrior3 = new WarriorImpl(1, 5);
        assertTrue(warrior3.isAlive());
    }

    @Test
    void takeDamage() {
        KnightImpl damageDealer = new KnightImpl();
        warrior.receiveDamage(damageDealer);


        assertEquals(43, warrior.getHealth());
    }

    @Test
    void hit() {
        WarriorImpl opponentWarrior = new WarriorImpl();
        warrior.hit(opponentWarrior);

        assertEquals(45, opponentWarrior.getHealth());
    }

    @DisplayName("different weapons equipped by Warrior")
    @ParameterizedTest(name = "equipped {0}")
    @MethodSource({"equipWeapon"})
    void EquipDifferentWeaponsOnWarriorAndVerifyItsStats (Weapon weapon, int expectedHealth, int expectedAttack) {
        warrior.equipWeapon(weapon);

        assertEquals(expectedHealth, warrior.getHealth());
        assertEquals(expectedAttack, warrior.getAttack());
    }

    static Stream<Arguments> equipWeapon() {
        return Stream.of(
                arguments(WeaponType.SWORD, 55, 7),
                arguments(WeaponType.SHIELD, 70, 4),
                arguments(WeaponType.GREAT_AXE, 35, 10),
                arguments(WeaponType.KATANA, 30, 11),
                arguments(WeaponType.MAGIC_WAND, 80, 8),
                arguments(Weapon.builder().setHealthStat(-100).setAttackStat(-100).setDefenceStat(-100)
                        .setVampirismStat(-100).setHealPowerStat(-100).build(), -50, 0, 0));
    }
}