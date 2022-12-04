package org.example.battleunits.characteristics;

public interface Health {

    default boolean isAlive() {
        return getHealth() > 0;
    }

    int getHealth();

    void reduceHealth(int damage);

    int getInitialHealth();
}
