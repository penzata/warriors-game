package org.example.battleunits;

import org.example.battleunits.weapons.Weapon;
import org.example.battleunits.weapons.WeaponFactory;
import org.example.battleunits.weapons.WeaponImpl;
import org.example.fighting.Battle;
import org.example.fighting.Duel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class HealerTest {
    private Healer healer;

    static Stream<Arguments> equipWeapon() {
        return Stream.of(
                arguments(WeaponFactory.SWORD, 65, 2, 2),
                arguments(WeaponFactory.SHIELD, 80, 0, 2),
                arguments(WeaponFactory.GREAT_AXE, 45, 5, 2),
                arguments(WeaponFactory.KATANA, 40, 6, 2),
                arguments(WeaponFactory.MAGIC_WAND, 90, 3, 5),
                arguments(WeaponImpl.builder()
                        .healthStat(-100)
                        .attackStat(-100)
                        .defenceStat(-100)
                        .vampirismStat(-100)
                        .healPowerStat(-100)
                        .build(), -40, 0, 0));
    }

    @BeforeEach
    void init() {
        healer = new Healer();
    }

    @Test
    void WarriorFightsWarriorWithHealerAndLoses() {
        Army warrior = new ArmyImpl(CombatUnitFactory::createWarrior, 1);
        Army warriorWithHealer = new ArmyImpl(CombatUnitFactory::createWarrior, 1)
                .addBattleUnits(CombatUnitFactory::createHealer, 1);

        assertFalse(Battle.fight(warrior, warriorWithHealer));
    }

    @Test
    void WhenLancerAttacksWarriorUnitWithTwoHealersBehind_ThenHealersChainHealing() {
        Army army1 = new ArmyImpl(CombatUnitFactory::createLancer, 1).
                addBattleUnits(CombatUnitFactory::createWarrior, 1);
        ArmyImpl army2 = new ArmyImpl(CombatUnitFactory::createVampire, 1).
                addBattleUnits(CombatUnitFactory::createHealer, 2);
        boolean result = Battle.fight(army1, army2);

        assertTrue(result);
    }

    @Test
    void OneWarriorArmyVersusOneHealerArmyAndHealerDoesNotHealHimself() {
        Army warrior = new ArmyImpl(CombatUnitFactory::createWarrior, 1);
        Army healer = new ArmyImpl(CombatUnitFactory::createHealer, 1);

        assertTrue(Battle.fight(warrior, healer));
    }

    @DisplayName("different weapons equipped by Healer")
    @ParameterizedTest(name = "equipped {0}")
    @MethodSource({"equipWeapon"})
    void EquipDifferentWeaponsOnWarriorAndVerifyItsStats(Weapon weapon, int expectedHealth,
                                                         int expectedAttack, int expectedHealPower) {
        healer.equipWeapon(weapon);

        assertEquals(expectedHealth, healer.getHealth());
        assertEquals(expectedAttack, healer.getAttack());
        assertEquals(expectedHealPower, healer.getHealPower());
    }

    @Test
    void HealerVsHealer() {
        CombatUnit secondHealer = CombatUnitFactory.createHealer();
        Duel.fight(healer, secondHealer);
    }

    @Test
    @DisplayName("testing for breaking infinite loop when defender and healer fights defender and healer")
    void ArmyDefenderWithHealerVSSecondArmyDefenderWithHealerAndFirstArmyWins() {
        Army army1 = new ArmyImpl(CombatUnitFactory::createDefender, 1)
                .addBattleUnits(CombatUnitFactory::createHealer, 1);
        Army army2 = new ArmyImpl(CombatUnitFactory::createDefender, 1)
                .addBattleUnits(CombatUnitFactory::createHealer, 1);

        assertTrue(Battle.fight(army1, army2));
    }
}