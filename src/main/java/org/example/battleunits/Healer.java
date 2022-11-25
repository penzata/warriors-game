package org.example.battleunits;

import org.example.battleunits.subsidiary.Command;
import org.example.battleunits.subsidiary.WarriorUnitHitCommand;
import org.example.battleunits.units.HealerUnit;
import org.example.battleunits.units.WarriorUnit;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Healer extends Warrior implements HealerUnit {
    private static final Logger LOGGER = LoggerFactory.getLogger(Healer.class);
    private int healthPointsFromMedKit = 2;

    /**
     * Constructs default Healer object with default health(60) & attack(0).
     */
    public Healer() {
        super(60, 0);
    }

    Healer(int health, int attack) {
        super(health, attack);
    }

    /**
     * @param healer - copy constructor
     */
    Healer(@NotNull Healer healer) {
        super(healer);
    }

    @Override
    public void processCommand(Command command, Army.ArmyWarriorUnitDecorator commandSender) {
        if (isAlive() && command.equals(WarriorUnitHitCommand.HEAL)) {
            heal(commandSender);
            LOGGER.debug("-->(proceed with chain healing if army has more healers)-->");
        }
    }

    @Override
    public void heal(Army.ArmyWarriorUnitDecorator warriorUnit) {
        Warrior unwrapped = warriorUnit.unwrap();
        LOGGER.debug("health before healer's healing: {}", unwrapped.getHealth());
        unwrapped.healedBy(getHealthPointsFromMedKit());
        LOGGER.debug("health after healer's healing: {}", unwrapped.getHealth());
    }

    @Override
    public int getHealthPointsFromMedKit() {
        return healthPointsFromMedKit;
    }

    @Override
    public void hit(WarriorUnit opponent) {
        //do nothing, has no attack pts
    }

    private void setHealthPointsFromMedKit(int healthPoints) {
        this.healthPointsFromMedKit = healthPoints;
    }

}
