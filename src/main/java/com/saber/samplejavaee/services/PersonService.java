package com.saber.samplejavaee.services;

import com.saber.samplejavaee.model.query.Person;

import java.util.List;

public interface PersonService {
    List<Person> findAll();
    Person findByNationalCode(String nationalCode);
    Person findById(Long id);
    Boolean save(Person person);
    Boolean deleteById(Long id);
}
