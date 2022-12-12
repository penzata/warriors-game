package org.example.battleunits;

public class CombatUnitFactory {

    private CombatUnitFactory() {
    }

    /**
     * @return Warrior object with default health(50) & attack(5).
     */
    public static CombatUnit createWarrior() {
        return new Warrior();
    }

    /**
     * @return Knight object with default health(50) & attack(7).
     */
    public static CombatUnit createKnight() {
        return new Knight();
    }

    /**
     * @return Defender object with default health(60), attack(3) & defence(2).
     */
    public static CombatUnit createDefender() {
        return new Defender();
    }

    /**
     * @return Vampire object with default health(40), attack(4) & vampirism(50%).
     */
    public static CombatUnit createVampire() {
        return new Vampire();
    }

    /**
     * @return Lancer object with default health(50), attack(6) & piercing attack(50%).
     */
    public static CombatUnit createLancer() {
        return new Lancer();
    }

    /**
     * @return Healer object with default health(60), attack(0) & heal power(2).
     */
    public static CombatUnit createHealer() {
        return new Healer();
    }

    /**
     * @return Warlord object with default health(100), attack(4) & defence(2).
     */
    public static CombatUnit createWarlord() {
        return new Warlord();
    }
}
