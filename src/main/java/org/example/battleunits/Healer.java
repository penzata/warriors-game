package org.example.battleunits;

import org.example.battleunits.characteristics.HealPower;
import org.example.battlecommands.CombatUnitHitCommand;
import org.example.battlecommands.Command;
import org.example.battlecommands.ProcessCommandChain;
import org.example.battleunits.subsidiary.CanReceiveDamage;

public interface Healer extends Warrior, HealPower, ProcessCommandChain {

    /**
     * @return Healer object with default health(60), attack(0) & heal power(2).
     */
    static Healer create() {
        return new HealerImpl();
    }

    @Override
    default void hit(CanReceiveDamage opponent) {
//        TODO think of handling Healer vs. Healer fight
        if (opponent instanceof Healer) {
        }
        //do nothing, has no attack points
    }

    @Override
    default void processCommand(Command command, Warrior commandSender) {
        if (isAlive() && command.equals(CombatUnitHitCommand.HEAL)) {
            heal(commandSender);
        }
    }

    default void heal(Warrior commandSender) {
        if ((commandSender.getHealth() < commandSender.getInitialHealth()) && (getMedKit() > 0)) {
            commandSender.healedBy(getHealPower());
            changeMedKitQuantity(getMedKit() - 1);
        }
    }

    void changeMedKitQuantity(int quantity);

}
