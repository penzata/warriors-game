package org.example.battleunits.weapons;

public class WeaponBuilder {
    private static int idSequence = 0;
    /**
     * used to keep track on created custom weapons at debugging
     */
    private final int id = ++idSequence;
    int health;
    int attack;
    int defence;
    int vampirism;
    int healPower;
    int piercingAttack;
    int decreaseDurabilityStep;
    int durability;

    WeaponBuilder() {
    }

    public WeaponBuilder setHealthStat(int health) {
        this.health = health;
        return this;
    }

    public WeaponBuilder setAttackStat(int attack) {
        this.attack = attack;
        return this;
    }

    public WeaponBuilder setDefenceStat(int defence) {
        this.defence = defence;
        return this;
    }

    public WeaponBuilder setVampirismStat(int vampirism) {
        this.vampirism = vampirism;
        return this;
    }

    public WeaponBuilder setPiercingAttackStat(int piercingAttack) {
        this.piercingAttack = piercingAttack;
        return this;
    }

    public WeaponBuilder setHealPowerStat(int healPower) {
        this.healPower = healPower;
        return this;
    }

    public WeaponBuilder setDecreaseDurabilityStep(int decreaseDurabilityStep) {
        this.decreaseDurabilityStep = decreaseDurabilityStep;
        return this;
    }

    public WeaponBuilder setDurability(int durability) {
        this.durability = durability;
        return this;
    }

    public Weapon build() {
        return new Weapon() {
            @Override
            public String toString() {
                return "CUSTOM_WEAPON#%02d".formatted(id);
            }

            @Override
            public int getHealthStatFromWeapon() {
                return health;
            }

            @Override
            public int getAttackStatFromWeapon() {
                return attack;
            }

            @Override
            public int getDefenceStatFromWeapon() {
                return defence;
            }

            @Override
            public int getVampirismStatFromWeapon() {
                return vampirism;
            }

            @Override
            public int getHealPowerStatFromWeapon() {
                return healPower;
            }

            @Override
            public int getPiercingAttackStatFromWeapon() {
                return piercingAttack;
            }

            @Override
            public void decreaseDurability() {
            }
        };
    }


}