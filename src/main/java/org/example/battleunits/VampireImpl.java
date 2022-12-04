package org.example.battleunits;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VampireImpl extends WarriorImpl implements Vampire {
    private int vampirism;

    VampireImpl() {
        super(40, 4);
        this.vampirism = 50;
    }

    VampireImpl(int health, int attack, int vampirism) {
        super(health, attack);
        this.vampirism = vampirism;
    }

    /**
     * @param vampire - copy constructor
     */
    VampireImpl(VampireImpl vampire) {
        super(vampire);
        this.vampirism = vampire.vampirism;
    }

    @Override
    public int getVampirism() {
        return Math.max(vampirism + vampirismBonusFromWeapon(), 0);
    }

    @Override
    public String toString() {
        return super.toString() + "{v:" + getVampirism() + "%}";
    }

    private void setVampirism(int vampirism) {
        this.vampirism = vampirism;
    }
}