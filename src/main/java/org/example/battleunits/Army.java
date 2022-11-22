package org.example.battleunits;

import org.example.battleunits.characteristics.Attack;
import org.example.battleunits.common.InfGenerator;
import org.example.battleunits.units.ArmyUnit;
import org.example.battleunits.units.WarriorBehind;
import org.example.battleunits.units.WarriorUnit;
import org.example.exceptions.DoesntExistException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Supplier;

public class Army implements ArmyUnit {
    private Collection<WarriorUnit> army;
    private WarriorUnitDecorator lastWarrior;

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
        WarriorUnitDecorator wrapped = new WarriorUnitDecorator(warrior);
        lastWarrior = wrapped;
        lastWarrior.setWarriorBehind(wrapped);
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

    static class WarriorUnitDecorator implements WarriorUnit, WarriorBehind {
        WarriorUnit warriorUnit;
        WarriorUnit nextWarrior;

        WarriorUnitDecorator(WarriorUnit warriorUnit) {
            this.warriorUnit = warriorUnit;
        }

        @Override
        public WarriorUnit getWarriorBehind() {
            return nextWarrior;
        }

        private void setWarriorBehind(WarriorUnit nextWarrior) {
            this.nextWarrior = nextWarrior;
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
        public void vampirism(int healingPoints) {
            warriorUnit.vampirism(healingPoints);
        }

        @Override
        public int getHealth() {
            return warriorUnit.getHealth();
        }

        @Override
        public void hit(WarriorUnit opponent) {
            warriorUnit.hit(opponent);
        }

        @Override
        public void receiveDamage(Attack damageDealer) {
            warriorUnit.receiveDamage(damageDealer);
        }
    }

    private class GetAliveUnitIterate implements InfGenerator<WarriorUnit> {
        Iterator<WarriorUnit> it = army.iterator();
        WarriorUnit champion;

        @Override
        public WarriorUnit next() {
            if (!hasNext()) {
                try {
                    throw new DoesntExistException("no more army units left");
                } catch (DoesntExistException e) {
                    System.out.println(e);
                }
            }
            return champion;
        }

        @Override
        public boolean hasNext() {
            if (champion != null && champion.isAlive()) {
                return true;
            }
            while (it.hasNext()) {
                champion = it.next();
                if (champion.isAlive()) {
                    return true;
                }
            }
            return false;
        }
    }

}