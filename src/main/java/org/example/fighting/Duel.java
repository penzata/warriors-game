package org.example.fighting;

import org.example.battleunits.Warrior;

public class Duel {

    private Duel() {
    }

    /**
     * @param warrior1 first warrior (first warrior always attacks first)
     * @param warrior2 second warrior
     * @return TRUE if the first warrior is alive (and thus the other one is not anymore), else - FALSE
     */
    static boolean fight(Warrior warrior1, Warrior warrior2) {
        while (warrior1.isAlive() && warrior2.isAlive()) {
            warrior1.hits(warrior2);
            if(warrior2.isAlive()) {
                warrior2.hits(warrior1);
            }
        }
            return warrior1.isAlive();
    }

}
