package org.example.battleunits;

import org.example.battleunits.weapons.Weapon;
import org.example.battleunits.weapons.WeaponType;
import org.example.fighting.Battle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class LancerTest {
    private Lancer lancer;

    static Stream<Arguments> equipWeapon() {
        return Stream.of(
                arguments(WeaponType.SWORD, 55, 8),
                arguments(WeaponType.SHIELD, 70, 5),
                arguments(WeaponType.GREAT_AXE, 35, 11),
                arguments(WeaponType.KATANA, 30, 12),
                arguments(WeaponType.MAGIC_WAND, 80, 9),
                arguments(Weapon.builder()
                        .setHealthStat(-100).setAttackStat(-100).setDefenceStat(-100)
                        .setVampirismStat(-100).setHealPowerStat(-100)
                        .build(), -50, 0, 0));
    }

    @BeforeEach
    void init() {
        lancer = CombatUnit.createLancer();
    }

    @Test
    void LancerFightsArmyOfTwoAndMakesPiercingDamage() {
        Army oneLancerArmy = new ArmyImpl(CombatUnit::createLancer, 1);
//        Lancer needs to hit Warrior 9 times to kill him;
//        creating custom Knight with 27 health;
//        after piercing attack (9 x 3 (50% of 6att)) Knight should be dead with 0 health.
        Army army = new ArmyImpl(CombatUnit::createWarrior, 1)
                .addBattleUnits(() -> new KnightImpl(27, 7), 1);

        assertTrue(Battle.fight(oneLancerArmy, army));
    }

    @DisplayName("different weapons equipped by Lancer")
    @ParameterizedTest(name = "equipped {0}")
    @MethodSource({"equipWeapon"})
    void EquipDifferentWeaponsOnWarriorAndVerifyItsStats(Weapon weapon, int expectedHealth, int expectedAttack) {
        lancer.equipWeapon(weapon);

        assertEquals(expectedHealth, lancer.getHealth());
        assertEquals(expectedAttack, lancer.getAttack());
    }

}