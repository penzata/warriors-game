package org.example.main;

import org.example.battleunits.*;
import org.example.fighting.Battle;
import org.example.weapons.Weapon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class MainTest {

    @Test
    void TrialForBattle21() {
        Army army5 = new ArmyImpl(Lancer::create, 7)
                .addBattleUnits(Vampire::create, 3)
                .addBattleUnits(Healer::create, 1)
                .addBattleUnits(Warrior::create, 4)
                .addBattleUnits(Healer::create, 1)
                .addBattleUnits(Defender::create, 2);
        Army army6 = new ArmyImpl(Warrior::create, 4)
                .addBattleUnits(Defender::create, 4)
                .addBattleUnits(Healer::create, 1)
                .addBattleUnits(Vampire::create, 6)
                .addBattleUnits(Lancer::create, 4);
        boolean straightFightResult = Battle.straightFight(army5, army6);

        assertFalse(straightFightResult);
    }

    @Test
    void print() {
        Warrior vampire = Vampire.create();
        vampire.equipWeapon(Weapon.katana());
        System.out.println(vampire);
        Warrior lancer = Lancer.create();
    }

/*    @Test
    void RookieTesting() {
        Army army3 = new ArmyImpl(Vampire::create, 2)
                .addBattleUnits(Rookie::new, 2);
        army3.equipWarriorAtPosition(0, Weapon.katana());
        army3.equipWarriorAtPosition(1, Weapon.katana());
        army3.equipWarriorAtPosition(2, Weapon.shield());
        Army army4 = new ArmyImpl(Warrior::create, 1)
                .addBattleUnits(Warrior::newDefender, 2);
        army4.equipWarriorAtPosition(0, Weapon.katana());
        army4.equipWarriorAtPosition(1, Weapon.shield());
        army4.equipWarriorAtPosition(2, Weapon.shield());

        Battle.straightFight(army3, army4);
    }*/

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