package org.example.battleunits;

import org.example.battleunits.units.LancerUnit;
import org.example.battleunits.units.WarriorUnitBehind;
import org.example.battleunits.units.WarriorUnit;
import org.jetbrains.annotations.NotNull;

public class Lancer extends Warrior implements LancerUnit {
    private final int PERCENTS = 100;
    /**
     * piercing damage to unit behind (second unit) - 50% of the dealt damage to the first enemy unit.
     */
    private final int PIERCING_DAMAGE = 50;

    /**
     * Constructs default Lancer object with default health(50) & attack(6).
     */
    public Lancer() {

        super(50, 6);
    }

    public Lancer(int health, int attack) {

        super(health, attack);
    }

    /**
     * @param lancer - copy constructor
     */
    public Lancer(@NotNull Lancer lancer) {

        super(lancer);
    }

    @Override
    public void hit(WarriorUnit opponent) {
        int healthBeforeHit = opponent.getHealth();
        super.hit(opponent);
        if (opponent instanceof WarriorUnitBehind opponentBehind) {
            WarriorUnit nextOpponent = opponentBehind.getWarriorBehind();
            if (nextOpponent != null) {
                int healthAfterHit = opponent.getHealth();
                int damageDealt = Math.min(healthBeforeHit, healthBeforeHit - healthAfterHit);
                int reducedDamage = damageDealt * PIERCING_DAMAGE / PERCENTS;
                nextOpponent.receiveDamage(() -> reducedDamage);
            }
        }
    }

}


