package org.example.battleunits.characteristics;

public interface Health {

    void reduceHealth(int damage);

    /**
     * @param healingPoints - self-healing (restores health by 50% of the dealt damage)
     */
    void vampirism(int healingPoints);

    void heal();

    default boolean isAlive() {
        return getHealth() > 0;
    }

    int getHealth();
}
