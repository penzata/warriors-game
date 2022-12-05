package org.example.battleunits;

import org.example.battleunits.characteristics.Attack;
import org.example.battleunits.characteristics.Health;
import org.example.battleunits.subsidiary.CanBeHealed;
import org.example.battleunits.subsidiary.CanReceiveDamage;
import org.example.battleunits.subsidiary.CombatUnitType;
import org.example.battleunits.weapons.Weapon;

public interface CombatUnit extends Attack, Health, CanReceiveDamage, CanBeHealed, Weapon {
    /**
     * @return Warrior object with default health(50) & attack(5).
     */
    static Warrior createWarrior() {
        return new WarriorImpl();
    }

    /**
     * @return Knight object with default health(50) & attack(7).
     */
    static Knight createKnight() {
        return new KnightImpl();
    }

    /**
     * @return Defender object with default health(60), attack(3) & defence(2).
     */
    static Defender createDefender() {
        return new DefenderImpl();
    }

    /**
     * @return Vampire object with default health(40), attack(4) & vampirism(50%).
     */
    static Vampire createVampire() {
        return new VampireImpl();
    }

    /**
     * @return Lancer object with default health(50), attack(6) & piercing attack(50%).
     */
    static Lancer createLancer() {
        return new LancerImpl();
    }

    /**
     * @return Healer object with default health(60), attack(0) & heal power(2).
     */
    static Healer createHealer() {
        return new HealerImpl();
    }

    /**
     * @return Warlord object with default health(100), attack(4) & defence(2).
     */
    static Warlord createWarlord() {
        return new WarlordImpl();
    }

    default void hit(CanReceiveDamage opponent) {
        opponent.receiveDamage(this);
    }

    default CombatUnit equipWeapon(Weapon weapon) {
        return this;
    }

    CombatUnitType getCombatUnitType();
}
