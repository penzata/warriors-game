package org.example.battleunits;

public interface HasHealth {
    int getHealth();
    void setHealth(int health);
    void reduceHealthBasedOnDamage(int damage);
    void healthRestored(int healingPoints);
    default boolean isAlive() {
        return getHealth() > 0;
    }
}
