package org.example.battleunits.characteristics;

public interface Health {

    void reduceHealth(int damage);

//    /**
//     * @param healingPoints - self-healing (restores health by 50% of the dealt damage).
//     */
//    default void vampireSelfHeal(int healingPoints) {
//        //to not be needed to implement by any extending classes
//    }
//
//    default void healedByMedKit(int healingPoints) {
//        //to not be needed to implement by any extending classes
//    }

    default boolean isAlive() {
        return getHealth() > 0;
    }

    int getHealth();
}
