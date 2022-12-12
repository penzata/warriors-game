package org.example.battleunits.weapons;

public class WeaponBuilder {
    String name;
    int healthStat;
    int attackStat;
    int defenceStat;
    int vampirismStat;
    int healPowerStat;
    int piercingAttackStat;
    WeaponClass weaponClass;

    private WeaponBuilder() {
    }

    public static WeaponBuilder newInstance() {
        return new WeaponBuilder();
    }

    public WeaponBuilder name(String name) {
        this.name = name;
        return this;
    }

    public WeaponBuilder healthStat(int healthStat) {
        this.healthStat = healthStat;
        return this;
    }

    public WeaponBuilder attackStat(int attackStat) {
        this.attackStat = attackStat;
        return this;
    }

    public WeaponBuilder defenceStat(int defenceStat) {
        this.defenceStat = defenceStat;
        return this;
    }

    public WeaponBuilder vampirismStat(int vampirismStat) {
        this.vampirismStat = vampirismStat;
        return this;
    }

    public WeaponBuilder healPowerStat(int healPowerStat) {
        this.healPowerStat = healPowerStat;
        return this;
    }

    public WeaponBuilder piercingAttackStat(int piercingAttackStat) {
        this.piercingAttackStat = piercingAttackStat;
        return this;
    }

    public WeaponBuilder weaponClass(WeaponClass weaponClass) {
        this.weaponClass = weaponClass;
        return this;
    }

    public Weapon build() {
        return new WeaponImpl(this);
    }

}
