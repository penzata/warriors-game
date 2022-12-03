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
    public Iterable<Warrior> rearrangeArmy(InfGenerator<Warrior> army) {
        Collection<Warrior> initialArmy = new ArrayList<>();
        for (Warrior warrior : army) {
            if (warrior != this) {
                initialArmy.add(warrior);
            }
        }
        Collection<Warrior> lancers = initialArmy.stream()
                .filter(Lancer.class::isInstance)
                .toList();
        Collection<Warrior> healers = initialArmy.stream()
                .filter(Healer.class::isInstance)
                .toList();
        Collection<Warrior> fighters = initialArmy.stream()
                .filter(e -> !(e instanceof Lancer || e instanceof Healer))
                .toList();

        Collection<Warrior> rearrangedArmy = new ArrayList<>();
        lancers.stream()
                .limit(1)
                .findFirst()
                .ifPresent(rearrangedArmy::add);
        rearrangedArmy.addAll(healers);
        rearrangedArmy.addAll(lancers);
        rearrangedArmy.addAll(fighters);
        rearrangedArmy.add(Warlord.create());

        return rearrangedArmy;
    }
}