package org.example.battleunits.weapons;

import lombok.Getter;

@Getter
public enum WeaponType implements Weapon {
    /**
     * Sword with +5 health, +2 attack.
     */
    SWORD(Weapon.builder()
            .setHealthStat(5)
            .setAttackStat(2)),
    /**
     * Shield with +20 health, -1 attack, +2 defence.
     */
    SHIELD(Weapon.builder()
            .setHealthStat(20)
            .setAttackStat(-1)
            .setDefenceStat(2)),
    /**
     * Great Axe with -15 health, +5 attack, -2 defence, +10% vampirism.
     */
    GREAT_AXE(Weapon.builder()
            .setHealthStat(-15)
            .setAttackStat(5)
            .setDefenceStat(-2)
            .setVampirismStat(10)),
    /**
     * Katana with -20 health, +6 attack, -5 defence, +50% vampirism.
     */
    KATANA(Weapon.builder()
            .setHealthStat(-20)
            .setAttackStat(6)
            .setDefenceStat(-5)
            .setVampirismStat(50)),
    /**
     * Magic Wand with +30 health, +3 attack, +3 heal power.
     */
    MAGIC_WAND(Weapon.builder()
            .setHealthStat(30)
            .setAttackStat(3)
            .setHealPowerStat(3));

    private int healthStatFromWeapon;
    private int attackStatFromWeapon;
    private int defenceStatFromWeapon;
    private int vampirismStatFromWeapon;
    private int healPowerStatFromWeapon;
    private int piercingAttackStatFromWeapon;

    WeaponType(WeaponBuilder weaponBuilder) {
        healthStatFromWeapon = weaponBuilder.health;
        attackStatFromWeapon = weaponBuilder.attack;
        defenceStatFromWeapon = weaponBuilder.defence;
        vampirismStatFromWeapon = weaponBuilder.vampirism;
        healPowerStatFromWeapon = weaponBuilder.healPower;
        piercingAttackStatFromWeapon = weaponBuilder.piercingAttack;
    }
}