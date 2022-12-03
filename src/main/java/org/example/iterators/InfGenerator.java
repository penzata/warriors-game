package org.example.iterators;

import java.util.Iterator;

public interface InfGenerator<T> extends Iterable<T>, Iterator<T> {

    boolean hasNext();

    T next();

    @Override
    default Iterator<T> iterator() {
        return this;
    }
}