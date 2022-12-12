package org.example.iterators;

import java.util.Iterator;

public interface InfGenerator<T> extends Iterable<T>, Iterator<T> {

    @Override
    default Iterator<T> iterator() {
        return this;
    }

    @Override
    default boolean hasNext() {
        return false;
    }

    @Override
    default T next() {
        return null;
    }
}