package org.example.battleunits;


public class KnightImpl extends WarriorImpl implements Knight{

    KnightImpl() {
        super(50, 7);
    }

    KnightImpl(int health, int attack) {
        super(health, attack);
    }

    /**
     * @param knight - copy constructor
     */
    KnightImpl(KnightImpl knight) {
        super(knight);
    }
}