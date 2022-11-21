package org.example.battleunits.units;

import org.example.battleunits.common.InfGenerator;

import java.util.Iterator;

public interface ArmyUnit extends InfGenerator<WarriorUnit> {

    Iterator<WarriorUnit> getAliveUnit();
}
