package org.example.battleunits.units;

import org.example.battleunits.characteristics.Attack;
import org.example.battleunits.characteristics.Defence;

public interface DefenderUnit extends WarriorUnit, Defence {

    @Override
    default void receiveDamage(Attack damageDealer) {
        WarriorUnit.super.receiveDamage(() -> Math.max(0, damageDealer.getAttack() - getDefence()));
    }
}