package org.example.battleunits.weapons;

public class WeaponBuilder {
    private static int idSequence = 0;
    /**
     * used to keep track on created custom weapons at debugging
     */
    private final int id = ++idSequence;
    int health;
    int attack;
    int defence;
    int vampirism;
    int healPower;
    int piercingAttack;
    WeaponClass weaponClass;

    WeaponBuilder() {
    }

    public WeaponBuilder setHealthStat(int health) {
        this.health = health;
        return this;
    }

    public WeaponBuilder setAttackStat(int attack) {
        this.attack = attack;
        return this;
    }

    public WeaponBuilder setDefenceStat(int defence) {
        this.defence = defence;
        return this;
    }

    public WeaponBuilder setVampirismStat(int vampirism) {
        this.vampirism = vampirism;
        return this;
    }

    public WeaponBuilder setPiercingAttackStat(int piercingAttack) {
        this.piercingAttack = piercingAttack;
        return this;
    }

    public WeaponBuilder setHealPowerStat(int healPower) {
        this.healPower = healPower;
        return this;
    }

    public WeaponBuilder setWeaponClass(WeaponClass weaponClass) {
        this.weaponClass = weaponClass;
        return this;
    }

    public Weapon build() {
        return new Weapon() {
            @Override
            public String toString() {
                return "CUSTOM_WEAPON#%2d".formatted(id);
            }

            @Override
            public int getHealthStat() {
                return health;
            }

            @Override
            public int getAttackStat() {
                return attack;
            }

            @Override
            public int getDefenceStat() {
                return defence;
            }

            @Override
            public int getVampirismStat() {
                return vampirism;
            }

            @Override
            public int getHealPowerStat() {
                return healPower;
            }

            @Override
            public int getPiercingAttackStat() {
                return piercingAttack;
            }

            @Override
            public WeaponClass getWeaponClass() {
                return weaponClass;
            }

        };
    }

}