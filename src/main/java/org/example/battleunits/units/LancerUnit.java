package org.example.battleunits.units;

import org.example.battleunits.Lancer;

public interface LancerUnit extends WarriorUnit {

    /**
     * @return default Lancer object with default health(50) & attack(6).
     */
    static Lancer newLancer() {
        return new Lancer();
    }

    @Override
    default int hit(WarriorUnit opponent) {
        int damageDealt = WarriorUnit.super.hit(opponent);

//            piercingAttack(damageDealt / 2);
        return damageDealt;
    }

}
