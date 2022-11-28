package org.example.battleunits;

import org.example.battleunits.characteristics.Attack;
import org.example.battleunits.characteristics.Defence;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Defender extends Warrior implements Defence {
    private final static Logger LOGGER = LoggerFactory.getLogger(Defender.class);
    private final int defence;

    Defender() {
        super(60, 3);
        this.defence = 2;
    }

    Defender(int health, int attack, int defence) {
        super(health, attack);
        this.defence = defence;
    }

    /**
     * @param defender - copy constructor
     */
    Defender(@NotNull Defender defender) {
        super(defender);
        this.defence = defender.defence;
    }

    @Override
    public void receiveDamage(Attack damageDealer) {
        LOGGER.debug("defender's health before taking damage: {}", getHealth());
        super.receiveDamage(() -> Math.max(0, damageDealer.getAttack() - getDefence()));
        LOGGER.debug("defender's health after deflecting damage({}): {}", getDefence(), getHealth());
    }

    @Override
    public int getDefence() {
        return this.defence;
    }
}
