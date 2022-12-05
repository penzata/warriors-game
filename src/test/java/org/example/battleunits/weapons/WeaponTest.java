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
        Vampire vampire = CombatUnit.createVampire();
        vampire.equipWeapon(WeaponType.KATANA);
        log.atDebug().log("{} with katana: ", vampire);

        Weapon customWeapon = Weapon.builder()
                .setHealthStat(-10).setAttackStat(5)
                .setVampirismStat(40)
                .build();
        log.atDebug().log("{} chars: {}", customWeapon, customWeapon.getCharacteristics());
    }

    @Test
    void EquippingWeaponsProperly() {
        Knight knight = CombatUnit.createKnight();
        Defender defender = CombatUnit.createDefender();
        Vampire vampire = CombatUnit.createVampire();

        knight.equipWeapon(WeaponType.SHIELD).equipWeapon(WeaponType.SHIELD);
        defender.equipWeapon(WeaponType.SHIELD).equipWeapon(WeaponType.KATANA);
        vampire.equipWeapon(WeaponType.KATANA).equipWeapon(WeaponType.SHIELD);

        log.atDebug().log("{} with two shields.", knight);
        assertEquals(90, knight.getHealth());
        assertEquals(5, knight.getAttack());
        log.atDebug().log("{} with shield and katana.", defender);
        assertEquals(60, defender.getHealth());
        assertEquals(8, defender.getAttack());
        assertEquals(0, defender.getDefence());
        log.atDebug().log("{} with katana and shield.", vampire);
        assertEquals(40, vampire.getHealth());
        assertEquals(9, vampire.getAttack());
        assertEquals(100, vampire.getVampirism());
    }

    @Test
    void RookieDiesFromWeaponOverEquipment() {
        Rookie rookie = new Rookie();

        rookie.equipWeapon(WeaponType.SWORD);
        log.atDebug().log("equip sword -> {}", rookie);
        rookie.equipWeapon(WeaponType.KATANA);
        log.atDebug().log("equip katana -> {}", rookie);
        rookie.equipWeapon(WeaponType.GREAT_AXE);
        log.atDebug().log("equip great axe -> {}", rookie);
        rookie.equipWeapon(WeaponType.GREAT_AXE);
        log.atDebug().log("equip great axe -> {}", rookie);
        rookie.equipWeapon(WeaponType.KATANA);
        log.atDebug().log("equip katana -> {}", rookie);
        rookie.equipWeapon(WeaponType.MAGIC_WAND);
        log.atDebug().log("equip magic wand -> {}", rookie);
        rookie.equipWeapon(WeaponType.SHIELD);
        log.atDebug().log("equip shield -> {}", rookie);
        rookie.equipWeapon(WeaponType.KATANA);
        log.atDebug().log("equip katana -> {}", rookie);

        assertEquals(-15, rookie.getHealth());
    }

    @Test
    void DefenderWithShieldAndMagicWandFightsVampireWithKatanaAndLoses() {
        Defender defender = CombatUnit.createDefender();
        defender.equipWeapon(WeaponType.SHIELD)
                .equipWeapon(WeaponType.MAGIC_WAND);
        Vampire vampire = CombatUnit.createVampire();
        vampire.equipWeapon(WeaponType.KATANA);

        assertFalse(Duel.fight(defender, vampire));
    }

    @Test
    void DefenderWithShieldAndMagicWandAndSwordFightsVampireWithKatanaAndFinallyWins() {
        Defender defender = CombatUnit.createDefender();
        defender.equipWeapon(WeaponType.SHIELD)
                .equipWeapon(WeaponType.MAGIC_WAND)
                .equipWeapon(WeaponType.SWORD);
        Vampire vampire = CombatUnit.createVampire();
        vampire.equipWeapon(WeaponType.KATANA);

        assertTrue(Duel.fight(defender, vampire));
    }

    @Test
    @DisplayName("weapon fight between defender and warrior," +
            " after defender wins, he picks another weapon" +
            " and his health is raised only with last weapon's health stat points")
    void DefenderFightsWarriorWithWeaponsAndWins() {
        Defender defender = CombatUnit.createDefender();
        defender.equipWeapon(WeaponType.SWORD);
        Knight knight = CombatUnit.createKnight();
        knight.equipWeapon(WeaponType.GREAT_AXE);
        boolean result = Duel.fight(defender, knight);

        assertTrue(result);

        int healthBeforeEquipment = defender.getHealth();
        defender.equipWeapon(WeaponType.SHIELD);

        assertEquals(25, healthBeforeEquipment + 20);
        log.atDebug().log("{}", defender);
    }

}