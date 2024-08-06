package com.example.exceptions;

public class ObjectNotFoundException extends Exception {
    public ObjectNotFoundException() {
        super("Object did not find");
    }
}
