package org.example.main;

import org.example.battleunits.Army;
import org.example.battleunits.ArmyUnit;
import org.example.battleunits.CombatUnit;
import org.example.battleunits.Warrior;
import org.example.fighting.Battle;
import org.example.weapons.Weapon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class MainTest {

    @Test
    void TrialForBattle21() {
        ArmyUnit army5 = new Army(CombatUnit::newLancer, 7)
                .addBattleUnits(CombatUnit::newVampire, 3)
                .addBattleUnits(CombatUnit::newHealer, 1)
                .addBattleUnits(CombatUnit::newWarrior, 4)
                .addBattleUnits(CombatUnit::newHealer, 1)
                .addBattleUnits(CombatUnit::newDefender, 2);
        ArmyUnit army6 = new Army(CombatUnit::newWarrior, 4)
                .addBattleUnits(CombatUnit::newDefender, 4)
                .addBattleUnits(CombatUnit::newHealer, 1)
                .addBattleUnits(CombatUnit::newVampire, 6)
                .addBattleUnits(CombatUnit::newLancer, 4);
        boolean straightFightResult = Battle.straightFight(army5, army6);

        assertFalse(straightFightResult);
    }

    @Test
    void print() {
        CombatUnit vampire = CombatUnit.newVampire();
        vampire.equipWeapon(Weapon.katana());
        System.out.println(vampire);
        CombatUnit lancer = CombatUnit.newLancer();
    }

    @Test
    void RookieTesting() {
        ArmyUnit army3 = new Army(CombatUnit::newVampire, 2)
                .addBattleUnits(Rookie::new, 2);
        army3.equipWarriorAtPosition(0, Weapon.katana());
        army3.equipWarriorAtPosition(1, Weapon.katana());
        army3.equipWarriorAtPosition(2, Weapon.shield());
        ArmyUnit army4 = new Army(CombatUnit::newWarrior, 1)
                .addBattleUnits(CombatUnit::newDefender, 2);
        army4.equipWarriorAtPosition(0, Weapon.katana());
        army4.equipWarriorAtPosition(1, Weapon.shield());
        army4.equipWarriorAtPosition(2, Weapon.shield());

        Battle.straightFight(army3, army4);
    }

    @Test
    void TrialForBattle11() {
        Army army1 = new Army(CombatUnit::newDefender, 5)
                .addBattleUnits(CombatUnit::newVampire, 6)
                .addBattleUnits(CombatUnit::newWarrior, 7);
        Army army2 = new Army(CombatUnit::newWarrior, 6)
                .addBattleUnits(CombatUnit::newDefender, 6)
                .addBattleUnits(CombatUnit::newVampire, 6);

        boolean fightResult = Battle.fight(army1, army2);

        assertFalse(fightResult);
    }

    class Rookie extends Warrior {

        @Override
        public int getAttack() {
            return 1;
        }
    }

}