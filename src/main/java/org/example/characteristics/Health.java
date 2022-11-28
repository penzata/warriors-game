package org.example.characteristics;

public interface Health {

    default boolean isAlive() {
        return getHealth() > 0;
    }

    int getHealth();
}
