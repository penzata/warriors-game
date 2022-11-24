package org.example.fighting;

import org.example.battleunits.units.WarriorUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Duel {
    private static final Logger LOGGER = LoggerFactory.getLogger(Duel.class);

    private Duel() {
    }

    /**
     * @param attacker first warrior (first warrior always attacks first)
     * @param defender second warrior
     * @return TRUE if the first warrior is alive (and thus the other one is not anymore), else - FALSE
     */
    public static boolean fight(WarriorUnit attacker, WarriorUnit defender) {
        String lineSeparator = System.getProperty("line.separator");
        while (attacker.isAlive() && defender.isAlive()) {
            LOGGER.info("attacker hits ------>");
            attacker.hit(defender);
            LOGGER.info("defender health after getting hit: {}", defender.getHealth());
            if (defender.isAlive()) {
                LOGGER.info("defender hits ------>");
                defender.hit(attacker);
                LOGGER.info("attacker health after getting hit: {}{}", attacker.getHealth(), lineSeparator);
            }
        }
        return attacker.isAlive();
    }

}
