package org.example.demo;

import org.example.battleunits.*;
import org.example.fighting.Battle;
import org.example.weapons.Weapon;
import org.example.weapons.WeaponType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MainTest {

    @Test
    void print() {
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

        assertEquals(true, result);
    }

    @Test
    void stuff() {
        Vampire vampire = Vampire.create();
        vampire.getVampirism();
        Lancer lancer = Lancer.create();
        lancer.getPiercingAttack();
        Healer healer = Healer.create();
        healer.getHealPower();
        Warrior healer2 = Healer.create();
    }

}