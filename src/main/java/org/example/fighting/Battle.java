package org.example.fighting;

import org.example.battleunits.Army;

public class Battle {

    private Battle() {
    }

    /**
     * @param army1 - attacking army (first warrior of first army always attacks first)
     * @param army2 - defending army
     * @return TRUE if there is someone still alive from the first army (and thus everyone is dead from the other), else - FALSE
     */
    public static boolean fight(Army army1, Army army2) {
        var attacker = army1.getAliveUnit();
        var defender = army2.getAliveUnit();

        while(attacker.hasNext() && defender.hasNext()) {
            Duel.fight(attacker.next(), defender.next());
        }
        return attacker.hasNext();
    }
}
