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

    default String getCharacteristics() {
        return String.format("{health: %s, attack: %s, defence: %s" +
                ", vampirism: %s, heal power: %s, class: %s}", getHealthStatFromWeapon(), getAttackStatFromWeapon(), getDefenceStatFromWeapon(),
                getVampirismStatFromWeapon(), getHealPowerStatFromWeapon());
    }

}
