package org.example.weapons;

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

    default int getCharacteristics(String characteristics) {
        return 0;
    }

    class WeaponBuilder {
        int health;
        int attack;
        int defence;
        int vampirism;
        int healPower;
        int piercingAttack;

        public WeaponBuilder healthStat(int health) {
            this.health = health;
            return this;
        }

        public WeaponBuilder attackStat(int attack) {
            this.attack = attack;
            return this;
        }

        public WeaponBuilder defenceStat(int defence) {
            this.defence = defence;
            return this;
        }

        public WeaponBuilder vampirismStat(int vampirism) {
            this.vampirism = vampirism;
            return this;
        }

        public WeaponBuilder piercingAttackStat(int piercingAttack) {
            this.piercingAttack = piercingAttack;
            return this;
        }

        public WeaponBuilder healPowerStat(int healPower) {
            this.healPower = healPower;
            return this;
        }

        public Weapon build() {
            return new Weapon() {
                @Override
                public String toString() {
                    return "CUSTOM_WEAPON";
                }

                @Override
                public int getHealthStat() {
                    return health;
                }

                @Override
                public int getAttackStat() {
                    return attack;
                }

                @Override
                public int getDefenceStat() {
                    return defence;
                }

                @Override
                public int getVampirismStat() {
                    return vampirism;
                }

                @Override
                public int getHealPowerStat() {
                    return healPower;
                }

                @Override
                public int getPiercingAttackStat() {
                    return piercingAttack;
                }
            };
        }
    }

}
