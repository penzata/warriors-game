package org.example.demo;

import org.example.battleunits.Army;
import org.example.battleunits.ArmyImpl;
import org.example.battleunits.CombatUnit;
import org.example.battleunits.Rookie;
import org.example.battleunits.weapons.WeaponType;
import org.example.fighting.Battle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    @Test
    void RookieTesting() {
        Army army3 = new ArmyImpl(CombatUnit::createVampire, 2)
                .addBattleUnits(Rookie::new, 2);
        army3.equipCombatUnitAtPosition(0, WeaponType.KATANA);
        army3.equipCombatUnitAtPosition(1, WeaponType.KATANA);
        army3.equipCombatUnitAtPosition(2, WeaponType.SHIELD);
        Army army4 = new ArmyImpl(CombatUnit::createWarrior, 1)
                .addBattleUnits(CombatUnit::createDefender, 2);
        army4.equipCombatUnitAtPosition(0, WeaponType.KATANA);
        army4.equipCombatUnitAtPosition(1, WeaponType.SHIELD);
        army4.equipCombatUnitAtPosition(2, WeaponType.SHIELD);

        Battle.straightFight(army3, army4);
    }

    @Test
    void TrialForBattle11() {
        Army army1 = new ArmyImpl(CombatUnit::createDefender, 5)
                .addBattleUnits(CombatUnit::createVampire, 6)
                .addBattleUnits(CombatUnit::createWarrior, 7);
        Army army2 = new ArmyImpl(CombatUnit::createWarrior, 6)
                .addBattleUnits(CombatUnit::createDefender, 6)
                .addBattleUnits(CombatUnit::createVampire, 6);

        boolean fightResult = Battle.fight(army1, army2);

        assertFalse(fightResult);
    }

    @Test
    void StraightBattle2WithWeapons() {
        Army army3 = new ArmyImpl(CombatUnit::createVampire, 2)
                .addBattleUnits(Rookie::new, 2);
        army3.equipCombatUnitAtPosition(0, WeaponType.KATANA);
        army3.equipCombatUnitAtPosition(1, WeaponType.KATANA);
        army3.equipCombatUnitAtPosition(2, WeaponType.SWORD);
        Army army4 = new ArmyImpl(CombatUnit::createWarrior, 1)
                .addBattleUnits(CombatUnit::createDefender, 2);
        army4.equipCombatUnitAtPosition(0, WeaponType.KATANA);
        army4.equipCombatUnitAtPosition(1, WeaponType.SWORD);
        army4.equipCombatUnitAtPosition(2, WeaponType.SWORD);
        boolean result = Battle.straightFight(army3, army4);

        assertTrue(result);
    }

    @Test
    void Battle3WithWeapons() {
        //battle3 with weapons
        Army army5 = new ArmyImpl(CombatUnit::createDefender, 2);
        army5.equipCombatUnitAtPosition(0, WeaponType.KATANA);
        army5.equipCombatUnitAtPosition(1, WeaponType.KATANA);
        Army army6 = new ArmyImpl(CombatUnit::createKnight, 1)
                .addBattleUnits(CombatUnit::createVampire, 1);
        army6.equipCombatUnitAtPosition(0, WeaponType.KATANA);
        army6.equipCombatUnitAtPosition(1, WeaponType.KATANA);

        assertFalse(Battle.fight(army5, army6));

    }

}