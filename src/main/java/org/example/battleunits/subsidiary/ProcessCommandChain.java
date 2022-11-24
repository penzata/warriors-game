package org.example.battleunits.subsidiary;

import org.example.battleunits.units.WarriorUnit;

public interface ProcessCommandChain {

    void processCommand (Command command, WarriorUnit commandSender);
}
