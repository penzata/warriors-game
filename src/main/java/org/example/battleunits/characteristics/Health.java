package org.example.battleunits.characteristics;

public interface Health {
    int getHealth();
    void setHealth(int health);
    void reduceHealth(int damage);
    void healthRestored(int healingPoints);
    default boolean isAlive() {
        return getHealth() > 0;
    }
}
