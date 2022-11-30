package org.example.battleunits;

import org.example.characteristics.Attack;
import org.example.weapons.Weapon;

public class DefenderImpl extends WarriorImpl implements Defender {
    private int defence;

    DefenderImpl() {
        super(60, 3);
        this.defence = 2;
    }

    DefenderImpl(int health, int attack, int defence) {
        super(health, attack);
        this.defence = defence;
    }

    /**
     * @param defender - copy constructor
     */
    DefenderImpl(DefenderImpl defender) {
        super(defender);
        this.defence = defender.defence;
    }

    @Override
    public int getDefence() {
        return this.defence;
    }

    private void setDefence(int defence) {
        this.defence = defence;
    }

}
