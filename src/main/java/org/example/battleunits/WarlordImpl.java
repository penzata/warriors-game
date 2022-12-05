package org.example.battleunits;

import org.example.battleunits.subsidiary.CombatUnitType;
import org.example.iterators.InfGenerator;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toCollection;
import static org.example.battleunits.subsidiary.CombatUnitType.*;

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
        return Math.max(defence + getDefenceStatFromWeapon(), 0);
    }

    @Override
    public CombatUnitType getCombatUnitType() {
        return CombatUnitType.WARLORD;
    }

    @Override
    public Iterable<CombatUnit> rearrangeArmy(InfGenerator<CombatUnit> army) {
        Map<CombatUnitType, ArrayDeque<CombatUnit>> initialArmy = Stream.generate(army::hasNext)
                .takeWhile(b -> b)
                .map(b -> army.next())
                .collect(Collectors.groupingBy(
                        Warlord::classify,
                        toCollection(ArrayDeque::new)
                ));

        int totalArmyNumber = initialArmy.values().stream()
                .mapToInt(Collection::size)
                .sum();
        Collection<CombatUnit> rearrangedArmy = new ArrayList<>(totalArmyNumber);

        Stream.of(LANCER, FIGHTER)
                .map(initialArmy::get)
                .filter(Objects::nonNull)
                .map(ArrayDeque::pollFirst)
                .filter(Objects::nonNull)
                .findFirst()
                .ifPresent(rearrangedArmy::add);

        Stream.of(HEALER, LANCER, FIGHTER, WARLORD)
                .map(initialArmy::get)
                .filter(Objects::nonNull)
                .forEach(rearrangedArmy::addAll);

        return rearrangedArmy;
    }
}