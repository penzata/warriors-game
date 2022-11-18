package org.example.battleunits;

public interface BattleUnit extends CanAttack, HasHealth {
    default void hit(BattleUnit opponent) {
        opponent.receiveDamage(this);
    }

    default void receiveDamage(CanAttack damageDealer) {
        reduceHealthBasedOnDamage(damageDealer.getAttack());
    }
}
