package org.example.battlecommands;

import org.example.battleunits.CombatUnit;

@FunctionalInterface
public interface ProcessCommandChain {

    void processCommand(Command command, CombatUnit commandSender);
}
