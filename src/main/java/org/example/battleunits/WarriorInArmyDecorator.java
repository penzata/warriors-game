package org.example.battleunits;

import org.example.characteristics.Attack;
import org.example.subsidiary.CombatUnitBehind;
import org.example.subsidiary.CombatUnitHitCommand;
import org.example.subsidiary.Command;
import org.example.subsidiary.ProcessCommandChain;
import org.example.weapons.Weapon;

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
    public String toString() {
        return "" + warriorUnit;
    }

    @Override
    public void processCommand(Command command, Warrior commandSender) {
        if (warriorUnit instanceof ProcessCommandChain processor) {
            processor.processCommand(command, commandSender);
        }
        if (nextWarrior != null) {
            nextWarrior.processCommand(command, nextWarrior.unwrap());
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
        processCommand(CombatUnitHitCommand.HEAL, warriorUnit);
    }

    @Override
    public void receiveDamage(Attack damageDealer) {
        warriorUnit.receiveDamage(damageDealer);
    }

    @Override
    public Warrior equipWeapon(Weapon weapon) {
        return warriorUnit.equipWeapon(weapon);
    }
}
