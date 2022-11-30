package org.example.fighting;

import lombok.extern.slf4j.Slf4j;
import org.example.battleunits.Warrior;
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
    public static boolean fight(Warrior attacker, Warrior defender) {
        log.atDebug().log("{} vs. {}", attacker, defender);
        while (attacker.isAlive() && defender.isAlive()) {
            attacker.hit(defender);
            if (defender.isAlive()) {
                defender.hit(attacker);
            }
        }
        log.atDebug().log(() -> (attacker.isAlive() ? attacker : defender) + " won!!!");
        return attacker.isAlive();
    }

}
