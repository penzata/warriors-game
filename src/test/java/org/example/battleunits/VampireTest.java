package org.example.battleunits;

import org.example.battleunits.weapons.Weapon;
import org.example.fighting.Battle;
import org.example.battleunits.weapons.WeaponType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class VampireTest {
    Vampire vampire;
    Rookie rookie;

    @BeforeEach
    void init() {
        vampire = Vampire.create();
        rookie = new Rookie();
    }

    @Test
    void receiveDamageFromRookie() {
        rookie.hit(vampire);
        assertEquals(39, vampire.getHealth());

        vampire.hit(rookie);
        assertEquals(40, vampire.getHealth());
    }

    @Test
    void OneVampireArmyAttacksWarriorAndKnightAndLoses() {
        Army army1 = new ArmyImpl(Warrior::create, 1);
        Army army2 = new ArmyImpl(Warrior::create, 1)
                .addBattleUnits(Knight::create, 2);

        assertFalse(Battle.fight(army1, army2));
    }

    @DisplayName("different weapons equipped by Vampire")
    @ParameterizedTest(name = "equipped {0}")
    @MethodSource({"equipWeapon"})
    void EquipDifferentWeaponsOnWarriorAndVerifyItsStats (Weapon weapon, int expectedHealth, int expectedAttack, int expectedVampirism) {
        vampire.equipWeapon(weapon);

        assertEquals(expectedHealth, vampire.getHealth());
        assertEquals(expectedAttack, vampire.getAttack());
        assertEquals(expectedVampirism, vampire.getVampirism());
    }

    static Stream<Arguments> equipWeapon() {
        return Stream.of(
                arguments(WeaponType.SWORD, 45, 6, 50),
                arguments(WeaponType.SHIELD, 60, 3, 50),
                arguments(WeaponType.GREAT_AXE, 25, 9, 60),
                arguments(WeaponType.KATANA, 20, 10, 100),
                arguments(WeaponType.MAGIC_WAND, 70, 7, 50),
                arguments(Weapon.builder().setHealthStat(-100).setAttackStat(-100).setDefenceStat(-100)
                        .setVampirismStat(-100).setHealPowerStat(-100).build(), -60, 0, 0));
    }
}