package com.saber.samplejavaee.services.impl;

import com.saber.samplejavaee.model.query.Person;
import com.saber.samplejavaee.repositories.PersonRepository;
import com.saber.samplejavaee.services.PersonService;
import jakarta.transaction.Transactional;

import java.util.List;

public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person findByNationalCode(String nationalCode) {
        return personRepository.findByNationalCode(nationalCode);
    }

    @Override
    public Person findById(Long id) {
        return personRepository.findById(id);
    }

    @Override
    public Boolean save(Person person) {
        Person personResponse = personRepository.findByNationalCode(person.getNationalCode());
        if (personResponse!=null){
            throw new RuntimeException("person with nationalCode already exist");
        }
        Person personSaved = personRepository.save(person);
        return personSaved != null;
    }

    @Override
    public Boolean deleteById(Long id) {
        Person personResponse = personRepository.findById(id);
        if (personResponse==null){
            throw new RuntimeException("person with nationalCode does not exist");
        }
        return personRepository.deleteById(id);
    }
}
