package org.example.battleunits;

import lombok.extern.slf4j.Slf4j;
import org.example.battleunits.weapons.Weapon;
import org.example.battleunits.weapons.WeaponFactory;
import org.example.battleunits.weapons.WeaponImpl;
import org.example.fighting.Battle;
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

@Slf4j
class VampireTest {
    Vampire vampire;
    Rookie rookie;

    static Stream<Arguments> equipWeapon() {
        return Stream.of(
                arguments(WeaponFactory.SWORD, 45, 6, 50),
                arguments(WeaponFactory.SHIELD, 60, 3, 50),
                arguments(WeaponFactory.GREAT_AXE, 25, 9, 60),
                arguments(WeaponFactory.KATANA, 20, 10, 100),
                arguments(WeaponFactory.MAGIC_WAND, 70, 7, 50),
                arguments(WeaponImpl.builder()
                        .healthStat(-100)
                        .attackStat(-100)
                        .defenceStat(-100)
                        .vampirismStat(-100)
                        .healPowerStat(-100)
                        .build(), -60, 0, 0));
    }

    @BeforeEach
    void init() {
        vampire = new Vampire();
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
        Army army1 = new ArmyImpl(CombatUnitFactory::createWarrior, 1);
        Army army2 = new ArmyImpl(CombatUnitFactory::createWarrior, 1)
                .addBattleUnits(CombatUnitFactory::createKnight, 2);

        assertFalse(Battle.fight(army1, army2));
    }

    @DisplayName("different weapons equipped by Vampire")
    @ParameterizedTest(name = "equipped {0}")
    @MethodSource({"equipWeapon"})
    void EquipDifferentWeaponsOnWarriorAndVerifyItsStats(Weapon weapon, int expectedHealth, int expectedAttack, int expectedVampirism) {
        vampire.equipWeapon(weapon);

        assertEquals(expectedHealth, vampire.getHealth());
        assertEquals(expectedAttack, vampire.getAttack());
        assertEquals(expectedVampirism, vampire.getVampirism());
    }

    @Test
    void EquippingWeaponsProperly() {
        Vampire vampire = new Vampire();

        vampire.equipWeapon(WeaponFactory.KATANA).equipWeapon(WeaponFactory.SHIELD);

        log.atDebug().log("{} with katana and shield.", vampire);
        assertEquals(40, vampire.getHealth());
        assertEquals(9, vampire.getAttack());
        assertEquals(100, vampire.getVampirism());
    }
}