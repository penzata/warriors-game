package org.example.fighting;

import org.example.battleunits.units.ArmyUnit;

public class Battle {

    private Battle() {
    }

    /**
     * @param army1 - attacking army (first warrior of first army always attacks first)
     * @param army2 - defending army
     * @return TRUE if there is someone still alive from the first army (and thus everyone is dead from the other), else - FALSE
     */
    public static boolean fight(ArmyUnit army1, ArmyUnit army2) {
        var attacker = army1.getAliveUnit();
        var defender = army2.getAliveUnit();

        while (attacker.hasNext() && defender.hasNext()) {
            Duel.fight(attacker.next(), defender.next());
        }
        return attacker.hasNext();
    }

    public static boolean straightFight(ArmyUnit army1, ArmyUnit army2) {
        while (true) {
            var it1 = army1.iterator();
            var it2 = army2.iterator();

            if (!it1.hasNext()) {
                return false;
            }
            if (!it2.hasNext()) {
                return false;
            }

            while (it1.hasNext() && it2.hasNext()) {
                Duel.fight(it1.next(), it2.next());
            }
            army1.removeDeadBodies();
            army1.removeDeadBodies();
        }
    }
}
