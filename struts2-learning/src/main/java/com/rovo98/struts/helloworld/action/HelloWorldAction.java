package com.rovo98.struts.helloworld.action;

import com.opensymphony.xwork2.ActionSupport;
import com.rovo98.struts.helloworld.model.MessageStore;

/**
 * A hello world Action to test the struts 2
 * @author rovo98
 * Date: 2018.4.4
 */
public class HelloWorldAction extends ActionSupport {
    private MessageStore messageStore;
    private static int helloCount = 0;
    private String userName;

    public String execute() {
        messageStore = new MessageStore();
        helloCount++;
        if (userName != null) {
            messageStore.setMessage(messageStore.getMessage() + " " + userName);
        }
        return SUCCESS;
    }

    // Getters and Setters.
    public MessageStore getMessageStore() {
        return messageStore;
    }
    public int getHelloCount() {
        return helloCount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
