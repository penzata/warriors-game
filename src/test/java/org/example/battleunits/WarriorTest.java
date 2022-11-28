package org.example.battleunits;

import org.example.battleunits.characteristics.Attack;
import org.example.battleunits.characteristics.Health;
import org.example.battleunits.weapons.CustomWeapon;
import org.example.battleunits.weapons.Weapon;
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
    private Warrior warrior;

    @BeforeEach
    void init() {
        warrior = new Warrior();
    }

    @Test
    void isAlive() {
        Warrior warrior1 = new Warrior(0, 5);
        assertFalse(warrior1.isAlive());

        Warrior warrior2 = new Warrior(-1, 5);
        assertFalse(warrior2.isAlive());

        Warrior warrior3 = new Warrior(1, 5);
        assertTrue(warrior3.isAlive());
    }

    @Test
    void takeDamage() {
        Knight damageDealer = new Knight();
        warrior.receiveDamage(damageDealer);

        assertEquals(43, warrior.getHealth());
    }

    @Test
    void hit() {
        Warrior opponentWarrior = new Warrior();
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
                arguments(Weapon.sword(), 55, 7),
                arguments(Weapon.shield(), 70, 4),
                arguments(Weapon.greatAxe(), 35, 10),
                arguments(Weapon.katana(), 30, 11),
                arguments(Weapon.magicWand(), 80, 8),
                arguments(new CustomWeapon(-100, -100, -100, -100, -100), 0, 0));
    }

}