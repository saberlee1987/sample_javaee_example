package com.saber.samplejavaee.repositories;

import com.saber.samplejavaee.model.query.Person;

import java.util.List;

public interface PersonRepository {
    List<Person> findAll();
    Person findByNationalCode(String nationalCode);
    Person findById(Long id);
    Person save(Person person);
    Person update(Person person);
    Boolean deleteById(Long id);
}