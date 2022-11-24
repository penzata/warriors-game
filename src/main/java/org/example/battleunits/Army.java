package org.example.battleunits;

import org.example.battleunits.characteristics.Attack;
import org.example.battleunits.common.InfGenerator;
import org.example.battleunits.subsidiary.Command;
import org.example.battleunits.subsidiary.ProcessCommandChain;
import org.example.battleunits.subsidiary.WarriorUnitBehind;
import org.example.battleunits.subsidiary.WarriorUnitHitCommand;
import org.example.battleunits.units.ArmyUnit;
import org.example.battleunits.units.WarriorUnit;
import org.example.exceptions.DoesntExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Supplier;

public class Army implements ArmyUnit {
    private static final Logger LOGGER = LoggerFactory.getLogger(Army.class);
    private Collection<WarriorUnit> army;
    private ArmyWarriorUnitDecorator lastWarrior;

    /**
     * Constructs default Army with one Warrior.
     */
    public Army() {

        this(Warrior::new, 1);
    }

    public Army(Supplier<WarriorUnit> factory, Integer numberOfUnits) {
        this.army = new ArrayList<>();
        addBattleUnits(factory, numberOfUnits);
    }

    public Army addBattleUnits(Supplier<WarriorUnit> factory, Integer numberOfUnits) {
        for (int i = 0; i < numberOfUnits; i++) {
            addBattleUnit(factory.get());
        }
        return this;
    }

    private void addBattleUnit(WarriorUnit warrior) {
        ArmyWarriorUnitDecorator wrapped = new ArmyWarriorUnitDecorator(warrior);
        if (lastWarrior != null) {
            lastWarrior.setWarriorBehind(wrapped);
        }
        lastWarrior = wrapped;
        this.army.add(wrapped);
    }

    @Override
    public String toString() {
        return "Army{" +
                "units: " + army.size() +
                '}';
    }

    public Iterator<WarriorUnit> getAliveUnit() {
        return new GetAliveUnitIterate();
    }

    public static class ArmyWarriorUnitDecorator implements WarriorUnit, WarriorUnitBehind, ProcessCommandChain {
        private WarriorUnit warriorUnit;
        private ArmyWarriorUnitDecorator nextWarrior;

        ArmyWarriorUnitDecorator(WarriorUnit warriorUnit) {
            this.warriorUnit = warriorUnit;
        }

        @Override
        public ArmyWarriorUnitDecorator getWarriorBehind() {
            return nextWarrior;
        }

        private void setWarriorBehind(ArmyWarriorUnitDecorator nextWarrior) {
            this.nextWarrior = nextWarrior;
        }

        @Override
        public void processCommand(Command command, Army.ArmyWarriorUnitDecorator commandSender) {
            if (warriorUnit instanceof ProcessCommandChain processor && !warriorUnit.equals(commandSender.unwrap())) {
                processor.processCommand(command, commandSender);
            }
            if (nextWarrior != null) {
                nextWarrior.processCommand(command, this);
            }
        }

        protected Warrior unwrap() {
            return (Warrior) warriorUnit;
        }

        @Override
        public int getAttack() {
            return warriorUnit.getAttack();
        }

        @Override
        public void reduceHealth(int damage) {
            warriorUnit.reduceHealth(damage);
        }

        @Override
        public int getHealth() {
            return warriorUnit.getHealth();
        }

        @Override
        public void hit(WarriorUnit opponent) {
            warriorUnit.hit(opponent);
            processCommand(WarriorUnitHitCommand.HEAL, this);
        }

        @Override
        public void receiveDamage(Attack damageDealer) {
            warriorUnit.receiveDamage(damageDealer);
        }
    }

    private class GetAliveUnitIterate implements InfGenerator<WarriorUnit> {
        Iterator<WarriorUnit> it = army.iterator();
        WarriorUnit hitter;

        @Override
        public WarriorUnit next() {
            if (!hasNext()) {
                try {
                    throw new DoesntExistException("no more army units left");
                } catch (DoesntExistException e) {
                    LOGGER.error("insufficient army units funds.");
                }
            }
            return hitter;
        }

        @Override
        public boolean hasNext() {
            if (hitter != null && hitter.isAlive()) {
                return true;
            }
            while (it.hasNext()) {
                hitter = it.next();
                if (hitter.isAlive()) {
                    return true;
                }
            }
            return false;
        }
    }

}