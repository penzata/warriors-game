package org.example.fighting;

import org.example.battleunits.Warrior;
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
    public static boolean fight(Warrior attacker, Warrior defender) {
        //next four variables are used for logging simplicity
        var attackerName = attacker.getClass().getSimpleName();
        var attackerHashCode = attacker.hashCode();
        var defenderName = defender.getClass().getSimpleName();
        var defenderHashCode = defender.hashCode();
        String lineSeparator = System.getProperty("line.separator");

        LOGGER.debug("Fight between {}({})[health:{}] and {}({})[health:{}]:", attackerName, attackerHashCode, attacker.getHealth(),
                defenderName, defenderHashCode, defender.getHealth());
        while (attacker.isAlive() && defender.isAlive()) {
            LOGGER.debug("{} hits(+{})  ------> ", attackerName, attacker.getAttack());
            attacker.hit(defender);
            LOGGER.debug("-> {}'s health after being hit: {}", defenderName, defender.getHealth());
            if (defender.isAlive()) {
                LOGGER.debug("{} hits(+{})  ------> ", defenderName, defender.getAttack());
                defender.hit(attacker);
                LOGGER.debug("-> {}'s health after being hit: {}", attackerName, attacker.getHealth());
            }
        }
        LOGGER.debug("Health after fight: {}({})[health:{}];  {}({})[health:{}]{}{}", attackerName, attackerHashCode, attacker.getHealth(),
                defenderName, defenderHashCode, defender.getHealth(), lineSeparator, lineSeparator);
        return attacker.isAlive();
    }

}
