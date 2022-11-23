package org.example.battleunits.characteristics;

public interface Health {

    void reduceHealth(int damage);

    void increaseHealth();

    /**
     * @param healingPoints - self-healing (restores health by 50% of the dealt damage)
     */
    void vampirism(int healingPoints);

    default boolean isAlive() {
        return getHealth() > 0;
    }

    int getHealth();
}
