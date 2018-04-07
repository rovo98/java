package com.rovo98.struts.helloworld.model;

/**
 * Model class that stores a message.
 *
 * @author rovo98
 *
 * Date: 2018.4.7
 */
public class MessageStore {
    private String message;

    public MessageStore() {
        message = "Hello Struts User";
    }

    // Getters and setters.
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String toString() {
        return message + " (from toString)";
    }
}
