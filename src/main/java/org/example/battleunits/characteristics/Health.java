package org.example.battleunits.characteristics;

public interface Health {
    int getHealth();
    void reduceHealth(int damage);
    void healthRestored(int healingPoints);
    default boolean isAlive() {
        return getHealth() > 0;
    }
}
