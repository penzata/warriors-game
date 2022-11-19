package org.example.battleunits;

import org.example.battleunits.characteristic.Attack;
import org.example.battleunits.characteristic.Health;

public interface BattleUnit extends Attack, Health {
    default void hit(BattleUnit opponent) {

        opponent.receiveDamage(this);
    }

    default void receiveDamage(Attack damageDealer) {

        reduceHealth(damageDealer.getAttack());
    }
}
