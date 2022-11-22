package org.example.battleunits;

import org.example.battleunits.common.InfGenerator;
import org.example.battleunits.units.*;
import org.example.exceptions.DoesntExistException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Supplier;

public class Army implements ArmyUnit {
    private Collection<WarriorUnit> army;
    private WarriorUnitDecorator lastWarrior;

    /**
     * Constructs default Army with one Warrior.
     */
    public Army() {

        this(Warrior::new, 1);
    }

    public Army(Supplier<WarriorUnit> factory, Integer numberOfUnits) {
        this.army = new ArrayList<>();
        addBattleUnits(factory, numberOfUnits);
    }

    public Army addBattleUnits(Supplier<WarriorUnit> factory, Integer numberOfUnits) {
        for (int i = 0; i < numberOfUnits; i++) {
            addBattleUnit(factory.get());
        }
        return this;
    }

    private void addBattleUnit(WarriorUnit warrior) {
        WarriorUnitDecorator wrapped = new WarriorUnitDecorator(warrior);
        if (lastWarrior != null) {
            lastWarrior.setNextWarriorBehind(wrapped);
            lastWarrior = wrapped;
        }
        this.army.add(wrapped);
//        this.army.add(warrior);
    }

    @Override
    public String toString() {
        return "Army{" +
                "units: " + army.size() +
                '}';
    }

    public Iterator<WarriorUnit> getAliveUnit() {
        return new GetAliveUnitIterate();
    }

    static class WarriorUnitDecorator implements WarriorUnit, WarriorBehind {
        WarriorUnit warriorUnit;
        WarriorUnit nextWarrior;

        WarriorUnitDecorator(WarriorUnit warriorUnit) {
            this.warriorUnit = warriorUnit;
        }

        @Override
        public WarriorUnit getWarriorBehind() {
            return nextWarrior;
        }

        private void setNextWarriorBehind(WarriorUnit nextWarrior) {
            this.nextWarrior = nextWarrior;
        }
    }

    private class GetAliveUnitIterate implements InfGenerator<WarriorUnit> {
        Iterator<WarriorUnit> it = army.iterator();
        WarriorUnit champion;

        @Override
        public WarriorUnit next() {
            if (!hasNext()) {
                try {
                    throw new DoesntExistException("no more army units left");
                } catch (DoesntExistException e) {
                    System.out.println(e);
                }
            }
            return champion;
        }

        @Override
        public boolean hasNext() {
            if (champion != null && champion.isAlive()) {
                return true;
            }
            while (it.hasNext()) {
                champion = it.next();
                if (champion.isAlive()) {
                    return true;
                }
            }
            return false;
        }
    }

}