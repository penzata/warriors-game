package org.example.battleunits.weapons;

import lombok.Getter;

@Getter
public class WeaponImpl implements Weapon {

    private String name;
    private int healthStat;
    private int attackStat;
    private int defenceStat;
    private int vampirismStat;
    private int healPowerStat;
    private int piercingAttackStat;
    private WeaponClass weaponClass;

    WeaponImpl(WeaponBuilder weaponBuilder) {
        name = weaponBuilder.name;
        healthStat = weaponBuilder.healthStat;
        attackStat = weaponBuilder.attackStat;
        defenceStat = weaponBuilder.defenceStat;
        vampirismStat = weaponBuilder.vampirismStat;
        healPowerStat = weaponBuilder.healPowerStat;
        piercingAttackStat = weaponBuilder.piercingAttackStat;
        weaponClass = weaponBuilder.weaponClass;
    }

    public static WeaponBuilder builder() {
        return WeaponBuilder.newInstance();
    }

}