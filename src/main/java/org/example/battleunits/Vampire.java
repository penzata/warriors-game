package org.example.battleunits;

import org.example.battleunits.characteristics.Vampirism;
import org.example.battleunits.subsidiary.DealtDamageAwareness;


public interface Vampire extends Warrior, Vampirism, DealtDamageAwareness {

    /**
     * @return Vampire object with default health(40), attack(4) & vampirism(50%).
     */
    static Vampire create() {
        return new VampireImpl();
    }

    @Override
    default void hit(Warrior opponent) {
        int damageDealt = getDealtDamage(opponent);
        final int PERCENTS = 100;
        int healingPoints = (int) (damageDealt * getVampirism() / PERCENTS);
        vampirism(healingPoints);
    }

    void vampirism(int selfHealing);

}
