package org.example.battleunits;

import org.example.characteristics.Attack;
import org.example.characteristics.Defence;

public interface Defender extends Warrior, Defence {
    /**
     * @return Defender object with default health(60), attack(3) & defence(2).
     */
    static Defender create() {
        return new DefenderImpl();
    }

    @Override
    default void receiveDamage(Attack damageDealer) {
        Warrior.super.receiveDamage(() -> Math.max(0, damageDealer.getAttack() - getDefence()));
    }
}
