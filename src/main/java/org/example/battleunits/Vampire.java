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

    public Vampire(int health, int attack, int vampirism) {
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
    public void hits(Warrior opponent) {
        int healthBeforeHit = opponent.getHealth();
        super.hits(opponent);
        int healthAfterHit = opponent.getHealth();
        healthRestored(healthBeforeHit - healthAfterHit);
    }

    private void healthRestored(int damageDealt) {
        int heal = (damageDealt * this.vampirism) / 100;
        setHealth(Math.min(getHealth() + heal, 40));
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "h=" + getHealth() +
                ", a=" + getAttack() +
                ", v=" + vampirism +
                '}';
    }

    public int getVampirism() {
        return vampirism;
    }

    protected void setVampirism(int vampirism) {
        this.vampirism = vampirism;
    }
}
