package org.example.demo;

import org.example.battleunits.*;
import org.example.battleunits.weapons.Weapon;
import org.example.fighting.Battle;
import org.example.battleunits.weapons.WeaponType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void printVampireWithKatana() {
        Warrior vampire = Vampire.create();
        vampire.equipWeapon(WeaponType.KATANA);
        System.out.println(vampire);
    }

    @Test
    void RookieTesting() {
        Army army3 = new ArmyImpl(Vampire::create, 2)
                .addBattleUnits(Rookie::new, 2);
        army3.equipWarriorAtPosition(0, WeaponType.KATANA);
        army3.equipWarriorAtPosition(1, WeaponType.KATANA);
        army3.equipWarriorAtPosition(2, WeaponType.SHIELD);
        Army army4 = new ArmyImpl(Warrior::create, 1)
                .addBattleUnits(Defender::create, 2);
        army4.equipWarriorAtPosition(0, WeaponType.KATANA);
        army4.equipWarriorAtPosition(1, WeaponType.SHIELD);
        army4.equipWarriorAtPosition(2, WeaponType.SHIELD);

        Battle.straightFight(army3, army4);
    }

    @Test
    void TrialForBattle11() {
        ArmyImpl army1 = new ArmyImpl(Defender::create, 5)
                .addBattleUnits(Vampire::create, 6)
                .addBattleUnits(Warrior::create, 7);
        ArmyImpl army2 = new ArmyImpl(Warrior::create, 6)
                .addBattleUnits(Defender::create, 6)
                .addBattleUnits(Vampire::create, 6);

        boolean fightResult = Battle.fight(army1, army2);

        assertFalse(fightResult);
    }

    @Test
    void StraightBattle2WithWeapons() {
        Army army3 = new ArmyImpl(Vampire::create, 2)
                .addBattleUnits(Rookie::new, 2);
        army3.equipWarriorAtPosition(0, WeaponType.KATANA);
        army3.equipWarriorAtPosition(1, WeaponType.KATANA);
        army3.equipWarriorAtPosition(2, WeaponType.SWORD);
        Army army4 = new ArmyImpl(Warrior::create, 1)
                .addBattleUnits(Defender::create, 2);
        army4.equipWarriorAtPosition(0, WeaponType.KATANA);
        army4.equipWarriorAtPosition(1, WeaponType.SWORD);
        army4.equipWarriorAtPosition(2, WeaponType.SWORD);
        boolean result = Battle.straightFight(army3, army4);

        assertTrue(result);
    }

    @Test
    void Battle3WithWeapons() {
        //battle3 with weapons
        Army army5 = new ArmyImpl(Defender::create, 2);
        army5.equipWarriorAtPosition(0, WeaponType.KATANA);
        army5.equipWarriorAtPosition(1, WeaponType.KATANA);
        Army army6 = new ArmyImpl(Knight::create, 1)
                .addBattleUnits(Vampire::create, 1);
        army6.equipWarriorAtPosition(0, WeaponType.KATANA);
        army6.equipWarriorAtPosition(1, WeaponType.KATANA);

        assertFalse(Battle.fight(army5, army6));

    }

    @Test
    @DisplayName("testing for miscellaneous stuff")
    void stuff() {
        Weapon shield = WeaponType.SHIELD;
        Weapon katana = WeaponType.KATANA;

        Knight knight = Knight.create();
        Defender defender = Defender.create();
        knight.equipWeapon(shield).equipWeapon(shield);
        defender.equipWeapon(shield);
        Vampire vampire = Vampire.create();
        vampire.equipWeapon(katana);


        System.out.println(knight);
        System.out.println(defender);
        System.out.println(vampire);

        Weapon customWeapon = Weapon.builder().healthStat(-10).attackStat(5).vampirismStat(40).build();
    }
}