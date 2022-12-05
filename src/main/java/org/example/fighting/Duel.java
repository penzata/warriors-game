package org.example.fighting;

import lombok.extern.slf4j.Slf4j;
import org.example.battleunits.CombatUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class Duel {
    private static final Logger LOGGER = LoggerFactory.getLogger(Duel.class);

    private Duel() {
    }

    /**
     * @param attacker first warrior (first warrior always attacks first)
     * @param defender second warrior
     * @return TRUE if the first warrior is alive (and thus the other one is not anymore), else - FALSE
     */
    public static boolean fight(CombatUnit attacker, CombatUnit defender) {
        log.atDebug().log("{} vs. {}", attacker, defender);
        while (attacker.isAlive() && defender.isAlive()) {
            log.atTrace().log("{} hits --> {}", attacker, defender);
            attacker.hit(defender);
            if (defender.isAlive()) {
                log.atTrace().log("{} hits --> {}", defender, attacker);
                defender.hit(attacker);
            }
        }

        log.atDebug().log(() -> (attacker.isAlive() ? attacker : defender) + " won!!!");
        return attacker.isAlive();
    }

}
