package org.example.battleunits.iterators;

import java.util.Iterator;

public interface InfGenerator<T> extends Iterable<T>, Iterator<T> {


    @Override
    default boolean hasNext() {
        return false;
    }

    @Override
    default T next() {
        return null;
    }

    @Override
    default void remove() {
        Iterator.super.remove();
    }

    @Override
    default Iterator<T> iterator() {
        return this;
    }
}
