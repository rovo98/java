package com.rovo98.struts.edit.model;

import java.util.Arrays;

/**
 * Models a Person who registers.
 * @author rovo98
 * Date: 2018.4.11
 */
public class Person {
    private String firstName;
    private String lastName;
    private String sport;
    private String gender;
    private String residency;
    private boolean over21;
    private String[] carModels;
    private String email;
    private String phoneNumber;
    private Integer age;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sport='" + sport + '\'' +
                ", gender='" + gender + '\'' +
                ", residency='" + residency + '\'' +
                ", over21=" + over21 +
                ", carModels=" + Arrays.toString(carModels) +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", age=" + age +
                '}';
    }
}
