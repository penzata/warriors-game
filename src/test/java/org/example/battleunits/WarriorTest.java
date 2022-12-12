package org.example.battleunits;

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

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class WarriorTest {
    private CombatUnit warrior;

    static Stream<Arguments> equipWeapon() {
        return Stream.of(
                arguments(WeaponFactory.SWORD, 55, 7),
                arguments(WeaponFactory.SHIELD, 70, 4),
                arguments(WeaponFactory.GREAT_AXE, 35, 10),
                arguments(WeaponFactory.KATANA, 30, 11),
                arguments(WeaponFactory.MAGIC_WAND, 80, 8),
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
        warrior = CombatUnitFactory.createWarrior();
    }

    @Test
    void isAlive() {
        CombatUnitImpl warrior1 = new Warrior(0, 5);

        assertFalse(warrior1.isAlive());

        CombatUnitImpl warrior2 = new Warrior(-1, 5);
        assertFalse(warrior2.isAlive());

        CombatUnitImpl warrior3 = new Warrior(1, 5);
        assertTrue(warrior3.isAlive());
    }

    @Test
    void takeDamage() {
        CombatUnit damageDealer = CombatUnitFactory.createKnight();
        warrior.receiveDamage(damageDealer.getAttack());

        assertEquals(43, warrior.getHealth());
    }

    @Test
    void hit() {
        CombatUnit opponentWarrior = CombatUnitFactory.createWarrior();
        warrior.hit(opponentWarrior);

        assertEquals(45, opponentWarrior.getHealth());
    }

    @DisplayName("different weapons equipped by Warrior")
    @ParameterizedTest(name = "equipped {0}")
    @MethodSource({"equipWeapon"})
    void EquipDifferentWeaponsOnWarriorAndVerifyItsStats(Weapon weapon, int expectedHealth, int expectedAttack) {
        warrior.equipWeapon(weapon);

        assertEquals(expectedHealth, warrior.getHealth());
        assertEquals(expectedAttack, warrior.getAttack());
    }
}