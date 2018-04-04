package com.rovo98.struts.helloworld.action;

import com.opensymphony.xwork2.ActionSupport;
import com.rovo98.struts.helloworld.model.Person;

public class RegisterAction extends ActionSupport{
    private Person personBean;

    public String execute() {
        return SUCCESS;
    }

    // Getters and setters.
    public Person getPersonBean() {
        return personBean;
    }

    public void setPersonBean(Person person) {
        this.personBean = person;
    }
}
