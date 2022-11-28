package org.example.battleunits;

import org.example.characteristics.Attack;
import org.example.subsidiary.Command;
import org.example.subsidiary.ProcessCommandChain;
import org.example.subsidiary.CombatUnitBehind;
import org.example.subsidiary.CombatUnitHitCommand;
import org.example.weapons.Weapon;

public class CombatUnitInArmyDecorator implements CombatUnit, CombatUnitBehind, ProcessCommandChain {
    private CombatUnit warriorUnit;
    private CombatUnitInArmyDecorator nextWarrior;

    public CombatUnitInArmyDecorator(CombatUnit warriorUnit) {
        this.warriorUnit = warriorUnit;
    }

    @Override
    public CombatUnitInArmyDecorator getWarriorBehind() {
        return nextWarrior;
    }

    public void setWarriorBehind(CombatUnitInArmyDecorator nextWarrior) {
        this.nextWarrior = nextWarrior;
    }

    @Override
    public String toString() {
        return "" + warriorUnit;
    }

    @Override
    public void processCommand(Command command, CombatUnitInArmyDecorator commandSender) {
        if (warriorUnit instanceof ProcessCommandChain processor && !warriorUnit.equals(commandSender.unwrap())) {
            processor.processCommand(command, commandSender);
        }
        if (nextWarrior != null) {
            nextWarrior.processCommand(command, this);
        }
    }

    public Warrior unwrap() {
        return (Warrior) warriorUnit;
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
    public void hit(CombatUnit opponent) {
        warriorUnit.hit(opponent);
        processCommand(CombatUnitHitCommand.HEAL, this);
    }

    @Override
    public void receiveDamage(Attack damageDealer) {
        warriorUnit.receiveDamage(damageDealer);
    }

    @Override
    public void equipWeapon(Weapon weapon) {
        warriorUnit.equipWeapon(weapon);
    }
}
