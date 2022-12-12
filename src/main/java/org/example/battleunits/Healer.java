package org.example.battleunits;

import org.example.battlecommands.CombatUnitHitCommand;
import org.example.battlecommands.Command;
import org.example.battlecommands.ProcessCommandChain;
import org.example.battleunits.characteristics.HealPower;
import org.example.battleunits.subsidiary.CombatUnitType;
import org.example.battleunits.weapons.Weapon;
import org.example.battleunits.weapons.WeaponFactory;

public class Healer extends CombatUnitImpl implements HealPower, ProcessCommandChain {
    private static final int DEFAULT_HEALTH = 60;
    private static final int DEFAULT_ATTACK = 0;
    private static final int DEFAULT_HEAL_POWER = 2;
    private int healPower;
    private int medKit = 10;

    Healer() {
        super(DEFAULT_HEALTH, DEFAULT_ATTACK);
        this.healPower = DEFAULT_HEAL_POWER;
    }

    Healer (int health, int attack, int healPower) {
        super(health, attack);
        this.healPower = healPower;
    }

    @Override
    public String toString() {
        return super.toString() + "{heal:" + getHealPower() + "}";
    }

    @Override
    public int getHealPower() {
        return Math.max(healPower + getHealPowerStatFromWeapon(), 0);
    }

    private int getHealPowerStatFromWeapon() {
        return getWeaponsEquipped().stream()
                .mapToInt(Weapon::getHealPowerStat)
                .sum();
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

    private void changeMedKitQuantity(int quantity) {
        setMedKits(quantity);
    }

    private void setMedKits(int medKit) {
        this.medKit = medKit;
    }

    @Override
    public void hit(CombatUnit opponent) {
//        TODO think of handling Healer vs. Healer fight
        if (opponent instanceof Healer) {
            equipWeapon(WeaponFactory.KATANA);
            equipWeapon(WeaponFactory.KATANA);
            equipWeapon(WeaponFactory.KATANA);
        }
    }

    @Override
    public void processCommand(Command command, CombatUnit commandSender) {
        if (isAlive() && command.equals(CombatUnitHitCommand.HEAL)) {
            healCombatUnit(commandSender);
        }
    }

    @Override
    public void healCombatUnit(CombatUnit commandSender) {
        if ((commandSender.getHealth() < commandSender.getMaxHealth()) && (getMedKit() > 0)) {
            commandSender.heal(getHealPower());
            changeMedKitQuantity(getMedKit() - 1);
        }
    }

}