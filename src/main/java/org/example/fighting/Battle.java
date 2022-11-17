package org.example.fighting;

import org.example.battleunits.Army;
import org.example.battleunits.Warrior;

import java.util.List;

public class Battle {

    private Battle() {
    }

    /**
     * @param army1 - attacking army (first warrior of first army always attacks first)
     * @param army2 - defending army
     * @return TRUE if there is someone still alive from the first army (and thus everyone is dead from the other), else - FALSE
     */
    static boolean fight(Army army1, Army army2) {
        List<Warrior> firstArmy = army1.getArmy();
        List<Warrior> secondArmy = army2.getArmy();
        boolean firstArmyWins = true;
        int attacker = 0;
        int defender = 0;

        while (attacker != firstArmy.size() && defender != secondArmy.size()) {
            boolean firstOpponentWins = Duel.fight(firstArmy.get(attacker), secondArmy.get(defender));
            firstArmyWins = firstOpponentWins;
            if (firstOpponentWins) {
                defender++;
            } else {
                attacker++;
            }
        }
        return firstArmyWins;
    }
}
