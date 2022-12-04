package org.example.battleunits;

import org.example.battleunits.characteristics.PiercingAttack;
import org.example.battleunits.subsidiary.CanReceiveDamage;
import org.example.battleunits.subsidiary.CombatUnitBehind;
import org.example.battleunits.subsidiary.DealtDamageAwareness;

public interface Lancer extends CombatUnit, PiercingAttack, DealtDamageAwareness {

    @Override
    default void hit(CanReceiveDamage opponent) {
        int damageDealt = getDealtDamage(opponent);
        if (opponent instanceof CombatUnitBehind opponentBehind) {
            CombatUnit nextOpponent = opponentBehind.getCombatUnitBehind();
            if (nextOpponent != null) {
                final int PERCENTS = 100;
                int reducedDamage = (int) (damageDealt * getPiercingAttack() / PERCENTS);
                nextOpponent.receiveDamage(() -> reducedDamage);
            }
        }
    }
}
