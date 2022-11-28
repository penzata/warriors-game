package org.example.battleunits;

import org.example.battleunits.weapons.CustomWeapon;
import org.example.battleunits.weapons.Weapon;
import org.example.fighting.Battle;
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
    private CombatUnit healer;

    @BeforeEach
    void init() {
        healer = CombatUnit.newHealer();
    }

    @Test
    void WarriorFightsWarriorWithHealerAndLoses() {
        Army warrior = new Army(Warrior::new, 1);
        Army warriorWithHealer = new Army(Warrior::new, 1)
                .addBattleUnits(Healer::new, 1);

        assertFalse(Battle.fight(warrior, warriorWithHealer));
    }

    @Test
    void WhenLancerAttacksWarriorUnitWithTwoHealersBehind_ThenHealersChainHealing() {
        Army army1 = new Army(Lancer::new, 1).
                addBattleUnits(Warrior::new, 1);
        Army army2 = new Army(Vampire::new, 1).
                addBattleUnits(Healer::new, 2);
        boolean result = Battle.fight(army1, army2);

        assertTrue(result);
    }

    @Test
    void WarriorVersusHealerAndHealerDoesNotHealHimself() {
        Army warrior = new Army(Warrior::new, 1);
        Army healer = new Army(Healer::new, 1);

        assertTrue(Battle.fight(warrior, healer));
    }

    @DisplayName("different weapons equipped by Healer")
    @ParameterizedTest(name = "equipped {0}")
    @MethodSource({"equipWeapon"})
    void EquipDifferentWeaponsOnWarriorAndVerifyItsStats (Weapon weapon, int expectedHealth,
                                                          int expectedAttack, int expectedHealPower) {
        healer.equipWeapon(weapon);
//TODO see why getHealPower() doesn't show
        assertEquals(expectedHealth, healer.getHealth());
        assertEquals(expectedAttack, healer.getAttack());
    }

    static Stream<Arguments> equipWeapon() {
        return Stream.of(
                arguments(Weapon.sword(), 65, 2, 2),
                arguments(Weapon.shield(), 80, 0, 2),
                arguments(Weapon.greatAxe(), 45, 5, 2),
                arguments(Weapon.katana(), 40, 6, 2),
                arguments(Weapon.magicWand(), 90, 3, 5),
                arguments(new CustomWeapon(-100, -100, -100, -100, -100), 0, 0, 0));
    }

}