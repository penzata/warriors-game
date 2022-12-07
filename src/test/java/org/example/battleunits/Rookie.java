package org.example.battleunits;


import lombok.extern.slf4j.Slf4j;
import org.example.battleunits.subsidiary.CombatUnitType;
import org.example.battleunits.weapons.Weapon;
import org.example.battleunits.weapons.WeaponFactory;
import org.example.battleunits.weapons.WeaponImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Slf4j
public class Rookie extends CombatUnitImpl {
    Rookie rookie;

    public Rookie() {
        super(50, 1);
    }

    static Stream<Arguments> equipWeapon() {
        return Stream.of(
                arguments(WeaponFactory.SWORD, 55, 3),
                arguments(WeaponFactory.SHIELD, 70, 0),
                arguments(WeaponFactory.GREAT_AXE, 35, 6),
                arguments(WeaponFactory.KATANA, 30, 7),
                arguments(WeaponFactory.MAGIC_WAND, 80, 4),
                arguments(WeaponImpl.builder()
                        .healthStat(-100).attackStat(-100).defenceStat(-100)
                        .vampirismStat(-100).healPowerStat(-100)
                        .build(), -50, 0, 0));
    }

    @BeforeEach
    void init() {
        this.rookie = new Rookie();
    }

    @DisplayName("different weapons equipped by Rookie")
    @ParameterizedTest(name = "equipped {0}")
    @MethodSource({"equipWeapon"})
    void EquipDifferentWeaponsOnRookieAndVerifyItsStats(Weapon weapon, int expectedHealth, int expectedAttack) {
        rookie.equipWeapon(weapon);

        assertEquals(expectedHealth, rookie.getHealth());
        assertEquals(expectedAttack, rookie.getAttack());
    }

    @Override
    public CombatUnitType getCombatUnitType() {
        return CombatUnitType.FIGHTER;
    }
}
