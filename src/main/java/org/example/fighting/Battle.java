package org.example.fighting;

import lombok.extern.slf4j.Slf4j;
import org.example.battleunits.Army;

@Slf4j
public class Battle {

    private Battle() {
    }

    /**
     * @param army1 - attacking army (first warrior of first army always attacks first)
     * @param army2 - defending army
     * @return TRUE if there is someone still alive from the first army (and thus everyone is dead from the other), else - FALSE
     */
    public static boolean fight(Army army1, Army army2) {
        log.atDebug().log("Army1's lineup: \n{}", army1);
        log.atDebug().log("Army2's lineup: \n{}", army2);
        var attacker = army1.getAliveUnit();
        var defender = army2.getAliveUnit();

        while (attacker.hasNext() && defender.hasNext()) {
            Duel.fight(attacker.next(), defender.next());
        }
        log.atDebug().log("first army after battle: \n{}", army1);
        log.atDebug().log("second army after battle: \n{}", army2);
        log.atDebug().log(() -> (attacker.hasNext() ? "First" : "Second") + " army won!!!\n");
        return attacker.hasNext();
    }

    public static boolean straightFight(Army army1, Army army2) {
        log.atDebug().log("Straight Fight!!!");
        log.atDebug().log("Army1's lineup: {}", army1);
        log.atDebug().log("Army2's lineup: {}", army2);
        int roundCount = 1;
        boolean res;

        while (true) {
            var it1 = army1.nextInLine();
            var it2 = army2.nextInLine();

            if (!it1.hasNext()) {
                res = false;
                break;
            }
            if (!it2.hasNext()) {
                res = true;
                break;
            }
            log.atDebug().log("Round {}", roundCount++);
            while (it1.hasNext() && it2.hasNext()) {
                Duel.fight(it1.next(), it2.next());
            }
        }
        log.atDebug().log("first army after battle: {}", army1);
        log.atDebug().log("second army after battle: {}", army2);
        log.atDebug().log(() -> (res ? "First" : "Second") + " army won!!!\n");
        return res;
    }

}

