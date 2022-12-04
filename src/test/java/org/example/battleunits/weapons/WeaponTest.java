package org.example.battleunits.weapons;

import lombok.extern.slf4j.Slf4j;
import org.example.battleunits.*;
import org.example.fighting.Duel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class WeaponTest {

    @Test
    void printVampireWithKatana() {
        Vampire vampire = CombatUnit.createVampire();
        vampire.equipWeapon(WeaponType.KATANA);
        log.atDebug().log("{} with katana: ", vampire);
    }

    @Test
    void EquippingWeaponsProperly() {
        Weapon shield = WeaponType.SHIELD;
        Weapon katana = WeaponType.KATANA;

        Knight knight = CombatUnit.createKnight();
        Defender defender = CombatUnit.createDefender();
        Vampire vampire = CombatUnit.createVampire();

        knight.equipWeapon(shield).equipWeapon(shield);
        defender.equipWeapon(shield).equipWeapon(katana);
        vampire.equipWeapon(katana).equipWeapon(shield);

        log.atDebug().log("{} with two shields: ", knight);
        assertEquals(90, knight.getHealth());
        assertEquals(5, knight.getAttack());
        log.atDebug().log("{} with shield and katana: ", defender);
        assertEquals(60, defender.getHealth());
        assertEquals(8, defender.getAttack());
        assertEquals(0, defender.getDefence());
        log.atDebug().log("{} with katana and shield: ", vampire);
        assertEquals(40, vampire.getHealth());
        assertEquals(9, vampire.getAttack());
        assertEquals(100, vampire.getVampirism());

        Weapon customWeapon = Weapon.builder()
                .setHealthStat(-10).setAttackStat(5)
                .setVampirismStat(40).setWeaponClass(WeaponClass.DEFENSIVE)
                .build();
        log.atDebug().log("{} chars: {}", customWeapon, customWeapon.getCharacteristics());
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

}