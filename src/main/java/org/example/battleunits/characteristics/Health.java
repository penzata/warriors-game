package org.example.battleunits.characteristics;

public interface Health {
    int getHealth();
    void reduceHealth(int damage);
    /**
     * @param healingPoints - self-healing (restores health by 50% of the dealt damage)
     */
    void vampirism(int healingPoints);
    default boolean isAlive() {
        return getHealth() > 0;
    }
}
