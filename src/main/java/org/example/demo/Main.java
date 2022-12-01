package org.example.demo;

import org.example.battleunits.Knight;
import org.example.weapons.Weapon;
import org.example.weapons.WeaponType;

public class Main {

    public static void main(String[] args) {
        Weapon weapon = WeaponType.SHIELD;
        System.out.println(weapon.getVampirismStat());
        Knight knight = Knight.create();
        System.out.println(knight.equipWeapon(weapon));
    }
}