package org.example.battleunits;

import org.example.battleunits.characteristics.Attack;
import org.example.battleunits.characteristics.Defence;

public interface Defender extends CombatUnit, Defence {

    @Override
    default void receiveDamage(Attack damageDealer) {
        CombatUnit.super.receiveDamage(() -> Math.max(0, damageDealer.getAttack() - getDefence()));
    }
}
