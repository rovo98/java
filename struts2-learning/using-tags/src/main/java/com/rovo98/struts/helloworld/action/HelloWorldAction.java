package com.rovo98.struts.helloworld.action;

import com.opensymphony.xwork2.ActionSupport;
import com.rovo98.struts.helloworld.model.MessageStore;

/**
 * Acts as a Struts 2 controller that responds to a user action
 *
 * @author rovo98
 *
 * Date: 2018.4.7
 */
public class HelloWorldAction extends ActionSupport {
    private MessageStore messageStore;
    private static int helloCount = 0;
    private String userName;

    public String execute() {
        messageStore = new MessageStore();
        if (userName != null) {
            messageStore.setMessage(messageStore.getMessage() + " " + userName);
        }
        helloCount++;

        return SUCCESS;
    }
    // Getters and setters.
    public MessageStore getMessageStore() {
        return messageStore;
    }
    public int getHelloCount() {
        return helloCount;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserName() {
        return userName;
    }
}
