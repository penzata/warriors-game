package org.example.battleunits;

import org.example.battleunits.characteristics.Defence;
import org.example.battleunits.subsidiary.CombatUnitType;
import org.example.battleunits.weapons.Weapon;
import org.example.iterators.InfGenerator;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toCollection;
import static org.example.battleunits.subsidiary.CombatUnitType.*;

public class Warlord extends CombatUnitImpl implements Defence {
    private static final int DEFAULT_HEALTH = 100;
    private static final int DEFAULT_ATTACK = 4;
    private static final int DEFAULT_DEFENCE = 2;
    private int defence;

    Warlord() {
        super(DEFAULT_HEALTH, DEFAULT_ATTACK);
        this.defence = DEFAULT_DEFENCE;
    }

    Warlord (int health, int attack, int defence) {
        super(health, attack);
        this.defence = defence;
    }

    @Override
    public String toString() {
        return super.toString() + "{d:" + getDefence() + "}";
    }

    @Override
    public int getDefence() {
        return Math.max(defence + getDefenceStatFromWeapon(), 0);
    }

    private int getDefenceStatFromWeapon() {
        return getWeaponsEquipped().stream()
                .mapToInt(Weapon::getDefenceStat)
                .sum();
    }

    @Override
    public CombatUnitType getCombatUnitType() {
        return WARLORD;
    }

    public Iterable<CombatUnit> rearrangeArmy(InfGenerator<CombatUnit> army) {
        Map<CombatUnitType, ArrayDeque<CombatUnit>> initialArmy = StreamSupport
                .stream(army.spliterator(), false)
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

    private static CombatUnitType classify(CombatUnit combatUnit) {
        return combatUnit.getCombatUnitType();
    }
}