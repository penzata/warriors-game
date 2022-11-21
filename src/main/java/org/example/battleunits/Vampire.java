package org.example.battleunits;

import org.example.battleunits.units.VampireUnit;
import org.jetbrains.annotations.NotNull;

public class Vampire extends Warrior implements VampireUnit {
    private final int vampirism;
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
    public int getVampirism() {
        return this.vampirism;
    }
}
