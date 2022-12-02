package org.example.battleunits;

import org.example.battleunits.weapons.Weapon;
import org.example.iterators.AliveUnitIterate;
import org.example.iterators.InfGenerator;
import org.example.iterators.StraightIterate;

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
    private Warlord onlyOneWarlord;
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
        if (warrior instanceof Warlord) {
            if (onlyOneWarlord != null) {
                return;
            }
            onlyOneWarlord = (Warlord) warrior;
        }
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
        try {
            army.get(position).equipWeapon(weapon);
        } catch (IndexOutOfBoundsException ex) {
            army.get(army.size() - 1).equipWeapon(weapon);
        }
    }

    @Override
    public void moveUnits() {
        if (onlyOneWarlord != null) {
            InfGenerator<Warrior> newArragendarmy = onlyOneWarlord.rearrangeArmy(this);
            warriorInFront = warriorBehind = null;
             for (var warrior : newArragendarmy) {
                 addBattleUnit(warrior);
             }
        }
    }

}