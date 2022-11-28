package org.example.battleunits.weapons;

public interface Weapon {

    /**
     * @return Sword with +5 health, +2 attack.
     */
    static Sword sword() {
        return new Sword(5, 2, 0, 0, 0);
    }

    /**
     * @return Shield with +20 health, -1 attack, +2 defence.
     */
    static Shield shield() {
        return new Shield(20, -1, 2, 0, 0);
    }

    /**
     * @return Great Axe with -15 health, +5 attack, -2 defence.
     */
    static GreatAxe greatAxe() {
        return new GreatAxe(-15, 5, -2, 10, 0);
    }

    /**
     * @return Katana with -20 health, +6 attack, -5 defence, +50 vampirism.
     */
    static Katana katana() {
        return new Katana(-20, 6, -5, 50, 0);
    }

    /**
     * @return Magic Wand with +30 health, +3 attack, +3 heal power.
     */
    static MagicWand magicWand() {
        return new MagicWand(30, 3, 0, 0, 3);
    }

    int getWeaponHealth();

    int getWeaponAttack();

    int getWeaponDefence();

    int getWeaponVampirism();
    int getWeaponHealPower();
}
