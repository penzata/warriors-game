package org.example.battleunits.weapons;

import lombok.Getter;

@Getter
public class WeaponType implements Weapon {
    /**
     * Sword with +5 health, +2 attack.
     */
    public static final Weapon SWORD = Weapon.builder()
            .setHealthStat(5)
            .setAttackStat(2)
            .build();
    /**
     * Shield with +20 health, -1 attack, +2 defence.
     */
    public static final Weapon SHIELD = Weapon.builder()
            .setHealthStat(20)
            .setAttackStat(-1)
            .setDefenceStat(2)
            .build();
    /**
     * Great Axe with -15 health, +5 attack, -2 defence, +10% vampirism.
     */
    public static final Weapon GREAT_AXE = Weapon.builder()
            .setHealthStat(-15)
            .setAttackStat(5)
            .setDefenceStat(-2)
            .setVampirismStat(10)
            .build();
    /**
     * Katana with -20 health, +6 attack, -5 defence, +50% vampirism.
     */
    public static final Weapon KATANA = Weapon.builder()
            .setHealthStat(-20)
            .setAttackStat(6)
            .setDefenceStat(-5)
            .setVampirismStat(50)
            .build();
    /**
     * Magic Wand with +30 health, +3 attack, +3 heal power.
     */
    public static final Weapon MAGIC_WAND = Weapon.builder()
            .setHealthStat(30)
            .setAttackStat(3)
            .setHealPowerStat(3)
            .build();

    private int healthStatFromWeapon;
    private int attackStatFromWeapon;
    private int defenceStatFromWeapon;
    private int vampirismStatFromWeapon;
    private int healPowerStatFromWeapon;
    private int piercingAttackStatFromWeapon;
    private int decreaseDurabilityStep;
    private int durabilityStat;

    WeaponType(WeaponBuilder weaponBuilder) {
        healthStatFromWeapon = weaponBuilder.health;
        attackStatFromWeapon = weaponBuilder.attack;
        defenceStatFromWeapon = weaponBuilder.defence;
        vampirismStatFromWeapon = weaponBuilder.vampirism;
        healPowerStatFromWeapon = weaponBuilder.healPower;
        piercingAttackStatFromWeapon = weaponBuilder.piercingAttack;
        decreaseDurabilityStep = weaponBuilder.decreaseDurabilityStep;
        durabilityStat = weaponBuilder.durability;
    }

    @Override
    public void decreaseDurability() {
        if (durabilityStat > 0 && decreaseDurabilityStep > 0) {
            durabilityStat -= decreaseDurabilityStep;
        }
        if (durabilityStat <= 0) {
            breakWeapon();
        }
    }

    private void breakWeapon() {
        healthStatFromWeapon = 0;
        attackStatFromWeapon = 0;
        defenceStatFromWeapon = 0;
        vampirismStatFromWeapon = 0;
        healPowerStatFromWeapon = 0;
        piercingAttackStatFromWeapon = 0;
    }
}