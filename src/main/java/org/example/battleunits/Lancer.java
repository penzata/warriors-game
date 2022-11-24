package org.example.battleunits;

import org.example.battleunits.subsidiary.DealtDamageAwareness;
import org.example.battleunits.units.LancerUnit;
import org.example.battleunits.units.WarriorUnit;
import org.example.battleunits.subsidiary.WarriorUnitBehind;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lancer extends Warrior implements LancerUnit, DealtDamageAwareness {
    private static final Logger LOGGER = LoggerFactory.getLogger(Lancer.class);
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

    Lancer(int health, int attack) {

        super(health, attack);
    }

    /**
     * @param lancer - copy constructor
     */
    Lancer(@NotNull Lancer lancer) {

        super(lancer);
    }

    @Override
    public void hit(WarriorUnit opponent) {
        int damageDealt = getDealtDamage(opponent);
        LOGGER.info("health of Lancer's first opponent after being hit: {}", opponent.getHealth());
        if (opponent instanceof WarriorUnitBehind opponentBehind) {
            WarriorUnit nextOpponent = opponentBehind.getWarriorBehind();
            if (nextOpponent != null) {
                int reducedDamage = damageDealt * PIERCING_DAMAGE / PERCENTS;
                nextOpponent.receiveDamage(() -> reducedDamage);
                LOGGER.info("health of Lancer's next opponent after piercing damage({}): {}",
                        reducedDamage, nextOpponent.getHealth());
            }
        }
    }

}


