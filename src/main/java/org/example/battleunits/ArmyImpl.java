package org.example.battleunits;

import org.example.iterators.AliveUnitIterate;
import org.example.iterators.StraightIterate;
import org.example.weapons.Weapon;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Supplier;

public class ArmyImpl implements Army {
    private static int idSequence = 0;
    private final int id = ++idSequence;
    private WarriorInArmyDecorator warriorInFront;
    private WarriorInArmyDecorator warriorBehind;
    private Map<Integer, WarriorInArmyDecorator> army;


    public ArmyImpl(Supplier<Warrior> factory, Integer numberOfUnits) {
        this.army = new LinkedHashMap<>();
        addBattleUnits(factory, numberOfUnits);
    }

    public ArmyImpl addBattleUnits(Supplier<Warrior> factory, Integer numberOfUnits) {
        for (int i = 0; i < numberOfUnits; i++) {
            addBattleUnit(factory.get(), i);
        }
        return this;
    }

    private void addBattleUnit(Warrior warrior, int position) {
        WarriorInArmyDecorator wrapped = new WarriorInArmyDecorator(warrior);
        if (warriorInFront == null) {
            warriorInFront = wrapped;
        } else {
            warriorBehind.setWarriorBehind(wrapped);
        }
        warriorBehind = wrapped;
        this.army.put(position, wrapped);
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(",\n", "Army#%3d[%n ".formatted(id), "\n]");
        sj.setEmptyValue("Army#%3d[ DEAD ]".formatted(id));
        for (Warrior warrior : this) {
            sj.add(warrior.toString());
        }
        return sj.toString();
    }

    @Override
    public Iterator<Warrior> nextInLine() {
        return new StraightIterate(warriorInFront);
    }

    @Override
    public Iterator<Warrior> getAliveUnit() {
        return new AliveUnitIterate(warriorInFront);
    }

    @Override
    public void removeDeadBodies() {
        army.values().removeIf(combatUnit -> !combatUnit.isAlive());
    }

    @Override
    public void equipWarriorAtPosition(int position, Weapon weapon) {
        if (army.containsKey(position)) {
            army.get(position).equipWeapon(weapon);
        }
    }

}