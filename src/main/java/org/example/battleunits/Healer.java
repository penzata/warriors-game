package org.example.battleunits;

import org.example.characteristics.HealPower;
import org.example.subsidiary.CombatUnitHitCommand;
import org.example.subsidiary.Command;
import org.example.subsidiary.ProcessCommandChain;

public interface Healer extends Warrior, HealPower, ProcessCommandChain {

    /**
     * @return Healer object with default health(60), attack(0) & heal power(2).
     */
    static Healer create() {
        return new HealerImpl();
    }

    @Override
    default void hit(Warrior opponent) {
        //do nothing, has no attack points
    }

    @Override
    default void processCommand(Command command, Warrior commandSender) {
        if (isAlive() && command.equals(CombatUnitHitCommand.HEAL)) {
            heal(commandSender);
        }
    }

    void heal(Warrior commandSender);
}
