package com.rovo98.struts.helloworld.model;

/**
 * A Person Javabean.
 * @author rovo98
 * Date : 2018.4.4
 */
public class Person {
    private String firstName;
    private String lastName;
    private String email;
    private int age;

    // Getters and Setters.
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public String toString() {
        return "First Name: " + getFirstName() + " Last Name: " + getLastName() +
                " Email: " + getEmail() + " Age:     " + getAge();
    }
}
