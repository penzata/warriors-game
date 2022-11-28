package org.example.fighting;

import org.example.battleunits.ArmyUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Battle {

    private static final Logger LOGGER = LoggerFactory.getLogger(Battle.class);

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
        String lineSeparator = System.getProperty("line.separator");
        int roundCount = 1;

        while (true) {
            var it1 = army1.nextInLine();
            var it2 = army2.nextInLine();

            if (!it1.hasNext()) {
                return false;
            }
            if (!it2.hasNext()) {
                return true;
            }

            LOGGER.debug("Straight Fight Round: {}{}", roundCount, lineSeparator);
            LOGGER.debug("Army1's lineup: {}{}", lineSeparator, army1);
            LOGGER.debug("Army2's lineup: {}{}{}", lineSeparator, army2, lineSeparator);

            while (it1.hasNext() && it2.hasNext()) {
                Duel.fight(it1.next(), it2.next());
            }
            LOGGER.debug("Army1 before removing fallen units from battlefield: {}{}", lineSeparator, army1);
            LOGGER.debug("Army2 before removing fallen units from battlefield: {}{}", lineSeparator, army2);
            army1.removeDeadBodies();
            army2.removeDeadBodies();
            LOGGER.debug("end of round!!!{}{}{}", lineSeparator, lineSeparator, lineSeparator);
            roundCount++;
        }
    }

}

