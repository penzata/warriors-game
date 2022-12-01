package org.example.demo;

import org.example.battleunits.Defender;
import org.example.battleunits.Knight;
import org.example.battleunits.Vampire;
import org.example.weapons.Weapon;
import org.example.weapons.WeaponType;

public class Main {

    public static void main(String[] args) {
        Weapon shield = WeaponType.SHIELD;
        Weapon katana = WeaponType.KATANA;

        Knight knight = Knight.create();
        Defender defender = Defender.create();
        knight.equipWeapon(shield).equipWeapon(shield);
        defender.equipWeapon(shield);
        Vampire vampire = Vampire.create();
        vampire.equipWeapon(katana);


        System.out.println(knight);
        System.out.println(defender);
        System.out.println(vampire);
    }
}