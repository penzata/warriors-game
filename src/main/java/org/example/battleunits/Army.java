package org.example.battleunits;

import org.example.iterators.InfGenerator;
import org.example.battleunits.weapons.Weapon;

import java.util.Iterator;

public interface Army extends InfGenerator<Warrior> {

    /**
     * @return iterator over Army for units-in-line order for turn-based fight.
     */
    Iterator<Warrior> getAliveUnit();

    /**
     * @return iterator over Army for one-on-one duel fights between army units based on theirs position in army.
     */
    Iterator<Warrior> nextInLine();

    void equipWarriorAtPosition(int position, Weapon weapon);

}
