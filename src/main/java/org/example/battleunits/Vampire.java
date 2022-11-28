package org.example.battleunits;

import org.example.battleunits.characteristics.Vampirism;
import org.example.battleunits.subsidiary.DealtDamageAwareness;
import org.example.battleunits.weapons.Weapon;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Vampire extends Warrior implements Vampirism, DealtDamageAwareness {
    private static final Logger LOGGER = LoggerFactory.getLogger(Vampire.class);
    private int vampirism;

    Vampire() {
        super(40, 4);
        this.vampirism = 50;
    }

    @Override
    public void equipWeapon(Weapon weapon) {
        super.equipWeapon(weapon);
        setVampirism(Math.max(getVampirism() + weapon.getWeaponVampirism(), 0));
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
    public void hit(CombatUnit opponent) {
        int damageDealt = getDealtDamage(opponent);
        final int PERCENTS = 100;
        int healingPoints = (int) (damageDealt * getVampirism() / PERCENTS);
        vampirism(healingPoints);
        LOGGER.debug("health after vampirism: {}", getHealth());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{h:" + getHealth() +
                ", a:" + getAttack() +
                ", v:" + getVampirism() + "%}";
    }

    @Override
    public int getVampirism() {
        return vampirism;
    }

    /**
     * @param selfHealing - vampire's self-healing (restores health by 50% of the dealt damage).
     */
    private void vampirism(int selfHealing) {
        healedBy(selfHealing);
    }

    private void setVampirism(int vampirism) {
        this.vampirism = vampirism;
    }
}