package org.example.weapons;

public class CustomWeapon implements Weapon {
    private int health;
    private int attack;
    private int defence;
    private int vampirism;
    private int healPower;


    public CustomWeapon(int health, int attack, int defence, int vampirism, int healPower) {
        this.health = health;
        this.attack = attack;
        this.defence = defence;
        this.vampirism = vampirism;
        this.healPower = healPower;
    }

    @Override
    public int getWeaponHealth() {
        return health;
    }

    @Override
    public int getWeaponAttack() {
        return attack;
    }

    @Override
    public int getWeaponDefence() {
        return defence;
    }

    @Override
    public int getWeaponVampirism() {
        return vampirism;
    }

    @Override
    public int getWeaponHealPower() {
        return healPower;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{h:" + health +
                ", a:" + attack +
                ", d:" + defence +
                ", v:" + vampirism +
                ", heal:" + healPower +
                '}';
    }
}
