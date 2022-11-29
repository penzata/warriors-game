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
    public Warrior equipWeapon(Weapon weapon) {
        super.equipWeapon(weapon);
        setHealPower(Math.max(getHealPower() + weapon.getWeaponHealPower(), 0));
        return this;
    }

    @Override
    public int getHealPower() {
        return healPower;
    }

    private void setHealPower(int healthPoints) {
        this.healPower = healthPoints;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{h:" + getHealth() +
                ", a:" + getAttack() +
                ", heal:" + getHealPower() + "}";
    }

    @Override
    public void heal(Warrior commandSender) {
        WarriorImpl sender = (WarriorImpl) commandSender;
        sender.healedBy(getHealPower());
    }

}
