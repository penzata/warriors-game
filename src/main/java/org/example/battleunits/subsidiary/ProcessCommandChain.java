package org.example.battleunits.subsidiary;

import org.example.battleunits.ArmyCombatUnitDecorator;

@FunctionalInterface
public interface ProcessCommandChain {

    void processCommand(Command command, ArmyCombatUnitDecorator commandSender);
}
