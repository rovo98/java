package com.rovo98.struts.edit.action;

import com.opensymphony.xwork2.ActionSupport;
import com.rovo98.struts.edit.model.Person;
import com.rovo98.struts.edit.model.State;
import com.rovo98.struts.edit.service.EditService;
import com.rovo98.struts.edit.service.EditServiceInMemory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Acts as a controller to handle actions related to editing a Person.
 *
 * @author rovo98
 * Date : 2018.4.11
 */
public class EditAction extends ActionSupport {
    private EditService editService = new EditServiceInMemory();

    private Person personBean;

    private String[] sports = {"football", "baseball", "basketball"};

    private String[] genders = {"male", "female", "not sure"};

    private List<State> states;

    private String[] carModelsAvailable = {"Ford", "Chrysler", "Toyota", "Nissan"};

    public String execute() throws Exception {
        editService.savePerson(getPersonBean());
        System.out.println("Person information saved.");
        return SUCCESS;
    }

    public String input() throws Exception {
        setPersonBean(editService.getPerson());

        return INPUT;
    }

    // Getters and Setters.
    public Person getPersonBean() {
        return personBean;
    }

    public void setPersonBean(Person personBean) {
        this.personBean = personBean;
    }

    public List<String> getSports() {
        return Arrays.asList(sports);
    }


    public List<String> getGenders() {
        return Arrays.asList(genders);
    }


    public List<State> getStates() {
        states = new ArrayList<State>();
        states.add(new State("AZ", "Arizona"));
        states.add(new State("CA", "California"));
        states.add(new State("FL", "Florida"));
        states.add(new State("KS", "Kansas"));
        states.add(new State("NY", "New York"));

        return states;
    }

    public List<String> getCarModelsAvailable() {
        return Arrays.asList(carModelsAvailable);
    }
}
