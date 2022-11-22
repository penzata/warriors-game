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
        while (attacker.isAlive() && defender.isAlive()) {
            attacker.hit(defender);
            LOGGER.info("Vampire health after getting hit: {}", defender.getHealth());
            if (defender.isAlive()) {
                defender.hit(attacker);
                LOGGER.info("Defender health after getting hit: {}", attacker.getHealth());
                LOGGER.info("vampire health after vampirism: {}", defender.getHealth());
            }
        }
        return attacker.isAlive();
    }

}
