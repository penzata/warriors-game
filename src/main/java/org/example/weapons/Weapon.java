package org.example.weapons;

public interface Weapon {

    static WeaponBuilder builder() {
        return new WeaponBuilder();
    }

    int getWeaponHealth();

    int getWeaponAttack();

    int getWeaponDefence();

    int getWeaponVampirism();

    int getWeaponHealPower();

    int getWeaponPiercingAttack();

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

        WeaponBuilder healthStat(int health) {
            this.health = health;
            return this;
        }

        WeaponBuilder attackStat(int attack) {
            this.attack = attack;
            return this;
        }

        WeaponBuilder defenceStat(int defence) {
            this.defence = defence;
            return this;
        }

        WeaponBuilder vampirismStat(int vampirism) {
            this.vampirism = vampirism;
            return this;
        }

        WeaponBuilder piercingAttackStat(int piercingAttack) {
            this.piercingAttack = piercingAttack;
            return this;
        }

        WeaponBuilder healPowerStat(int healPower) {
            this.healPower = healPower;
            return this;
        }

        Weapon build() {
            return new Weapon() {
                @Override
                public int getWeaponHealth() {
                    return health;
                }

                @Override
                public int getWeaponAttack() {
                    return attack;
                }

                @Override
                public int getWeaponDefence() {
                    return defence;
                }

                @Override
                public int getWeaponVampirism() {
                    return vampirism;
                }

                @Override
                public int getWeaponHealPower() {
                    return healPower;
                }

                @Override
                public int getWeaponPiercingAttack() {
                    return piercingAttack;
                }
            };
        }
    }

}
