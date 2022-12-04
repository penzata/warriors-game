package org.example.battleunits;

import org.example.battleunits.characteristics.PiercingAttack;
import org.example.battleunits.subsidiary.CanReceiveDamage;
import org.example.battleunits.subsidiary.CombatUnitBehind;
import org.example.battleunits.subsidiary.DealtDamageAwareness;

public interface Lancer extends Warrior, PiercingAttack, DealtDamageAwareness {
    /**
     * @return Lancer object with default health(50), attack(6) & piercing attack(50%).
     */
    static Lancer create() {
        return new LancerImpl();
    }

    @Override
    default void hit(CanReceiveDamage opponent) {
        int damageDealt = getDealtDamage(opponent);
        if (opponent instanceof CombatUnitBehind opponentBehind) {
            Warrior nextOpponent = opponentBehind.getWarriorBehind();
            if (nextOpponent != null) {
                final int PERCENTS = 100;
                int reducedDamage = (int) (damageDealt * getPiercingAttack() / PERCENTS);
                nextOpponent.receiveDamage(() -> reducedDamage);
            }
        }
    }
}
