package org.example.battleunits;

import org.example.exceptions.DoesntExistException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Supplier;

public class Army {
    private Collection<Warrior> army;

    /**
     * Constructs default Army with two Warriors.
     */
    public Army() {

        this(Warrior::new, 2);
    }

    public Army(Supplier<Warrior> factory, Integer numberOfUnits) {
        this.army = new ArrayList<>();
        addBattleUnits(factory, numberOfUnits);
    }

    public Army addBattleUnits(Supplier<Warrior> factory, Integer numberOfUnits) {
        for (int i = 0; i < numberOfUnits; i++) {
            addBattleUnit(factory.get());
        }
        return this;
    }

    @Override
    public String toString() {
        return "Army{" +
                "units: " + army.size() +
                '}';
    }

    private void addBattleUnit(Warrior warrior) {
        this.army.add(warrior);
    }

    public Iterator<Warrior> getAliveUnit() {
        return new GetAliveUnitIterate();
    }

    private class GetAliveUnitIterate implements Iterator<Warrior> {
        Iterator<Warrior> it = army.iterator();
        Warrior warrior;

        @Override
        public Warrior next() {
            if (!hasNext()) {
                try {
                    throw new DoesntExistException("no more army units left");
                } catch (DoesntExistException e) {
                    System.out.println(e);
                }
            }
            return warrior;
        }

        @Override
        public boolean hasNext() {
            if (warrior == null || !warrior.isAlive()) {
                if (it.hasNext()) {
                    warrior = it.next();
                    return true;
                } else {
                    return false;
                }
            }
            return true;
        }
    }

}
