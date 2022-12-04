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

//    TODO make this method returns all the stats
    default int getCharacteristics(String characteristics) {
        return 0;
    }
}
