package org.example.battleunits;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class WarlordImplTest {

    @Test
    void OnlyOnWarlordCanBeAddedToTheArmy() {
        Army army = new ArmyImpl(Warlord::create, 3)
                .addBattleUnits(Knight::create, 1)
                .addBattleUnits(Warlord::create, 3)
                .addBattleUnits(Healer::create, 1);
        log.atInfo().log("only one warlord: {}", army);
    }

    @Test
    void ArmyRearrangedByWarlord() {
        ArmyImpl army1 = new ArmyImpl(Defender::create, 2)
                .addBattleUnits(Vampire::create, 3)
                .addBattleUnits(Healer::create, 4)
                .addBattleUnits(Warlord::create, 4)
                .addBattleUnits(Defender::create, 4)
                .addBattleUnits(Lancer::create, 3);
        army1.moveUnits();
    }
}