package org.example.battleunits;

import org.example.iterators.InfGenerator;

import java.util.ArrayList;
import java.util.Collection;

public class WarlordImpl extends WarriorImpl implements Warlord {
    private int defence;

    WarlordImpl() {
        super(100, 4);
        this.defence = 2;
    }

    WarlordImpl(int health, int attack) {
        super(health, attack);
        this.defence = 2;
    }

    /**
     * @param warlord - copy constructor
     */
    WarlordImpl(WarlordImpl warlord) {
        super(warlord);
        this.defence = 2;
    }

    @Override
    public String toString() {
        return super.toString() + "{d:" + getDefence() + "}";
    }

    @Override
    public int getDefence() {
        return defence;
    }

    @Override
    public InfGenerator<Warrior> rearrangeArmy(InfGenerator<Warrior> army) {
        Collection<Warrior> rearrangedArmy = new ArrayList<>();
        for (Warrior warrior : army) {
            if (warrior != this) {
                rearrangedArmy.add(warrior);
            }
        }
        return null;
    }
}