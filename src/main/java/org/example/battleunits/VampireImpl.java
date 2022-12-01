package org.example.battleunits;

import lombok.extern.slf4j.Slf4j;
import org.example.weapons.Weapon;

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
        return vampirism + vampirismBonusFromWeapon();
    }

    @Override
    public String toString() {
        return super.toString() + "{v:" + vampirism + "%}";
    }

    @Override
    public int vampirismBonusFromWeapon() {
        return super.vampirismBonusFromWeapon();
    }

//    @Override
//    public Warrior equipWeapon(Weapon weapon) {
//        super.equipWeapon(weapon);
//        return this;
//    }

    private void setVampirism(int vampirism) {
        this.vampirism = vampirism;
    }

    /**
     * @param selfHealing - vampire's self-healing (restores health by 50% of the dealt damage).
     */
    @Override
    public void vampirism(int selfHealing) {
        healedBy(selfHealing);
        log.atTrace().log("health after vampirism: {}", getHealth());
    }

}