package org.example.battleunits.units;

import org.example.battleunits.Lancer;

public interface LancerUnit extends WarriorUnit {
    int PERCENTS = 100;
    /**
     * piercing damage to unit behind (second unit) - 50% of the dealt damage to the first enemy unit.
     */
    int PIERCING_DAMAGE = 50;

    /**
     * @return default Lancer object with default health(50) & attack(6).
     */
    static Lancer newLancer() {
        return new Lancer();
    }

    @Override
    default int hit(WarriorUnit opponent) {
        int damageDealt = WarriorUnit.super.hit(opponent);
        int reducedDamage = damageDealt * PIERCING_DAMAGE / PERCENTS;
        if (opponent instanceof WarriorBehind opponentBehind) {
            WarriorUnit nextOpponent = opponentBehind.getWarriorBehind();
            if (nextOpponent != null) {
                nextOpponent.receiveDamage(() -> reducedDamage);
            }
        }
        return damageDealt;
    }

}
