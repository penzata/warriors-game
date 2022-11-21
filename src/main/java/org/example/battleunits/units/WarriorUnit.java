package org.example.battleunits.units;

import org.example.battleunits.Defender;
import org.example.battleunits.Knight;
import org.example.battleunits.Warrior;
import org.example.battleunits.characteristics.Attack;
import org.example.battleunits.characteristics.Health;

public interface WarriorUnit extends Attack, Health {
    default void hit(WarriorUnit opponent) {

        opponent.receiveDamage(this);
    }

    default void receiveDamage(Attack damageDealer) {

        reduceHealth(damageDealer.getAttack());
    }

    static WarriorUnit newWarrior() {
        return new Warrior();
    }

    static WarriorUnit newKnight() {
        return new Knight();
    }

    static DefenderUnit newDefender() {
        return new Defender();
    }

}
