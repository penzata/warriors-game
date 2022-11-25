package org.example.battleunits.characteristics;

public interface Vampirism extends Health {

    int getVampirism();

    /**
     * @param selfHealing - vampire's self-healing (restores health by 50% of the dealt damage).
     */
    void vampirism(int selfHealing);
}
