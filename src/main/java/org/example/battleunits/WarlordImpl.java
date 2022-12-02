package org.example.battleunits;

import org.example.battlestrategy.CombatStrategy;

public class WarlordImpl extends WarriorImpl implements Warlord {
    private int defence;
    private CombatStrategy strategy;

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
    public void setStrategy(CombatStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void strategize() {
        this.strategy.strategize();
    }
}
/*
Warlord warlord = new WarlordImpl();
warlord.setStrategy(new RearrangeArmy());
warlord.strategize();*/
