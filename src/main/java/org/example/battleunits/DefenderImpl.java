package org.example.battleunits;

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

        return defence + defenceBonusFromWeapon();
    }

    @Override
    public String toString() {
        return super.toString() + "{d:" + defence + "}";
    }

    @Override
    public int defenceBonusFromWeapon() {
        return super.defenceBonusFromWeapon();
    }

    private void setDefence(int defence) {
        this.defence = defence;
    }

}
