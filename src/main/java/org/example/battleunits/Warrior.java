package org.example.battleunits;

import org.example.battleunits.characteristics.Attack;
import org.example.battleunits.weapons.Weapon;
import org.jetbrains.annotations.NotNull;

public class Warrior implements CombatUnit {
    private final int initialHealth;
    private int health;
    private int attack;

    Warrior() {
        this(50, 5);
    }

    Warrior(int health, int attack) {
        this.initialHealth = this.health = health;
        this.attack = attack;
    }

    /**
     * @param warrior - copy constructor
     */
    Warrior(@NotNull Warrior warrior) {
        this.health = warrior.health;
        this.attack = warrior.attack;
        this.initialHealth = warrior.initialHealth;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{h:" + getHealth() +
                ", a:" + getAttack() + "}";
    }

    private void setHealth(int health) {

        this.health = health;
    }

    @Override
    public void receiveDamage(Attack damageDealer) {

        reduceHealth(damageDealer.getAttack());
    }

    @Override
    public void equipWeapon(Weapon weapon) {
        setHealth(Math.max(getHealth() + weapon.getWeaponHealth(), 0));
        setAttack(Math.max(getAttack() + weapon.getWeaponAttack(), 0));
    }

    private void reduceHealth(int damage) {
        setHealth(getHealth() - damage);
    }

    void healedBy(int healingPoints) {
        setHealth(Math.min(getHealth() + healingPoints, initialHealth));
    }

    @Override
    public int getAttack() {
        return attack;
    }

    private void setAttack(int attack) {
        this.attack = attack;
    }

}