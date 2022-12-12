package org.example.demo;

import org.example.battleunits.weapons.DestructibleWeapon;
import org.example.battleunits.weapons.Weapon;
import org.example.battleunits.weapons.WeaponFactory;

public class Main {

    public static void main(String[] args) {
        Weapon dasKatana = DestructibleWeapon.destructibleBuilder()
                .weapon(WeaponFactory.KATANA)
                .durability(20)
                .decreaseDurabilityStep(3)
                .build();

        Weapon katana = WeaponFactory.KATANA;

        System.out.println(katana.getCharacteristics());
        System.out.println(dasKatana.getCharacteristics());
        System.out.println(dasKatana.equals(katana));
    }
}