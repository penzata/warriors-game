package org.example.demo;

import org.example.battleunits.Knight;
import org.example.weapons.Weapon;
import org.example.weapons.WeaponType;

public class Main {

    public static void main(String[] args) {
        Weapon weapon = WeaponType.SHIELD;

        Knight knight = Knight.create();
        knight.equipWeapon(weapon);
        System.out.println(knight);
    }
}