package org.example.battleunits;

import org.example.weapons.Weapon;
import org.example.fighting.Battle;
import org.example.weapons.WeaponType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class HealerTest {
    private Healer healer;

    @BeforeEach
    void init() {
        healer = Healer.create();
    }

    @Test
    void WarriorFightsWarriorWithHealerAndLoses() {
        ArmyImpl warrior = new ArmyImpl(Warrior::create, 1);
        ArmyImpl warriorWithHealer = new ArmyImpl(Warrior::create, 1)
                .addBattleUnits(Healer::create, 1);

        assertFalse(Battle.fight(warrior, warriorWithHealer));
    }

    @Test
    void WhenLancerAttacksWarriorUnitWithTwoHealersBehind_ThenHealersChainHealing() {
        ArmyImpl army1 = new ArmyImpl(LancerImpl::new, 1).
                addBattleUnits(WarriorImpl::new, 1);
        ArmyImpl army2 = new ArmyImpl(VampireImpl::new, 1).
                addBattleUnits(HealerImpl::new, 2);
        boolean result = Battle.fight(army1, army2);

        assertTrue(result);
    }

    @Test
    void WarriorVersusHealerAndHealerDoesNotHealHimself() {
        ArmyImpl warrior = new ArmyImpl(WarriorImpl::new, 1);
        ArmyImpl healer = new ArmyImpl(HealerImpl::new, 1);

        assertTrue(Battle.fight(warrior, healer));
    }

    @DisplayName("different weapons equipped by Healer")
    @ParameterizedTest(name = "equipped {0}")
    @MethodSource({"equipWeapon"})
    void EquipDifferentWeaponsOnWarriorAndVerifyItsStats (Weapon weapon, int expectedHealth,
                                                          int expectedAttack, int expectedHealPower) {
        healer.equipWeapon(weapon);

        assertEquals(expectedHealth, healer.getHealth());
        assertEquals(expectedAttack, healer.getAttack());
        assertEquals(expectedHealPower, healer.getHealPower());
    }

    static Stream<Arguments> equipWeapon() {
        return Stream.of(
                arguments(WeaponType.SWORD, 65, 2, 2),
                arguments(WeaponType.SHIELD, 80, 0, 2),
                arguments(WeaponType.GREAT_AXE, 45, 5, 2),
                arguments(WeaponType.KATANA, 40, 6, 2),
                arguments(WeaponType.MAGIC_WAND, 90, 3, 5),
                arguments(Weapon.builder().healthStat(-100).attackStat(-100).defenceStat(-100)
                        .vampirismStat(-100).healPowerStat(-100).build(), -40, 0, 0));
    }
}