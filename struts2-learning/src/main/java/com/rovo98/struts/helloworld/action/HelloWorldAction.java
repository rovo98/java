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

    public String execute() {
        messageStore = new MessageStore();
        return SUCCESS;
    }

    public MessageStore getMessageStore() {
        return messageStore;
    }
}
