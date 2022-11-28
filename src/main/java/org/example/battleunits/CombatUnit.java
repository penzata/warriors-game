package org.example.battleunits;

import org.example.battleunits.characteristics.Attack;
import org.example.battleunits.characteristics.Health;
import org.example.battleunits.weapons.Weapon;

public interface CombatUnit extends Attack, Health {

    /**
     * @return Warrior object with default health(50) & attack(5).
     */
    static Warrior newWarrior() {
        return new Warrior();
    }

    /**
     * @return Knight object with default health(50) & attack(7).
     */
    static Knight newKnight() {
        return new Knight();
    }

    /**
     * @return Defender object with default health(60), attack(3) & defence(2).
     */
    static Defender newDefender() {
        return new Defender();
    }

    /**
     * @return Vampire object with default health(40), attack(4) & vampirism(50%).
     */
    static Vampire newVampire() {
        return new Vampire();
    }

    /**
     * @return Lancer object with default health(50), attack(6) & piercing attack(50%).
     */
    static Lancer newLancer() {
        return new Lancer();
    }

    /**
     * @return Healer object with default health(60), attack(0) & heal power(2).
     */
    static Healer newHealer() {
        return new Healer();
    }

    default void hit(CombatUnit opponent) {

        opponent.receiveDamage(this);
    }

    void receiveDamage(Attack damageDealer);

    void equipWeapon(Weapon weapon);
}
