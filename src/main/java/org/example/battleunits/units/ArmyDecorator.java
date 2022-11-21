package org.example.battleunits.units;

import java.util.Iterator;

public class ArmyDecorator implements ArmyUnit {

    protected WarriorUnit warriorUnit;

    public ArmyDecorator(WarriorUnit warriorUnit) {
        this.warriorUnit = warriorUnit;
    }

    @Override
    public Iterator<WarriorUnit> getAliveUnit() {
        return null;
    }
}
