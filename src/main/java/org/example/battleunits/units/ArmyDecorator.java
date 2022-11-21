package org.example.battleunits.units;

import java.util.Iterator;

public class ArmyDecorator implements ArmyUnit {

    protected ArmyUnit army;

    public ArmyDecorator(ArmyUnit army) {
        this.army = army;
    }

    @Override
    public Iterator<WarriorUnit> getAliveUnit() {
        return null;
    }
}
