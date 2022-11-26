package org.example.battleunits.units;

import org.example.battleunits.common.InfGenerator;

import java.util.Iterator;

public interface ArmyUnit extends InfGenerator<WarriorUnit> {

    /**
     * @return iterator over Army for put in army-in-line order turn-based fight.
     */
    Iterator<WarriorUnit> getAliveUnit();

    /**
     * @return iterator over Army for one-on-one duel fights between army units based on theirs position in army.
     */
    Iterator<WarriorUnit> nextInLine();

    void removeDeadBodies();

    }
