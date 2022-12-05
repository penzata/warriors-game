package org.example.battleunits;

import org.example.battleunits.subsidiary.CombatUnitType;

public class HealerImpl extends WarriorImpl implements Healer {
    private int healPower;
    private int medKit = 10;

    HealerImpl() {
        super(60, 0);
        this.healPower = 2;
    }

    HealerImpl(int health, int attack) {
        super(health, attack);
        this.healPower = 2;
    }

    /**
     * @param healer - copy constructor
     */
    HealerImpl(HealerImpl healer) {
        super(healer);
        this.healPower = 2;
    }

    @Override
    public String toString() {
        return super.toString() + "{heal:" + getHealPower() + "}";
    }

    @Override
    public int getHealPower() {
        return Math.max(healPower + getHealPowerStatFromWeapon(), 0);
    }

    private void setHealPower(int healthPoints) {
        this.healPower = healthPoints;
    }

    @Override
    public CombatUnitType getCombatUnitType() {
        return CombatUnitType.HEALER;
    }

    @Override
    public int getMedKit() {
        return medKit;
    }

    @Override
    public void changeMedKitQuantity(int quantity) {
        setMedKits(quantity);
    }

    private void setMedKits(int medKit) {
        this.medKit = medKit;
    }
}