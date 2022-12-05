package org.example.battleunits.weapons;

public interface Weapon {

    static WeaponBuilder builder() {
        return new WeaponBuilder();
    }

    int getHealthStatFromWeapon();

    int getAttackStatFromWeapon();

    int getDefenceStatFromWeapon();

    int getVampirismStatFromWeapon();

    int getHealPowerStatFromWeapon();

    int getPiercingAttackStatFromWeapon();

    void decreaseDurability();


    default String getCharacteristics() {
        return String.format("{health: %s, attack: %s, defence: %s" +
                ", vampirism: %s, heal power: %s}", getHealthStatFromWeapon(), getAttackStatFromWeapon(), getDefenceStatFromWeapon(),
                getVampirismStatFromWeapon(), getHealPowerStatFromWeapon());
    }

}
