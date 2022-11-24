package org.example.battleunits.subsidiary;

import org.example.battleunits.Army;

public interface ProcessCommandChain {

    void processCommand(Command command, Army.ArmyWarriorUnitDecorator commandSender);
}
