package org.example.demo;

import org.example.battleunits.*;
import org.example.battleunits.weapons.WeaponFactory;
import org.example.battleunits.weapons.WeaponImpl;
import org.example.fighting.Battle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    @Test
    void RookieTesting() {
        Army army3 = new ArmyImpl(CombatUnitFactory::createVampire, 2)
                .addBattleUnits(Rookie::new, 2);
        army3.equipCombatUnitAtPosition(0, WeaponFactory.KATANA);
        army3.equipCombatUnitAtPosition(1, WeaponFactory.KATANA);
        army3.equipCombatUnitAtPosition(2, WeaponFactory.SHIELD);
        Army army4 = new ArmyImpl(CombatUnitFactory::createWarrior, 1)
                .addBattleUnits(CombatUnitFactory::createDefender, 2);
        army4.equipCombatUnitAtPosition(0, WeaponFactory.KATANA);
        army4.equipCombatUnitAtPosition(1, WeaponFactory.SHIELD);
        army4.equipCombatUnitAtPosition(2, WeaponFactory.SHIELD);

        Battle.straightFight(army3, army4);
    }

    @Test
    void TrialForBattle11() {
        Army army1 = new ArmyImpl(CombatUnitFactory::createDefender, 5)
                .addBattleUnits(CombatUnitFactory::createVampire, 6)
                .addBattleUnits(CombatUnitFactory::createWarrior, 7);
        Army army2 = new ArmyImpl(CombatUnitFactory::createWarrior, 6)
                .addBattleUnits(CombatUnitFactory::createDefender, 6)
                .addBattleUnits(CombatUnitFactory::createVampire, 6);

        boolean fightResult = Battle.fight(army1, army2);

        assertFalse(fightResult);
    }

    @Test
    void StraightBattle2WithWeapons() {
        Army army3 = new ArmyImpl(CombatUnitFactory::createVampire, 2)
                .addBattleUnits(Rookie::new, 2);
        army3.equipCombatUnitAtPosition(0, WeaponFactory.KATANA);
        army3.equipCombatUnitAtPosition(1, WeaponFactory.KATANA);
        army3.equipCombatUnitAtPosition(2, WeaponFactory.SWORD);
        Army army4 = new ArmyImpl(CombatUnitFactory::createWarrior, 1)
                .addBattleUnits(CombatUnitFactory::createDefender, 2);
        army4.equipCombatUnitAtPosition(0, WeaponFactory.KATANA);
        army4.equipCombatUnitAtPosition(1, WeaponFactory.SWORD);
        army4.equipCombatUnitAtPosition(2, WeaponFactory.SWORD);
        boolean result = Battle.straightFight(army3, army4);

        assertTrue(result);
    }

    @Test
    void Battle3WithWeapons() {
        //battle3 with weapons
        Army army5 = new ArmyImpl(CombatUnitFactory::createDefender, 2);
        army5.equipCombatUnitAtPosition(0, WeaponFactory.KATANA);
        army5.equipCombatUnitAtPosition(1, WeaponFactory.KATANA);
        Army army6 = new ArmyImpl(CombatUnitFactory::createKnight, 1)
                .addBattleUnits(CombatUnitFactory::createVampire, 1);
        army6.equipCombatUnitAtPosition(0, WeaponFactory.KATANA);
        army6.equipCombatUnitAtPosition(1, WeaponFactory.KATANA);

        assertFalse(Battle.fight(army5, army6));

    }

}