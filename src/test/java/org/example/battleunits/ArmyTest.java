package org.example.battleunits;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArmyTest {

    private Army testArmy;
    private List<Warrior> unmodifiableArmy;

    @BeforeEach
    void startUp() {
        testArmy = new Army();
        unmodifiableArmy = testArmy.getArmy();
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

        assertEquals(15, unmodifiableArmy.size());

        unmodifiableArmy.forEach(System.out::println);
    }

    @Test
    @DisplayName("testing read-only army collection")
    void ChangingCollectionThrowsException() {
        Warrior newWarrior = new Warrior();
        Warrior warrior = unmodifiableArmy.get(0);

        assertThrows(UnsupportedOperationException.class, () -> unmodifiableArmy.add(newWarrior));
        assertThrows(UnsupportedOperationException.class, () -> unmodifiableArmy.remove(warrior));
        assertThrows(UnsupportedOperationException.class, () -> unmodifiableArmy.set(1, newWarrior));
    }
}