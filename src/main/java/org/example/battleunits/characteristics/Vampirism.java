package org.example.battleunits.characteristics;

public interface Vampirism extends Health {

    int getVampirism();

    /**
     * @param healingPoints - vampire self-healing (restores health by 50% of the dealt damage).
     */
    default void vampirism(int healingPoints) {
        vampireSelfHeal(healingPoints);
    }
    }
