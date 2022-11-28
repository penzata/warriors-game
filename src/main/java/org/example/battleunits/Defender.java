package org.example.battleunits;

import org.example.characteristics.Attack;
import org.example.characteristics.Defence;
import org.example.weapons.Weapon;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Defender extends Warrior implements Defence {
    private final static Logger LOGGER = LoggerFactory.getLogger(Defender.class);
    private int defence;

    Defender() {
        super(60, 3);
        this.defence = 2;
    }

    Defender(int health, int attack, int defence) {
        super(health, attack);
        this.defence = defence;
    }

    /**
     * @param defender - copy constructor
     */
    Defender(@NotNull Defender defender) {
        super(defender);
        this.defence = defender.defence;
    }

    @Override
    public void receiveDamage(Attack damageDealer) {
        LOGGER.debug("defender's health before taking damage: {}", getHealth());
        super.receiveDamage(() -> Math.max(0, damageDealer.getAttack() - getDefence()));
        LOGGER.debug("defender's health after deflecting damage({}): {}", getDefence(), getHealth());
    }

    @Override
    public CombatUnit equipWeapon(Weapon weapon) {
        super.equipWeapon(weapon);
        setDefence(Math.max(getDefence() + weapon.getWeaponDefence(), 0));
        return this;
    }

    @Override
    public int getDefence() {
        return this.defence;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{h:" + getHealth() +
                ", a:" + getAttack() +
                ", d:" + getDefence() + "}";
    }

    private void setDefence(int defence) {
        this.defence = defence;
    }
}
