package org.example.battleunits;

import org.example.weapons.Weapon;

public class HealerImpl extends WarriorImpl implements Healer {

    private int healPower;

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
    }

    @Override
    public String toString() {
        return super.toString() + "{heal:" + getHealPower() + "}";
    }

    @Override
    public void heal(Warrior commandSender) {
        WarriorImpl sender = (WarriorImpl) commandSender;
        sender.healedBy(getHealPower());
    }

    @Override
    public int getHealPower() {
        return Math.max(healPower + healPowerBonusFromWeapon(), 0);
    }

    private void setHealPower(int healthPoints) {
        this.healPower = healthPoints;
    }
}
