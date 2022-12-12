package org.example.battleunits.weapons;

import lombok.Getter;

@Getter
public class DestructibleWeapon extends WeaponImpl {

    private final int decreaseDurabilityStep;
    private int durability;
    private Weapon weapon;
    private DestructibleWeaponBuilder destructibleBuilder = destructibleBuilder();

    public DestructibleWeapon(WeaponBuilder weaponBuilder) {
        super(weaponBuilder);
        this.weapon = destructibleBuilder.weapon;
        this.durability = destructibleBuilder.durability;
        this.decreaseDurabilityStep = destructibleBuilder.decreaseDurabilityStep;
    }

    public static DestructibleWeaponBuilder destructibleBuilder() {
        return DestructibleWeaponBuilder.newInstance();
    }

    public void decreaseDurability() {
        if (durability > 0 && decreaseDurabilityStep > 0) {
            durability -= decreaseDurabilityStep;
        }
        if (durability < 0) {
            breakWeapon();
        }
    }

    @Override
    public String getCharacteristics() {
        return super.getCharacteristics() + String.format("{durability: %s, decrease durability step: %s}",
                getDurability(), getDecreaseDurabilityStep());
    }

    private void breakWeapon() {
    }

}
