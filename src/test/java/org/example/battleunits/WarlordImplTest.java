package org.example.battleunits;

import lombok.extern.slf4j.Slf4j;
import org.example.battleunits.weapons.Weapon;
import org.example.battleunits.weapons.WeaponType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Slf4j
class WarlordImplTest {

    @Test
    void OnlyOnWarlordCanBeAddedToTheArmy() {
        Army army = new ArmyImpl(CombatUnit::createWarlord, 3)
                .addBattleUnits(CombatUnit::createKnight, 1)
                .addBattleUnits(CombatUnit::createWarlord, 3)
                .addBattleUnits(CombatUnit::createHealer, 1);
        log.atInfo().log("only one warlord: {}", army);
    }

    @Test
    void ArmyRearrangedByWarlord() {
        ArmyImpl army1 = new ArmyImpl(CombatUnit::createDefender, 2)
                .addBattleUnits(CombatUnit::createVampire, 3)
                .addBattleUnits(CombatUnit::createHealer, 4)
                .addBattleUnits(CombatUnit::createWarlord, 4)
                .addBattleUnits(CombatUnit::createDefender, 4)
                .addBattleUnits(CombatUnit::createLancer, 3);
        army1.moveUnits();

        log.atDebug().log("after rearranging: {}", army1);
    }

    @DisplayName("different weapons equipped by Warlord")
    @ParameterizedTest(name = "equipped {0}")
    @MethodSource({"equipWeapon"})
    void EquipDifferentWeaponsOnWarriorAndVerifyItsStats(Weapon weapon, int expectedHealth,
                                                         int expectedAttack, int expectedDefence) {
        Warlord warlord = CombatUnit.createWarlord();
        warlord.equipWeapon(weapon);

        assertEquals(expectedHealth, warlord.getHealth());
        assertEquals(expectedAttack, warlord.getAttack());
        assertEquals(expectedDefence, warlord.getDefence());
    }

    static Stream<Arguments> equipWeapon() {
        return Stream.of(
                arguments(WeaponType.SWORD, 105, 6, 2),
                arguments(WeaponType.SHIELD, 120, 3, 4),
                arguments(WeaponType.GREAT_AXE, 85, 9, 0),
                arguments(WeaponType.KATANA, 80, 10, 0),
                arguments(WeaponType.MAGIC_WAND, 130, 7, 2),
                arguments(Weapon.builder()
                        .setHealthStat(-100).setAttackStat(-100).setDefenceStat(-100)
                        .setVampirismStat(-100).setHealPowerStat(-100)
                        .build(), -0, 0, 0));
    }
}