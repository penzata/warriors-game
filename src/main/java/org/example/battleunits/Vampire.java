package org.example.battleunits;

import org.example.battleunits.characteristics.Vampirism;
import org.example.battleunits.subsidiary.CanReceiveDamage;
import org.example.battleunits.subsidiary.DealtDamageAwareness;


public interface Vampire extends Warrior, Vampirism, DealtDamageAwareness {

    /**
     * @return Vampire object with default health(40), attack(4) & vampirism(50%).
     */
    static Vampire create() {
        return new VampireImpl();
    }

    @Override
    default void hit(CanReceiveDamage opponent) {
        int damageDealt = getDealtDamage(opponent);
        final int PERCENTS = 100;
        int healingPoints = (int) (damageDealt * getVampirism() / PERCENTS);
        vampirism(healingPoints);
    }

    /**
     * @param selfHealing - vampire's self-healing (restores health by 50% of the dealt damage).
     */
    default void vampirism(int selfHealing) {
        healedBy(selfHealing);
    }
}
