package org.example.battleunits;

import org.example.battlecommands.CombatUnitHitCommand;
import org.example.battlecommands.Command;
import org.example.battlecommands.ProcessCommandChain;
import org.example.battleunits.subsidiary.CombatUnitBehind;
import org.example.battleunits.subsidiary.CombatUnitType;
import org.example.battleunits.weapons.Weapon;

import java.util.Deque;
import java.util.List;

public class CombatUnitInArmyDecorator implements CombatUnit, CombatUnitBehind, ProcessCommandChain {
    private final CombatUnit combatUnit;
    private CombatUnitInArmyDecorator nextCombatUnit;

    public CombatUnitInArmyDecorator(CombatUnit combatUnit) {
        this.combatUnit = combatUnit;
    }

    @Override
    public CombatUnitInArmyDecorator getCombatUnitBehind() {
        return nextCombatUnit;
    }

    public void setCombatUnitBehind(CombatUnitInArmyDecorator nextCombatUnit) {
        this.nextCombatUnit = nextCombatUnit;
    }

    @Override
    public void processCommand(Command command, CombatUnit commandSender) {
        if (commandSender != this && combatUnit instanceof ProcessCommandChain processor) {
            processor.processCommand(command, commandSender);
        }
        if (nextCombatUnit != null) {
            nextCombatUnit.processCommand(command, this.unwrap());
        }
    }

    public CombatUnit unwrap() {
        return combatUnit;
    }

    @Override
    public int getAttack() {
        return combatUnit.getAttack();
    }

    @Override
    public int getHealth() {
        return combatUnit.getHealth();
    }

    @Override
    public void hit(CombatUnit opponent) {
        combatUnit.hit(opponent);
        processCommand(CombatUnitHitCommand.HEAL, this);
    }

    @Override
    public int receiveDamage(int damage) {
        return combatUnit.receiveDamage(damage);
    }

    @Override
    public void heal(int healingPoints) {
        combatUnit.heal(healingPoints);
    }

    @Override
    public int reduceHealth(int damageReceived) {
        return combatUnit.reduceHealth(damageReceived);
    }

    @Override
    public int getMaxHealth() {
        return combatUnit.getMaxHealth();
    }

    @Override
    public CombatUnit equipWeapon(Weapon weapon) {
        return combatUnit.equipWeapon(weapon);
    }

    @Override
    public Deque<Weapon> getWeaponsEquipped() {
        return combatUnit.getWeaponsEquipped();
    }

    @Override
    public CombatUnitType getCombatUnitType() {
        return combatUnit.getCombatUnitType();
    }

    @Override
    public String toString() {
        return combatUnit.toString();
    }

}
