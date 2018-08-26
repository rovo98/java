package com.rovo98.struts.edit.model;

public class State {
    private String stateAbbr;

    private String stateName;

    public State(String stateAbbr, String stateName) {
        this.stateAbbr = stateAbbr;
        this.stateName = stateName;
    }

    // Getters and Setters.
    public String getStateAbbr() {
        return stateAbbr;
    }

    public void setStateAbbr(String stateAbbr) {
        this.stateAbbr = stateAbbr;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
    public String toString() {
        return getStateAbbr();
    }
}
