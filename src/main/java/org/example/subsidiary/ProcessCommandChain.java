package org.example.subsidiary;

import org.example.battleunits.Warrior;
import org.example.battleunits.WarriorInArmyDecorator;

@FunctionalInterface
public interface ProcessCommandChain {

    void processCommand(Command command, Warrior commandSender);
}
