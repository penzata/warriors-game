package org.example.fighting;

import lombok.extern.slf4j.Slf4j;
import org.example.battleunits.Army;
import org.example.battleunits.CombatUnit;

import java.util.Iterator;

@Slf4j
public class Battle {
    private static final String AFTER_THE_BATTLE = "- after the battle: ";

    private Battle() {
    }

    /**
     * @param army1 - attacking army (first warrior of first army always attacks first)
     * @param army2 - defending army
     * @return TRUE if there is someone still alive from the first army (and thus everyone is dead from the other), else - FALSE
     */
    public static boolean fight(Army army1, Army army2) {
        army1.moveUnits();
        army2.moveUnits();
        log.atDebug().log("Normal Fight!!!");
        log.atDebug().log("First Army's lineup: {}", army1);
        log.atDebug().log("Second Army's lineup: {}", army2);
        Iterator<CombatUnit> attacker = army1.getAliveUnit();
        Iterator<CombatUnit> defender = army2.getAliveUnit();

        while (attacker.hasNext() && defender.hasNext()) {
            Duel.fight(attacker.next(), defender.next());
            army1.isEveryoneAlive();
            army2.isEveryoneAlive();
            attacker = army1.getAliveUnit();
            defender = army2.getAliveUnit();
        }
        log.atDebug().log("{}{}", AFTER_THE_BATTLE, army1);
        log.atDebug().log("{}{}", AFTER_THE_BATTLE, army2);
        Iterator<CombatUnit> finalAttacker = attacker;
        log.atDebug().log(() -> (finalAttacker.hasNext() ? "First" : "Second") + " army won!!!\n");
        return finalAttacker.hasNext();
    }

    public static boolean straightFight(Army army1, Army army2) {
        army1.moveUnits();
        army2.moveUnits();
        log.atDebug().log("Straight Fight!!!");
        log.atDebug().log("First Army's lineup: {}", army1);
        log.atDebug().log("Second Army's lineup: {}", army2);
        int roundCount = 1;
        boolean res;

        while (true) {
            Iterator<CombatUnit> it1 = army1.nextInLine();
            Iterator<CombatUnit> it2 = army2.nextInLine();

            if (!it1.hasNext()) {
                res = false;
                break;
            }
            if (!it2.hasNext()) {
                res = true;
                break;
            }
            log.atDebug().log("\nRound {}", roundCount++);
            while (it1.hasNext() && it2.hasNext()) {
                Duel.fight(it1.next(), it2.next());
                army1.isEveryoneAlive();
                army2.isEveryoneAlive();
            }
        }
        log.atDebug().log("{}{}", AFTER_THE_BATTLE, army1);
        log.atDebug().log("{}{}", AFTER_THE_BATTLE, army2);
        log.atDebug().log(() -> (res ? "First" : "Second") + " army won!!!\n");
        return res;
    }

}

