package org.example.battleunits;

import lombok.extern.slf4j.Slf4j;
import org.example.battleunits.subsidiary.CombatUnitType;

@Slf4j
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
    public CombatUnitType getCombatType() {
        return CombatUnitType.HEALER;
    }

    @Override
    public void heal(Warrior commandSender) {
        //casting to Warrior concrete implementation because healedBy() is private method of the class
        WarriorImpl sender = (WarriorImpl) commandSender;
        if ((sender.getHealth() < sender.getInitialHealth()) && (getMedKit() > 0)) {
            sender.healedBy(getHealPower());
            log.atTrace().log("[health of {} after been healed by healer]", sender);
            setMedKits(getMedKit() - 1);
        }
    }

    @Override
    public int getHealPower() {
        return Math.max(healPower + healPowerBonusFromWeapon(), 0);
    }

    @Override
    public int getMedKit() {
        return medKit;
    }

    private void setMedKits(int medKit) {
        this.medKit = medKit;
    }

    private void setHealPower(int healthPoints) {
        this.healPower = healthPoints;
    }
}