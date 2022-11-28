package org.example.battleunits;

import org.example.battleunits.iterators.AliveUnitIterate;
import org.example.battleunits.iterators.StraightIterate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Supplier;

public class Army implements ArmyUnit {
    private static final Logger LOGGER = LoggerFactory.getLogger(Army.class);
    private Collection<CombatUnit> army;
    private ArmyCombatUnitDecorator lastWarrior;


    public Army(Supplier<CombatUnit> factory, Integer numberOfUnits) {
        this.army = new ArrayList<>();
        addBattleUnits(factory, numberOfUnits);
    }

    public Army addBattleUnits(Supplier<CombatUnit> factory, Integer numberOfUnits) {
        for (int i = 0; i < numberOfUnits; i++) {
            addBattleUnit(factory.get());
        }
        return this;
    }

    private void addBattleUnit(CombatUnit warrior) {
        ArmyCombatUnitDecorator wrapped = new ArmyCombatUnitDecorator(warrior);
        if (lastWarrior != null) {
            lastWarrior.setWarriorBehind(wrapped);
        }
        lastWarrior = wrapped;
        this.army.add(wrapped);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {

        return "Army{units: "
                + army.size()
//the two rows below are for logger debugging
//                + " "
//                + army
                + '}';
    }

    @Override
    public Iterator<CombatUnit> nextInLine() {
        return new StraightIterate(army);
    }

    @Override
    public Iterator<CombatUnit> getAliveUnit() {
        return new AliveUnitIterate(army);
    }

    @Override
    public void removeDeadBodies() {
        army.removeIf(warriorUnit -> !warriorUnit.isAlive());
    }

}