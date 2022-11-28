package org.example.battleunits;

import org.example.battleunits.characteristics.Heal;
import org.example.battleunits.subsidiary.Command;
import org.example.battleunits.subsidiary.ProcessCommandChain;
import org.example.battleunits.subsidiary.WarriorUnitHitCommand;
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
    public void processCommand(Command command, ArmyCombatUnitDecorator commandSender) {
        if (isAlive() && command.equals(WarriorUnitHitCommand.HEAL)) {
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
    public int getHealPower() {
        return healPower;
    }

    @Override
    public void hit(CombatUnit opponent) {
        //do nothing, has no attack points
    }

    private void setHealPower(int healthPoints) {
        this.healPower = healthPoints;
    }

}
