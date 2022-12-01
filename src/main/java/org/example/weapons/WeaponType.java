package org.example.weapons;

import lombok.Getter;

@Getter
public enum WeaponType implements Weapon {
    /**
     * Sword with +5 health, +2 attack.
     */
    SWORD(Weapon.builder()
            .healthStat(5)
            .attackStat(2)),
    /**
     * Shield with +20 health, -1 attack, +2 defence.
     */
    SHIELD(Weapon.builder()
            .healthStat(20)
            .attackStat(-1)
            .defenceStat(2)),
    /**
     * Great Axe with -15 health, +5 attack, -2 defence, +10% vampirism.
     */
    GREAT_AXE(Weapon.builder()
            .healthStat(-15)
            .attackStat(5)
            .defenceStat(-2)
            .vampirismStat(10)),
    /**
     * Katana with -20 health, +6 attack, -5 defence, +50% vampirism.
     */
    KATANA(Weapon.builder()
            .healthStat(-20)
            .attackStat(6)
            .defenceStat(-5)
            .vampirismStat(50)),
    /**
     * Magic Wand with +30 health, +3 attack, +3 heal power.
     */
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