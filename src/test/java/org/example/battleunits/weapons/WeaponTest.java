package org.example.battleunits.weapons;

import lombok.extern.slf4j.Slf4j;
import org.example.battleunits.*;
import org.example.fighting.Duel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class WeaponTest {

    @Test
    void SomePrints() {
        CombatUnit vampire = CombatUnitFactory.createVampire();
        vampire.equipWeapon(WeaponFactory.KATANA);
        log.atDebug().log("{} with katana: ", vampire);

        Weapon customWeapon = WeaponImpl.builder()
                .healthStat(-10).attackStat(5)
                .vampirismStat(40)
                .build();
        log.atDebug().log("{}", customWeapon.getCharacteristics());
        log.atDebug().log("{}", WeaponFactory.KATANA.getCharacteristics());
    }

    @Test
    void RookieDiesFromWeaponOverEquipment() {
        Rookie rookie = new Rookie();

        rookie.equipWeapon(WeaponFactory.SWORD);
        log.atDebug().log("equip sword -> {}", rookie);
        rookie.equipWeapon(WeaponFactory.KATANA);
        log.atDebug().log("equip katana -> {}", rookie);
        rookie.equipWeapon(WeaponFactory.GREAT_AXE);
        log.atDebug().log("equip great axe -> {}", rookie);
        rookie.equipWeapon(WeaponFactory.GREAT_AXE);
        log.atDebug().log("equip great axe -> {}", rookie);
        rookie.equipWeapon(WeaponFactory.KATANA);
        log.atDebug().log("equip katana -> {}", rookie);
        rookie.equipWeapon(WeaponFactory.MAGIC_WAND);
        log.atDebug().log("equip magic wand -> {}", rookie);
        rookie.equipWeapon(WeaponFactory.SHIELD);
        log.atDebug().log("equip shield -> {}", rookie);
        rookie.equipWeapon(WeaponFactory.KATANA);
        log.atDebug().log("equip katana -> {}", rookie);

        assertEquals(-15, rookie.getHealth());
    }

    @Test
    void DefenderWithShieldAndMagicWandFightsVampireWithKatanaAndLoses() {
        CombatUnit defender = CombatUnitFactory.createDefender();
        defender.equipWeapon(WeaponFactory.SHIELD)
                .equipWeapon(WeaponFactory.MAGIC_WAND);
        CombatUnit vampire = CombatUnitFactory.createVampire();
        vampire.equipWeapon(WeaponFactory.KATANA);

        assertFalse(Duel.fight(defender, vampire));
    }

    @Test
    void DefenderWithShieldAndMagicWandAndSwordFightsVampireWithKatanaAndFinallyWins() {
        CombatUnit defender = CombatUnitFactory.createDefender();
        defender.equipWeapon(WeaponFactory.SHIELD)
                .equipWeapon(WeaponFactory.MAGIC_WAND)
                .equipWeapon(WeaponFactory.SWORD);
        CombatUnit vampire = CombatUnitFactory.createVampire();
        vampire.equipWeapon(WeaponFactory.KATANA);

        assertTrue(Duel.fight(defender, vampire));
    }

    @Test
    @DisplayName("weapon fight between defender and warrior," +
            " after defender wins, he picks another weapon" +
            " and his health is raised only with last weapon's health stat points")
    void DefenderFightsWarriorWithWeaponsAndWins() {
        CombatUnit defender = CombatUnitFactory.createDefender();
        defender.equipWeapon(WeaponFactory.SWORD);
        CombatUnit knight = CombatUnitFactory.createKnight();
        knight.equipWeapon(WeaponFactory.GREAT_AXE);
        boolean result = Duel.fight(defender, knight);

        assertTrue(result);

        int healthBeforeEquipment = defender.getHealth();
        defender.equipWeapon(WeaponFactory.SHIELD);

        assertEquals(25, healthBeforeEquipment + 20);
        log.atDebug().log("{}", defender);
    }

}