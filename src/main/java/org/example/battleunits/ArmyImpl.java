package org.example.battleunits;

import lombok.extern.slf4j.Slf4j;
import org.example.battleunits.weapons.Weapon;
import org.example.iterators.AliveUnitIterate;
import org.example.iterators.StraightIterate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Supplier;

@Slf4j
public class ArmyImpl implements Army {
    private static int idSequence = 0;
    /**
     * used to keep track on created objects at debugging
     */
    private final int id = ++idSequence;
    private CombatUnitInArmyDecorator combatUnitInFront;
    private CombatUnitInArmyDecorator combatUnitBehind;
    private Warlord onlyOneWarlord;
    private List<CombatUnit> army;


    public ArmyImpl(Supplier<CombatUnit> factory, Integer numberOfUnits) {
        this.army = new ArrayList<>();
        addBattleUnits(factory, numberOfUnits);
    }

    public ArmyImpl addBattleUnits(Supplier<CombatUnit> factory, Integer numberOfUnits) {
        for (int i = 0; i < numberOfUnits; i++) {
            addBattleUnit(factory.get());
        }
        return this;
    }

    private void addBattleUnit(CombatUnit combatUnit) {
        if (combatUnit instanceof Warlord warlord) {
            if (onlyOneWarlord != null) {
                return;
            }
            onlyOneWarlord = warlord;
        }
        CombatUnitInArmyDecorator wrapped = new CombatUnitInArmyDecorator(combatUnit);
        if (combatUnitInFront == null) {
            combatUnitInFront = wrapped;
        } else {
            combatUnitBehind.setCombatUnitBehind(wrapped);
        }
        combatUnitBehind = wrapped;
        this.army.add(wrapped);
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(",\n  ", "Army#%02d --->%n[ ".formatted(id), " ]");
        sj.setEmptyValue("Army#%2d[ DEAD ]".formatted(id));
        for (CombatUnit combatUnit : army) {
            sj.add(combatUnit.toString());
        }
        return sj.toString();
    }

    @Override
    public Iterator<CombatUnit> nextInLine() {
        return new StraightIterate(combatUnitInFront);
    }

    @Override
    public Iterator<CombatUnit> getAliveUnit() {
        return new AliveUnitIterate(combatUnitInFront);
    }

    @Override
    public void equipCombatUnitAtPosition(int position, Weapon weapon) {
        try {
            army.get(position).equipWeapon(weapon);
        } catch (IndexOutOfBoundsException ex) {
            army.get(army.size() - 1).equipWeapon(weapon);
        }
    }

    @Override
    public void moveUnits() {
        if (onlyOneWarlord != null) {
            Iterable<CombatUnit> newArrangedArmy = onlyOneWarlord.rearrangeArmy(new StraightIterate(combatUnitInFront));
            combatUnitInFront = null;
            combatUnitBehind = null;
            army.clear();
            onlyOneWarlord = null;
            for (CombatUnit unit : newArrangedArmy) {
                addBattleUnit(unit);
            }
        }
    }

    @Override
    public void isEveryoneAlive() {
        List<CombatUnit> deadUnits = new ArrayList<>();
        for(CombatUnit unit : army) {
            if (!unit.isAlive() && !deadUnits.contains(unit)) {
                deadUnits.add(unit);
                moveUnits();
                return;
            }
        }
    }
}