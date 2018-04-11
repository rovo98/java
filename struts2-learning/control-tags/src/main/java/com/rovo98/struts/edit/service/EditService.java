package com.rovo98.struts.edit.service;

import com.rovo98.struts.edit.model.Person;

public interface EditService {
    Person getPerson();

    void savePerson(Person personBean);
}
