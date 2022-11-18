package org.example.battleunits;

import org.jetbrains.annotations.NotNull;

public class Vampire extends Warrior {
    private int vampirism;
    /**
     * health cap for healing
     */
    private int healthBeforeFight;

    /**
     * Constructs default Vampire object with default health(40), attack(4) & vampirism(50).
     */
    public Vampire() {
        super(40, 4);
        this.vampirism = 50;
        this.healthBeforeFight = 40;
    }

    Vampire(int health, int attack, int vampirism) {
        super(health, attack);
        this.vampirism = vampirism;
        this.healthBeforeFight = health;
    }

    /**
     * @param vampire - copy constructor
     */
    public Vampire(@NotNull Vampire vampire) {
        super(vampire);
        this.vampirism = vampire.vampirism;
    }

    @Override
    public void hit(BattleUnit opponent) {
        super.hit(opponent);
    }

/*    @Override
    public void hits(Warrior opponent) {
        int healthBeforeHit = opponent.getHealth();
        super.hits(opponent);
        int healthAfterHit = opponent.getHealth();
        int damageDealt = (healthAfterHit < 0) ? healthBeforeHit : (healthBeforeHit - healthAfterHit);
        healthRestored(damageDealt);
    }

    private void healthRestored(int damageDealt) {
        int heal = damageDealt * vampirism / 100;
        setHealth(Math.min(getHealth() + heal, healthBeforeFight));
    }

    public int getVampirism() {
        return vampirism;
    }

    protected void setVampirism(int vampirism) {
        this.vampirism = vampirism;
    }*/
}
