package org.example.battleunits;

import org.example.battleunits.units.LancerUnit;
import org.example.battleunits.units.WarriorBehind;
import org.example.battleunits.units.WarriorUnit;
import org.jetbrains.annotations.NotNull;

public class Lancer extends Warrior implements LancerUnit {

    /**
     * Constructs default Lancer object with default health(50) & attack(6).
     */
    public Lancer() {

        super(50, 6);
    }

    public Lancer(int health, int attack) {

        super(health, attack);
    }

    public Lancer(@NotNull Lancer lancer) {

        super(lancer);
    }

    @Override
    public int hit(WarriorUnit opponent) {
        int damageDealt = hit(opponent);
        if (opponent instanceof WarriorBehind opponentBehind) {
            WarriorUnit nextOpponent = opponentBehind.getWarriorBehind();
            if (nextOpponent != null) {
                int reducedDamage = damageDealt * PIERCING_DAMAGE / PERCENTS;
                nextOpponent.receiveDamage(() -> reducedDamage);
            }
        }
        return damageDealt;
    }

}


