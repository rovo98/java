package com.rovo98.struts.register.action;

import com.opensymphony.xwork2.ActionSupport;
import com.rovo98.struts.register.exceptions.SecurityBreachException;
import com.rovo98.struts.register.model.Person;

/**
 * A Register action class to deal with register request.
 * @author rovo98
 * Date: 2018.4.5
 *
 */
public class Register extends ActionSupport{
    private Person personBean;

    @Override
    public String execute() {
        return SUCCESS;
    }
    @Override
    public void validate() {
        if (personBean.getFirstName().length() == 0) {
            addFieldError("personBean.firstName", "First name is required.");
        }
        if (personBean.getEmail().length() == 0) {
            addFieldError("personBean.email", "Email is required.");
        }
        if (personBean.getAge() < 18) {
            addFieldError("personBean.age", "Age is required and must be 18 or older.");
        }
    }

    // Exception Handling Per Action.
    public void throwException() throws Exception {
        throw new Exception("Exception thrown from throwException");
    }
    public void throwNullPointerException() throws NullPointerException {
        throw new NullPointerException("Null Pointer Exception thrown from "
                + Register.class.toString());
    }
    public void throwSecurityException() throws SecurityBreachException {
        throw new SecurityBreachException("Security breach exception thrown from throwSecurityException");
    }
    // Getters and setters.
    public Person getPersonBean() {
        return personBean;
    }

    public void setPersonBean(Person person) {
        this.personBean = person;
    }
}
