package org.example.battleunits.weapons;

public interface Weapon {

    static WeaponBuilder builder() {
        return new WeaponBuilder();
    }

    int getHealthStat();

    int getAttackStat();

    int getDefenceStat();

    int getVampirismStat();

    int getHealPowerStat();

    int getPiercingAttackStat();

    WeaponClass getWeaponClass();

    default String getCharacteristics() {
        String chars = String.format("{health: %s, attack: %s, defence: %s" +
                ", vampirism: %s, heal power: %s, class: %s}", getHealthStat(), getAttackStat(), getDefenceStat(),
                getVampirismStat(), getHealPowerStat(), getWeaponClass());

        return chars;
    }
}
