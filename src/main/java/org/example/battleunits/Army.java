package org.example.battleunits;

import org.example.iterators.AliveUnitIterate;
import org.example.iterators.StraightIterate;
import org.example.weapons.Weapon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Supplier;

public class Army implements ArmyUnit {
    private static final Logger LOGGER = LoggerFactory.getLogger(Army.class);
    private Collection<CombatUnit> army;
    private CombatUnitInArmyDecorator lastWarrior;
    private Map<Integer, CombatUnit> positionInArmy;


    public Army(Supplier<CombatUnit> factory, Integer numberOfUnits) {
        this.army = new ArrayList<>();
        this.positionInArmy = new HashMap<>();
        addBattleUnits(factory, numberOfUnits);
    }

    public Army addBattleUnits(Supplier<CombatUnit> factory, Integer numberOfUnits) {
        for (int i = 0; i < numberOfUnits; i++) {
            addBattleUnit(factory.get(), i);
        }
        return this;
    }

    private void addBattleUnit(CombatUnit warrior, int position) {
        CombatUnitInArmyDecorator wrapped = new CombatUnitInArmyDecorator(warrior);
        if (lastWarrior != null) {
            lastWarrior.setWarriorBehind(wrapped);
        }
        lastWarrior = wrapped;
        this.army.add(wrapped);
        this.positionInArmy.put(position, wrapped);
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

    @Override
    public void equipWarriorAtPosition(int position, Weapon weapon) {
        if (positionInArmy.containsKey(position)) {
            positionInArmy.get(position).equipWeapon(weapon);
        }
    }

}