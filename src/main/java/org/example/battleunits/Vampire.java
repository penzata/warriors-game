package org.example.battleunits;

import org.example.battleunits.characteristics.Health;
import org.example.battleunits.subsidiary.DealtDamageAwareness;
import org.example.battleunits.units.VampireUnit;
import org.example.battleunits.units.WarriorUnit;
import org.jetbrains.annotations.NotNull;

public class Vampire extends Warrior implements VampireUnit, DealtDamageAwareness {
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
    public void hit(WarriorUnit opponent) {
        int damageDealt = getDealtDamage(opponent);
        final int PERCENTS = 100;
        int healingPoints = damageDealt * getVampirism() / PERCENTS;
        vampirism(healingPoints);
    }

    @Override
    public int getVampirism() {
        return this.vampirism;
    }

    @Override
    public void vampirism(int selfHealing) {
        healedBy(selfHealing);
    }

}