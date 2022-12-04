package org.example.battleunits;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class WarlordImplTest {

    @Test
    void OnlyOnWarlordCanBeAddedToTheArmy() {
        Army army = new ArmyImpl(CombatUnit::createWarlord, 3)
                .addBattleUnits(CombatUnit::createKnight, 1)
                .addBattleUnits(CombatUnit::createWarlord, 3)
                .addBattleUnits(CombatUnit::createHealer, 1);
        log.atInfo().log("only one warlord: {}", army);
    }

    @Test
    void ArmyRearrangedByWarlord() {
        ArmyImpl army1 = new ArmyImpl(CombatUnit::createDefender, 2)
                .addBattleUnits(CombatUnit::createVampire, 3)
                .addBattleUnits(CombatUnit::createHealer, 4)
                .addBattleUnits(CombatUnit::createWarlord, 4)
                .addBattleUnits(CombatUnit::createDefender, 4)
                .addBattleUnits(CombatUnit::createLancer, 3);
        army1.moveUnits();

        log.atDebug().log("after rearranging: {}", army1);
    }
}