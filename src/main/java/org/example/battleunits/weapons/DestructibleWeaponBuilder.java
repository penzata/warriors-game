package org.example.battleunits.weapons;

public class DestructibleWeaponBuilder {
    Weapon weapon;
    int durability;
    int decreaseDurabilityStep;

    private DestructibleWeaponBuilder() {
    }

    public static DestructibleWeaponBuilder newInstance() {
        return new DestructibleWeaponBuilder();
    }

    public DestructibleWeaponBuilder durability(int durability) {
        this.durability = durability;
        return this;
    }

    public DestructibleWeaponBuilder decreaseDurabilityStep(int decreaseDurabilityStep) {
        this.decreaseDurabilityStep = decreaseDurabilityStep;
        return this;
    }

    public DestructibleWeaponBuilder weapon(Weapon weapon) {
        this.weapon = weapon;
        return this;
    }

    public Weapon build() {
        return new Weapon() {
            @Override
            public String getName() {
                return weapon.getName();
            }

            @Override
            public int getHealthStat() {
                return weapon.getHealthStat();
            }

            @Override
            public int getAttackStat() {
                return weapon.getAttackStat();
            }

            @Override
            public int getDefenceStat() {
                return weapon.getDefenceStat();
            }

            @Override
            public int getVampirismStat() {
                return weapon.getVampirismStat();
            }

            @Override
            public int getHealPowerStat() {
                return weapon.getHealPowerStat();
            }

            @Override
            public int getPiercingAttackStat() {
                return weapon.getPiercingAttackStat();
            }

            @Override
            public WeaponClass getWeaponClass() {
                return weapon.getWeaponClass();
            }

        };
    }

}
