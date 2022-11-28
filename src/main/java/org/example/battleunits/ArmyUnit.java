package org.example.battleunits;

import org.example.iterators.InfGenerator;
import org.example.weapons.Weapon;

import java.util.Iterator;

public interface ArmyUnit extends InfGenerator<CombatUnit> {

    /**
     * @return iterator over Army for units-in-line order for turn-based fight.
     */
    Iterator<CombatUnit> getAliveUnit();

    /**
     * @return iterator over Army for one-on-one duel fights between army units based on theirs position in army.
     */
    Iterator<CombatUnit> nextInLine();

    void removeDeadBodies();

    void equipWarriorAtPosition(int position, Weapon weapon);

}
