package org.example.battleunits.weapons;

public interface Weapon {

    int getHealthStat();

    int getAttackStat();

    int getDefenceStat();

    int getVampirismStat();

    int getHealPowerStat();

    int getPiercingAttackStat();

    void decreaseDurability();

    default String getCharacteristics() {
        return String.format("{health: %s, attack: %s, defence: %s" +
                ", vampirism: %s, heal power: %s}", getHealthStat(), getAttackStat(), getDefenceStat(),
                getVampirismStat(), getHealPowerStat());
    }

}
