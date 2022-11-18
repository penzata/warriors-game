package org.example.battleunits;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArmyTest {

    private Army testArmy;


    @BeforeEach
    void startUp() {
        testArmy = new Army();
    }

    @Test
    @DisplayName("testing Army initialization")
    void init() {
        testArmy.addBattleUnits(Knight::new, 2);
        testArmy.addBattleUnits(Warrior::new, 1);
        testArmy.addBattleUnits(Knight::new, 3);
        testArmy.addBattleUnits(Defender::new, 2);
        testArmy.addBattleUnits(Defender::new, 4);
        testArmy.addBattleUnits(Warrior::new, 1);

        System.out.println(testArmy);
    }

}