package org.example.fighting;

import org.example.battleunits.units.WarriorUnit;
import org.example.main.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Duel {

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
            if (defender.isAlive()) {
                defender.hit(attacker);
            }
        }
        return attacker.isAlive();
    }

}
