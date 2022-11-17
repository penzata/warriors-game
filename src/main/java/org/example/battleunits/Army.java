package org.example.battleunits;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class Army {
    private List<Warrior> army;

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
            army.add(factory.get());
        }
        return this;
    }

    /**
     * @return unmodifiable (read-only) army list
     */
    public List<Warrior> getArmy() {
        return Collections.unmodifiableList(army);
    }

    @Override
    public String toString() {
        return "Army{" + army.size() + "}";
    }

}
