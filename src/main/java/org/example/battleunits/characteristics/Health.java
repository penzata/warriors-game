package org.example.battleunits.characteristics;

public interface Health {

    void reduceHealth(int damage);

    default boolean isAlive() {
        return getHealth() > 0;
    }

    int getHealth();
}
