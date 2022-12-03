package org.example.battleunits;

import org.example.battleunits.characteristics.Attack;
import org.example.battleunits.subsidiary.CombatUnitBehind;
import org.example.battlecommands.CombatUnitHitCommand;
import org.example.battlecommands.Command;
import org.example.battlecommands.ProcessCommandChain;
import org.example.battleunits.weapons.Weapon;

public class WarriorInArmyDecorator implements Warrior, CombatUnitBehind, ProcessCommandChain {
    private Warrior warriorUnit;
    private WarriorInArmyDecorator nextWarrior;

    public WarriorInArmyDecorator(Warrior warriorUnit) {
        this.warriorUnit = warriorUnit;
    }

    @Override
    public WarriorInArmyDecorator getWarriorBehind() {
        return nextWarrior;
    }

    public void setWarriorBehind(WarriorInArmyDecorator nextWarrior) {
        this.nextWarrior = nextWarrior;
    }

    @Override
    public void processCommand(Command command, Warrior commandSender) {
        if (commandSender != this && warriorUnit instanceof ProcessCommandChain processor) {
            processor.processCommand(command, commandSender);
        }
        if (nextWarrior != null) {
            nextWarrior.processCommand(command, this.unwrap());
        }
    }

    public WarriorImpl unwrap() {
        return (WarriorImpl) warriorUnit;
    }

    @Override
    public int getAttack() {
        return warriorUnit.getAttack();
    }

    @Override
    public int getHealth() {
        return warriorUnit.getHealth();
    }

    @Override
    public void hit(Warrior opponent) {
        warriorUnit.hit(opponent);
        processCommand(CombatUnitHitCommand.HEAL, this);
    }

    @Override
    public void receiveDamage(Attack damageDealer) {
        warriorUnit.receiveDamage(damageDealer);
    }

    @Override
    public void reduceHealth(int damage) {
        warriorUnit.reduceHealth(damage);
    }

    @Override
    public Warrior equipWeapon(Weapon weapon) {
        return warriorUnit.equipWeapon(weapon);
    }

    @Override
    public String toString() {
        return warriorUnit.toString();
    }

    @Override
    public int healthBonusFromWeapon() {
        return warriorUnit.healthBonusFromWeapon();
    }

    @Override
    public int attackBonusFromWeapon() {
        return warriorUnit.attackBonusFromWeapon();
    }

    @Override
    public int defenceBonusFromWeapon() {
        return warriorUnit.defenceBonusFromWeapon();
    }

    @Override
    public int vampirismBonusFromWeapon() {
        return warriorUnit.vampirismBonusFromWeapon();
    }

    @Override
    public int healPowerBonusFromWeapon() {
        return warriorUnit.healPowerBonusFromWeapon();
    }

    @Override
    public int piercingAttackBonusFromWeapon() {
        return warriorUnit.piercingAttackBonusFromWeapon();
    }
}
