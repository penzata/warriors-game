package org.example.battleunits.units;

import org.example.battleunits.Warrior;
import org.example.battleunits.characteristics.Attack;
import org.example.battleunits.characteristics.Health;

public interface WarriorUnit extends Attack, Health {

    void hit(WarriorUnit opponent);

    void receiveDamage(Attack damageDealer);

    /**
     * @return default Warrior object with default health(50) & attack(5).
     */
    static Warrior newWarrior() {
        return new Warrior();
    }

}
