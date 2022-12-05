package org.example.battleunits;

import org.example.battleunits.subsidiary.CombatUnitType;
import org.example.battleunits.weapons.Weapon;
import org.example.battleunits.weapons.WeaponClass;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WarriorImpl implements Warrior {

    private static int idSequence = 0;
    /**
     * used to keep track on created objects at debugging
     */
    private final int id = ++idSequence;
    private final int initialHealth;
    private int health;
    private int attack;
    //    private Map<Weapon, Integer> weapons = new LinkedHashMap<>();
    private List<Weapon> weapons = new ArrayList<>();

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
        return getClass().getSimpleName().replace("Impl", "") +
                " #%02d".formatted(id) +
                "{hp:" + getHealth() +
                ", att:" + getAttack() + "}";
    }

    @Override
    public int getHealth() {
        return health + getHealthStatFromWeapon();
    }

    private void setHealth(int health) {

        this.health = Math.min(health, initialHealth);
    }

    @Override
    public int getAttack() {

        return Math.max(attack + getAttackStatFromWeapon(), 0);
    }

    @Override
    public int getHealthStatFromWeapon() {
        return weapons.stream()
                .mapToInt(Weapon::getHealthStatFromWeapon).sum();
    }

    @Override
    public int getAttackStatFromWeapon() {
        return weapons.stream()
                .mapToInt(Weapon::getAttackStatFromWeapon).sum();
    }

    private void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public void healedBy(int healingPoints) {
        setHealth(Math.min(health + healingPoints, initialHealth));
    }

    @Override
    public int getDefenceStatFromWeapon() {
        return weapons.stream()
                .mapToInt(Weapon::getDefenceStatFromWeapon).sum();
    }

/*    @Override
    public int defenceBonusFromWeapon() {
        return weapons.entrySet()
                .stream()
                .mapToInt(w -> w.getKey().getDefenceStat() * w.getValue())
                .sum();
    }*/

    @Override
    public int getVampirismStatFromWeapon() {
        return weapons.stream()
                .mapToInt(Weapon::getVampirismStatFromWeapon).sum();
    }

    @Override
    public int getHealPowerStatFromWeapon() {
        return weapons.stream()
                .mapToInt(Weapon::getHealPowerStatFromWeapon).sum();
    }

    @Override
    public int getPiercingAttackStatFromWeapon() {
        return weapons.stream()
                .mapToInt(Weapon::getPiercingAttackStatFromWeapon).sum();
    }

    @Override
    public void reduceHealth(int damage) {
        setHealth(health - damage);
    }

    @Override
    public int getInitialHealth() {
        return initialHealth + getHealthStatFromWeapon();
    }

    @Override
    public CombatUnit equipWeapon(Weapon weapon) {
        if (isAlive()) {
//            weapons.put(weapon, weapons.get(weapon) == null ? 1 : weapons.get(weapon) + 1);
            weapons.add(weapon);
        }
        return this;
    }

    @Override
    public CombatUnitType getCombatUnitType() {

        return CombatUnitType.FIGHTER;
    }

}