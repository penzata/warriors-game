package org.example.battleunits;

import org.example.battleunits.subsidiary.Command;
import org.example.battleunits.subsidiary.WarriorUnitHitCommand;
import org.example.battleunits.units.HealerUnit;
import org.example.battleunits.units.WarriorUnit;
import org.jetbrains.annotations.NotNull;

public class Healer extends Warrior implements HealerUnit {
    private int medKitsPerHealer = 20;
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
    public void processCommand(Command command, WarriorUnit commandSender) {
        if (command == WarriorUnitHitCommand.HEAL) {
           heal(commandSender, getHealthPointsFromMedKit());
           setMedKitsPerHealer(this.medKitsPerHealer--);
        }
    }

    @Override
    public int getHealthPointsFromMedKit() {
        return 2;
    }

    private void setHealthPointsFromMedKit(int healthPoints) {
        this.healthPointsFromMedKit = healthPoints;
    }

    @Override
    public int getMedKitsPerHealer() {
        return medKitsPerHealer;
    }

    private void setMedKitsPerHealer(int medKitsPerHealer) {
        this.medKitsPerHealer = medKitsPerHealer;
    }
}
