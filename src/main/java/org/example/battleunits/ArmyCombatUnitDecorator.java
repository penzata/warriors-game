package org.example.battleunits;

import org.example.battleunits.characteristics.Attack;
import org.example.battleunits.subsidiary.Command;
import org.example.battleunits.subsidiary.ProcessCommandChain;
import org.example.battleunits.subsidiary.WarriorUnitBehind;
import org.example.battleunits.subsidiary.WarriorUnitHitCommand;

public class ArmyCombatUnitDecorator implements CombatUnit, WarriorUnitBehind, ProcessCommandChain {
    private CombatUnit warriorUnit;
    private ArmyCombatUnitDecorator nextWarrior;

    public ArmyCombatUnitDecorator(CombatUnit warriorUnit) {
        this.warriorUnit = warriorUnit;
    }

    @Override
    public ArmyCombatUnitDecorator getWarriorBehind() {
        return nextWarrior;
    }

    public void setWarriorBehind(ArmyCombatUnitDecorator nextWarrior) {
        this.nextWarrior = nextWarrior;
    }

    @Override
    public String toString() {
        return "" + warriorUnit;
    }

    @Override
    public void processCommand(Command command, ArmyCombatUnitDecorator commandSender) {
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
        processCommand(WarriorUnitHitCommand.HEAL, this);
    }

    @Override
    public void receiveDamage(Attack damageDealer) {
        warriorUnit.receiveDamage(damageDealer);
    }
}
