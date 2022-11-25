package org.example.battleunits.subsidiary;

import org.example.battleunits.Army;

@FunctionalInterface
public interface ProcessCommandChain {

    void processCommand(Command command, Army.ArmyWarriorUnitDecorator commandSender);
}
