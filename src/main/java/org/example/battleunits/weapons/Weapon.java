package org.example.battleunits.weapons;

public interface Weapon {

    String getName();

    int getHealthStat();

    int getAttackStat();

    int getDefenceStat();

    int getVampirismStat();

    int getHealPowerStat();

    int getPiercingAttackStat();

    WeaponClass getWeaponClass();

    default String getCharacteristics() {
        return String.format("%s{health: %s, attack: %s, defence: %s" +
                ", vampirism: %s, heal power: %s, weapon class: %s}",
                getName(), getHealthStat(), getAttackStat(), getDefenceStat(),
                getVampirismStat(), getHealPowerStat(), getWeaponClass());
    }

}
