package org.example.battleunits;

import org.example.iterators.AliveUnitIterate;
import org.example.iterators.StraightIterate;
import org.example.weapons.Weapon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Supplier;

public class ArmyImpl implements Army {
    private static int idSequence = 0;
    /**
     * used to keep track on created objects at debugging
     */
    private final int id = ++idSequence;
    private WarriorInArmyDecorator warriorInFront;
    private WarriorInArmyDecorator warriorBehind;
    private List<Warrior> army;


    public ArmyImpl(Supplier<Warrior> factory, Integer numberOfUnits) {
        this.army = new ArrayList<>();
        addBattleUnits(factory, numberOfUnits);
    }

    public ArmyImpl addBattleUnits(Supplier<Warrior> factory, Integer numberOfUnits) {
        for (int i = 0; i < numberOfUnits; i++) {
            addBattleUnit(factory.get());
        }
        return this;
    }

    private void addBattleUnit(Warrior warrior) {
        WarriorInArmyDecorator wrapped = new WarriorInArmyDecorator(warrior);
        if (warriorInFront == null) {
            warriorInFront = wrapped;
        } else {
            warriorBehind.setWarriorBehind(wrapped);
        }
        warriorBehind = wrapped;
        this.army.add(wrapped);
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(",\n ", "Army#%2d --->%n[".formatted(id), "]");
        sj.setEmptyValue("Army#%2d[ DEAD ]".formatted(id));
        for (var warrior : army) {
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
    public void equipWarriorAtPosition(int position, Weapon weapon) {
        if (position < army.size()) {
            army.get(position).equipWeapon(weapon);
        }
    }

}