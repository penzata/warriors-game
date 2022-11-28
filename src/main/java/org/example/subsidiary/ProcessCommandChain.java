package org.example.subsidiary;

import org.example.battleunits.CombatUnitInArmyDecorator;

@FunctionalInterface
public interface ProcessCommandChain {

    void processCommand(Command command, CombatUnitInArmyDecorator commandSender);
}
