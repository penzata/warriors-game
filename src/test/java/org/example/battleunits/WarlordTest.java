package org.example.battleunits;

import lombok.extern.slf4j.Slf4j;
import org.example.battleunits.weapons.Weapon;
import org.example.battleunits.weapons.WeaponFactory;
import org.example.battleunits.weapons.WeaponImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Slf4j
class WarlordTest {

    @Test
    void OnlyOnWarlordCanBeAddedToTheArmy() {
        Army army = new ArmyImpl(CombatUnitFactory::createWarlord, 3)
                .addBattleUnits(CombatUnitFactory::createKnight, 1)
                .addBattleUnits(CombatUnitFactory::createWarlord, 3)
                .addBattleUnits(CombatUnitFactory::createHealer, 1);
        log.atInfo().log("only one warlord: {}", army);
    }

    @Test
    void ArmyRearrangedByWarlord() {
        ArmyImpl army1 = new ArmyImpl(CombatUnitFactory::createDefender, 2)
                .addBattleUnits(CombatUnitFactory::createVampire, 3)
                .addBattleUnits(CombatUnitFactory::createHealer, 4)
                .addBattleUnits(CombatUnitFactory::createWarlord, 4)
                .addBattleUnits(CombatUnitFactory::createDefender, 4)
                .addBattleUnits(CombatUnitFactory::createLancer, 3);
        army1.moveUnits();
        army1.moveUnits();
        army1.moveUnits();

        log.atDebug().log("after rearranging: {}", army1);
    }

    @DisplayName("different weapons equipped by Warlord")
    @ParameterizedTest(name = "equipped {0}")
    @MethodSource({"equipWeapon"})
    void EquipDifferentWeaponsOnWarriorAndVerifyItsStats(Weapon weapon, int expectedHealth,
                                                         int expectedAttack, int expectedDefence) {
        Warlord warlord = new Warlord();
        warlord.equipWeapon(weapon);

        assertEquals(expectedHealth, warlord.getHealth());
        assertEquals(expectedAttack, warlord.getAttack());
        assertEquals(expectedDefence, warlord.getDefence());
    }

    static Stream<Arguments> equipWeapon() {
        return Stream.of(
                arguments(WeaponFactory.SWORD, 105, 6, 2),
                arguments(WeaponFactory.SHIELD, 120, 3, 4),
                arguments(WeaponFactory.GREAT_AXE, 85, 9, 0),
                arguments(WeaponFactory.KATANA, 80, 10, 0),
                arguments(WeaponFactory.MAGIC_WAND, 130, 7, 2),
                arguments(WeaponImpl.builder()
                        .healthStat(-100).attackStat(-100).defenceStat(-100)
                        .vampirismStat(-100).healPowerStat(-100)
                        .build(), -0, 0, 0));
    }
}