package org.example.battleunits.units;

import org.example.battleunits.Lancer;

public interface LancerUnit extends WarriorUnit {

    static LancerUnit newLancer() {
        return new Lancer();
    }

    @Override
    default void hit(WarriorUnit opponent) {
        int healthBeforeHit = opponent.getHealth();
        WarriorUnit.super.hit(opponent);
        int healthAfterHit = opponent.getHealth();
        int damageDealt = Math.min(healthBeforeHit, healthBeforeHit - healthAfterHit);

//        piercingAttack(damageDealt/2);
    }

}
