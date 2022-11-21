package org.example.battleunits.units;

import org.example.battleunits.Lancer;

public interface LancerUnit extends WarriorUnit, ArmyUnit {

    static LancerUnit newLancer() {
        return new Lancer();
    }

    @Override
    default int hit(WarriorUnit opponent) {
        int damageDealt = WarriorUnit.super.hit(opponent);

//            piercingAttack(damageDealt / 2);
        return damageDealt;
    }

}
