package org.example.battleunits;

public interface HasHealth {
    int getHealth();
    void setHealth(int health);
    void reduceHealthBasedOnDamage(int damage);
    void healthRestored(CanAttack damageDealer);
    default boolean isAlive() {
        return getHealth() > 0;
    }
}
