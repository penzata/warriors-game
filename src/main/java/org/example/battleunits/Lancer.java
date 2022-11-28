package org.example.battleunits;

import org.example.battleunits.characteristics.PiercingAttack;
import org.example.battleunits.subsidiary.DealtDamageAwareness;
import org.example.battleunits.subsidiary.WarriorUnitBehind;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lancer extends Warrior implements PiercingAttack, DealtDamageAwareness {
    private static final Logger LOGGER = LoggerFactory.getLogger(Lancer.class);
    /**
     * piercing damage to unit behind (second unit) - 50% of the dealt damage to the first enemy unit.
     */
    private int piercingDamage;


    Lancer() {
        super(50, 6);
        this.piercingDamage = 50;
    }

    Lancer(int health, int attack, int piercingDamage) {
        super(health, attack);
        this.piercingDamage = piercingDamage;
    }

    /**
     * @param lancer - copy constructor
     */
    Lancer(@NotNull Lancer lancer) {
        super(lancer);
        this.piercingDamage = lancer.piercingDamage;
    }

    @Override
    public void hit(CombatUnit opponent) {
        int damageDealt = getDealtDamage(opponent);
        LOGGER.debug("health of Lancer's first opponent after being hit: {}", opponent.getHealth());
        if (opponent instanceof WarriorUnitBehind opponentBehind) {
            CombatUnit nextOpponent = opponentBehind.getWarriorBehind();
            if (nextOpponent != null) {
                final int PERCENTS = 100;
                int reducedDamage = damageDealt * getPiercingAttack() / PERCENTS;
                nextOpponent.receiveDamage(() -> reducedDamage);
                LOGGER.debug("health of Lancer's next opponent after piercing damage({}): {}",
                        reducedDamage, nextOpponent.getHealth());
            }
        }
    }

    @Override
    public int getPiercingAttack() {
        return piercingDamage;
    }

    private void setPiercingAttack(int piercingDamage) {
        this.piercingDamage = piercingDamage;
    }
}


