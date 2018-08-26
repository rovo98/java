package com.rovo98.struts.register.exceptions;

public class SecurityBreachException extends Exception {

    public SecurityBreachException() {
        super("Security Exception");
    }

    public SecurityBreachException(String message) {
        super(message);
    }
}
