package org.example.battleunits;

import org.example.characteristics.Heal;
import org.example.subsidiary.CombatUnitHitCommand;
import org.example.subsidiary.Command;
import org.example.subsidiary.ProcessCommandChain;
import org.example.weapons.Weapon;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Healer extends Warrior implements Heal, ProcessCommandChain {
    private static final Logger LOGGER = LoggerFactory.getLogger(Healer.class);
    private int healPower;

    Healer() {
        super(60, 0);
        this.healPower = 2;
    }

    Healer(int health, int attack) {
        super(health, attack);
        this.healPower = 2;
    }

    /**
     * @param healer - copy constructor
     */
    Healer(@NotNull Healer healer) {
        super(healer);
    }

    @Override
    public CombatUnit equipWeapon(Weapon weapon) {
        super.equipWeapon(weapon);
        setHealPower(Math.max(getHealPower() + weapon.getWeaponHealPower(), 0));
        return this;
    }

    @Override
    public int getHealPower() {
        return healPower;
    }

    private void setHealPower(int healthPoints) {
        this.healPower = healthPoints;
    }

    @Override
    public void processCommand(Command command, CombatUnitInArmyDecorator commandSender) {
        if (isAlive() && command.equals(CombatUnitHitCommand.HEAL)) {
            Warrior unwrapped = commandSender.unwrap();
            heal(unwrapped);
            LOGGER.debug("----->proceed with chain healing if army has more healers----->");
        }
    }

    public void heal(Warrior warrior) {
        LOGGER.debug("health before healer's healing: {}", warrior.getHealth());
        warrior.healedBy(getHealPower());
        LOGGER.debug("health after healer's healing: {}", warrior.getHealth());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{h:" + getHealth() +
                ", a:" + getAttack() +
                ", heal:" + getHealPower() + "}";
    }

    @Override
    public void hit(CombatUnit opponent) {
        //do nothing, has no attack points
    }

}
