package org.example.battleunits;

import org.example.characteristics.Attack;
import org.example.weapons.Weapon;

public class WarriorImpl implements Warrior {
    private final int initialHealth;
    private int health;
    private int attack;

    WarriorImpl() {
        this(50, 5);
    }

    WarriorImpl(int health, int attack) {
        this.initialHealth = this.health = health;
        this.attack = attack;
    }

    /**
     * @param warrior - copy constructor
     */
    WarriorImpl(WarriorImpl warrior) {
        this.health = warrior.health;
        this.attack = warrior.attack;
        this.initialHealth = warrior.initialHealth;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{h:" + getHealth() +
                ", a:" + getAttack() + "}";
    }

    @Override
    public int getHealth() {
        return health;
    }

    private void setHealth(int health) {

        this.health = health;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    private void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public void receiveDamage(Attack damageDealer) {

        reduceHealth(damageDealer.getAttack());
    }

    private void reduceHealth(int damage) {
        setHealth(getHealth() - damage);
    }

    @Override
    public Warrior equipWeapon(Weapon weapon) {
        setHealth(Math.max(getHealth() + weapon.getWeaponHealth(), 0));
        setAttack(Math.max(getAttack() + weapon.getWeaponAttack(), 0));
        return this;
    }

    void healedBy(int healingPoints) {
        setHealth(Math.min(getHealth() + healingPoints, initialHealth));
    }

}