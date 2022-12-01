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
    public int getHealPower() {
        return healPower;
    }

    @Override
    public String toString() {
        return super.toString() + "{heal:" + healPower + "}";
    }

    private void setHealPower(int healthPoints) {
        this.healPower = healthPoints;
    }

    @Override
    public void heal(Warrior commandSender) {
        WarriorImpl sender = (WarriorImpl) commandSender;
        sender.healedBy(getHealPower());
    }

}
