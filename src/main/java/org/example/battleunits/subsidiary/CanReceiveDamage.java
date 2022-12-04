package org.example.battleunits.subsidiary;

import org.example.battleunits.characteristics.Attack;
import org.example.battleunits.characteristics.Health;

public interface CanReceiveDamage extends Health {

    default void receiveDamage(Attack damageDealer) {

        reduceHealth(damageDealer.getAttack());
    }

    void reduceHealth(int damage);
}
