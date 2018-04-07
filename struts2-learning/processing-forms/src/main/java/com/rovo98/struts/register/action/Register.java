package com.rovo98.struts.register.action;

import com.opensymphony.xwork2.ActionSupport;
import com.rovo98.struts.register.model.Person;

/**
 * Acts as a controller to handle actions related to registering a user.
 */
public class Register extends ActionSupport {
    private Person person;

    @Override
    public String execute() throws Exception {

        return SUCCESS;
    }
    @Override
    public void validate() {
        // Basic validation for this class
        if (person.getFirstName().length() == 0) {
            addFieldError("person.firstName", "First name is required.");
        }
        if (person.getLastName().length() == 0) {
            addFieldError("person.lastName", "Last name is required.");
        }
        if (person.getAge() < 18) {
            addFieldError("person.age", "Age must be 18 or older.");
        }
        if (person.getEmail().length() == 0) {
            addFieldError("person.email", "Email is required.");
        }
    }
    // Getter and setter.
    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }
}
