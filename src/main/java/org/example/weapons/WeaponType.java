package org.example.weapons;

import lombok.Getter;

@Getter
public enum WeaponType implements Weapon {
    SWORD(Weapon.builder()
            .healthStat(5)
            .attackStat(2)),
    SHIELD(Weapon.builder()
            .healthStat(20)
            .attackStat(-1)
            .defenceStat(2)),
    GREAT_AXE(Weapon.builder()
            .healthStat(-15)
            .attackStat(5)
            .defenceStat(-2)
            .vampirismStat(10)),
    KATANA(Weapon.builder()
            .healthStat(-20)
            .attackStat(6)
            .defenceStat(-5)
            .vampirismStat(50)),
    MAGIC_WAND(Weapon.builder()
            .healthStat(30)
            .attackStat(3)
            .healPowerStat(3));

    private int healthStat;
    private int attackStat;
    private int defenceStat;
    private int vampirismStat;
    private int healPowerStat;
    private int piercingAttackStat;

    WeaponType(WeaponBuilder weaponBuilder) {
        healthStat = weaponBuilder.health;
        attackStat = weaponBuilder.attack;
        defenceStat = weaponBuilder.defence;
        vampirismStat = weaponBuilder.vampirism;
        healPowerStat = weaponBuilder.healPower;
        piercingAttackStat = weaponBuilder.piercingAttack;
    }

}