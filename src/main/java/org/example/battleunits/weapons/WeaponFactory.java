package org.example.battleunits.weapons;

public class WeaponFactory {
    /**
     * Sword with +5 health, +2 attack.
     */
    public static final Weapon SWORD = WeaponImpl.builder()
            .name("Sword")
            .healthStat(5)
            .attackStat(2)
            .weaponClass(WeaponClass.OFFENSIVE)
            .build();
    /**
     * Shield with +20 health, -1 attack, +2 defence.
     */
    public static final Weapon SHIELD = WeaponImpl.builder()
            .name("Shield")
            .healthStat(20)
            .attackStat(-1)
            .defenceStat(2)
            .weaponClass(WeaponClass.DEFENSIVE)
            .build();
    /**
     * Great Axe with -15 health, +5 attack, -2 defence, +10% vampirism.
     */
    public static final Weapon GREAT_AXE = WeaponImpl.builder()
            .name("Great Axe")
            .healthStat(-15)
            .attackStat(5)
            .defenceStat(-2)
            .vampirismStat(10)
            .weaponClass(WeaponClass.OFFENSIVE)
            .build();
    /**
     * Katana with -20 health, +6 attack, -5 defence, +50% vampirism.
     */
    public static final Weapon KATANA = WeaponImpl.builder()
            .name("Katana")
            .healthStat(-20)
            .attackStat(6)
            .defenceStat(-5)
            .vampirismStat(50)
            .weaponClass(WeaponClass.OFFENSIVE)
            .build();
    /**
     * Magic Wand with +30 health, +3 attack, +3 heal power.
     */
    public static final Weapon MAGIC_WAND = WeaponImpl.builder()
            .name("Magic Wand")
            .healthStat(30)
            .attackStat(3)
            .healPowerStat(3)
            .build();

    private WeaponFactory() {
    }

}
