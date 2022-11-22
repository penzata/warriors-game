package org.example.battleunits.units;

import org.example.battleunits.Defender;
import org.example.battleunits.characteristics.Attack;
import org.example.battleunits.characteristics.Defence;

public interface DefenderUnit extends WarriorUnit, Defence {

    /**
     * @return default Defender object with default health(60), attack(3) & defence(2).
     */
    static Defender newDefender() {
        return new Defender();
    }

    @Override
    default void receiveDamage(Attack damageDealer) {
        WarriorUnit.super.receiveDamage(() -> Math.max(0, damageDealer.getAttack() - getDefence()));
    }
}