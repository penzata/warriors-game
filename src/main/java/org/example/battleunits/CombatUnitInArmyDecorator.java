package org.example.battleunits;

import org.example.battlecommands.CombatUnitHitCommand;
import org.example.battlecommands.Command;
import org.example.battlecommands.ProcessCommandChain;
import org.example.battleunits.characteristics.Attack;
import org.example.battleunits.subsidiary.CanReceiveDamage;
import org.example.battleunits.subsidiary.CombatUnitBehind;
import org.example.battleunits.subsidiary.CombatUnitType;
import org.example.battleunits.weapons.Weapon;

public class CombatUnitInArmyDecorator implements CombatUnit, CombatUnitBehind, ProcessCommandChain {
    private CombatUnit combatUnit;
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

    public Warrior unwrap() {
        return (WarriorImpl) combatUnit;
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
    public void hit(CanReceiveDamage opponent) {
        combatUnit.hit(opponent);
        processCommand(CombatUnitHitCommand.HEAL, this);
    }

    @Override
    public void receiveDamage(Attack damageDealer) {
        combatUnit.receiveDamage(damageDealer);
    }

    @Override
    public void reduceHealth(int damage) {
        combatUnit.reduceHealth(damage);
    }

    @Override
    public int getInitialHealth() {
        return combatUnit.getInitialHealth();
    }

    @Override
    public CombatUnit equipWeapon(Weapon weapon) {
        return combatUnit.equipWeapon(weapon);
    }

    @Override
    public CombatUnitType getCombatUnitType() {
        return combatUnit.getCombatUnitType();
    }

    @Override
    public String toString() {
        return combatUnit.toString();
    }

    @Override
    public int healthBonusFromWeapon() {
        return combatUnit.healthBonusFromWeapon();
    }

    @Override
    public int attackBonusFromWeapon() {
        return combatUnit.attackBonusFromWeapon();
    }

    @Override
    public int defenceBonusFromWeapon() {
        return combatUnit.defenceBonusFromWeapon();
    }

    @Override
    public int vampirismBonusFromWeapon() {
        return combatUnit.vampirismBonusFromWeapon();
    }

    @Override
    public int healPowerBonusFromWeapon() {
        return combatUnit.healPowerBonusFromWeapon();
    }

    @Override
    public int piercingAttackBonusFromWeapon() {
        return combatUnit.piercingAttackBonusFromWeapon();
    }

    @Override
    public void healedBy(int healingPoints) {
        combatUnit.healedBy(healingPoints);
    }
}
