package com.rovo98.struts.register.exceptions;

/**
 * SecurityBreachException
 * @author rovo98
 * Date: 2018.4.5
 */
public class SecurityBreachException extends Exception {

    public SecurityBreachException() {
        super("Security Exception");
    }
    public SecurityBreachException(String message) {
        super(message);
    }
}
