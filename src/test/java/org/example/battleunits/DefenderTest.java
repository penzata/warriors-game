package org.example.battleunits;

import org.example.weapons.CustomWeapon;
import org.example.weapons.Weapon;
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

    class Rookie extends WarriorImpl {
        @Override
        public int getAttack() {
            return 1;
        }
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
                arguments(Weapon.sword(), 65, 5, 2),
                arguments(Weapon.shield(), 80, 2, 4),
                arguments(Weapon.greatAxe(), 45, 8, 0),
                arguments(Weapon.katana(), 40, 9, 0),
                arguments(Weapon.magicWand(), 90, 6, 2),
                arguments(new CustomWeapon(-100, -100, -100, -100, -100), 0, 0, 0));
    }
}