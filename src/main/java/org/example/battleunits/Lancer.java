package org.example.battleunits;

import org.example.characteristics.PiercingAttack;
import org.example.subsidiary.CombatUnitBehind;
import org.example.subsidiary.DealtDamageAwareness;

public interface Lancer extends Warrior, PiercingAttack, DealtDamageAwareness {
    /**
     * @return Lancer object with default health(50), attack(6) & piercing attack(50%).
     */
    static Lancer create() {
        return new LancerImpl();
    }

    @Override
    default void hit(Warrior opponent) {
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
