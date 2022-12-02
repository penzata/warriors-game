package org.example.battlecommands;

import org.example.battleunits.Warrior;

@FunctionalInterface
public interface ProcessCommandChain {

    void processCommand(Command command, Warrior commandSender);
}
