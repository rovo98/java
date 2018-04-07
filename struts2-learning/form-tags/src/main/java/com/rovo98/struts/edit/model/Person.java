package com.rovo98.struts.edit.model;

import java.util.Arrays;

/**
 * Models a Person who registers.
 * @author rovo98
 * Date: 2018.4.6
 */
public class Person {
    private String firstName;
    private String lastName;
    private String sport;
    private String gender;
    private String residency;
    private boolean over21;
    private String[] carModels;

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

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getResidency() {
        return residency;
    }

    public void setResidency(String residency) {
        this.residency = residency;
    }

    public boolean isOver21() {
        return over21;
    }

    public void setOver21(boolean over21) {
        this.over21 = over21;
    }

    public String[] getCarModels() {
        return carModels;
    }

    public void setCarModels(String[] carModels) {
        this.carModels = carModels;
    }

    public String toString() {
        return "First Name: " + getFirstName() + " | " +
                " Last Name: " + getLastName() + " | " +
                " Favorite Sport: " + getSport() + " | " +
                " Gender: " + getGender() + " | " +
                " Residency: " + getResidency() + " | " +
                " Over 21: " + isOver21() + " | " +
                " Car models: " + Arrays.asList(getCarModels());
    }
}
