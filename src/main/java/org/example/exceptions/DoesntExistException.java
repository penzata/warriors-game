package org.example.exceptions;

import java.util.NoSuchElementException;

public class DoesntExistException extends NoSuchElementException {
    public DoesntExistException(String message) {

        super(message);
    }

}
