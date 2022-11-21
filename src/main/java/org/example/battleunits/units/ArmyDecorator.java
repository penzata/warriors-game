package org.example.battleunits.units;

public class ArmyDecorator implements ArmyUnit {

    protected WarriorUnit warriorUnit;

    public ArmyDecorator(WarriorUnit warriorUnit) {
        this.warriorUnit = warriorUnit;
    }

}
