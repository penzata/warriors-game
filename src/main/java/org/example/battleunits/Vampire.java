package org.example.battleunits;

import org.jetbrains.annotations.NotNull;

public class Vampire extends Warrior {
    private int vampirism;
    /**
     * Constructs default Vampire object with default health(40), attack(4) & vampirism(50).
     */
    public Vampire() {
        super(40, 4);
        this.vampirism = 50;
    }

    Vampire(int health, int attack, int vampirism) {
        super(health, attack);
        this.vampirism = vampirism;
    }

    /**
     * @param vampire - copy constructor
     */
    public Vampire(@NotNull Vampire vampire) {
        super(vampire);
        this.vampirism = vampire.vampirism;
    }

    @Override
    public void hit(BattleUnit opponent) {
        int healthBeforeHit = opponent.getHealth();
        super.hit(opponent);
        int healthAfterHit = opponent.getHealth();
        int damageDealt = Math.min(healthBeforeHit, healthBeforeHit - healthAfterHit);
        final int PERCENT = 100;
        int heal = damageDealt * vampirism / PERCENT;
        healthRestored(heal);
    }

}
