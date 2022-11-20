package org.example.battleunits;

import org.example.battleunits.characteristics.Attack;
import org.example.battleunits.characteristics.Health;

public interface BattleUnit extends Attack, Health {
    default void hit(BattleUnit opponent) {

        opponent.receiveDamage(this);
    }

    default void receiveDamage(Attack damageDealer) {

        reduceHealth(damageDealer.getAttack());
    }

}
