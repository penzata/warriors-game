package org.example.main;

import org.example.battleunits.*;
import org.example.fighting.Battle;
import org.example.weapons.Weapon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class MainTest {

    @Test
    void TrialForBattle21() {
        Army army5 = new ArmyImpl(Warrior::newLancer, 7)
                .addBattleUnits(Warrior::newVampire, 3)
                .addBattleUnits(Warrior::newHealer, 1)
                .addBattleUnits(Warrior::create, 4)
                .addBattleUnits(Warrior::newHealer, 1)
                .addBattleUnits(Warrior::newDefender, 2);
        Army army6 = new ArmyImpl(Warrior::create, 4)
                .addBattleUnits(Warrior::newDefender, 4)
                .addBattleUnits(Warrior::newHealer, 1)
                .addBattleUnits(Warrior::newVampire, 6)
                .addBattleUnits(Warrior::newLancer, 4);
        boolean straightFightResult = Battle.straightFight(army5, army6);

        assertFalse(straightFightResult);
    }

    @Test
    void print() {
        Warrior vampire = Warrior.newVampire();
        vampire.equipWeapon(Weapon.katana());
        System.out.println(vampire);
        Warrior lancer = Warrior.newLancer();
    }

    @Test
    void RookieTesting() {
        Army army3 = new ArmyImpl(Warrior::newVampire, 2)
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
    }

    @Test
    void TrialForBattle11() {
        ArmyImpl army1 = new ArmyImpl(Warrior::newDefender, 5)
                .addBattleUnits(Warrior::newVampire, 6)
                .addBattleUnits(Warrior::create, 7);
        ArmyImpl army2 = new ArmyImpl(Warrior::create, 6)
                .addBattleUnits(Warrior::newDefender, 6)
                .addBattleUnits(Warrior::newVampire, 6);

        boolean fightResult = Battle.fight(army1, army2);

        assertFalse(fightResult);
    }

    class Rookie extends WarriorImpl {

        @Override
        public int getAttack() {
            return 1;
        }
    }

    @Test
    void stuff() {
        Vampire vampire = Vampire.create();

    }

}