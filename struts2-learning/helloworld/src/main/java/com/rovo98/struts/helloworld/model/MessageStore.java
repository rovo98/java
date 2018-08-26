package com.rovo98.struts.helloworld.model;

/**
 * A model to store a message.
 * @author rovo98
 * Date : 2018.4.6
 */
public class MessageStore {
    private String message;

    public MessageStore() {
        message = "Hello Struts User";
    }
    public MessageStore(String msg) {
        this.message = msg;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message + " (from toString)";
    }
}
