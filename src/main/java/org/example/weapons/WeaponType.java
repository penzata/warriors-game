package org.example.weapons;

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

    WeaponType(WeaponBuilder weaponBuilder) {
    }

    @Override
    public int getWeaponHealth() {
        return 0;
    }

    @Override
    public int getWeaponAttack() {
        return 0;
    }

    @Override
    public int getWeaponDefence() {
        return 0;
    }

    @Override
    public int getWeaponVampirism() {
        return 0;
    }

    @Override
    public int getWeaponHealPower() {
        return 0;
    }

    @Override
    public int getWeaponPiercingAttack() {
        return 0;
    }
}