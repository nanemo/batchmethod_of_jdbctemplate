package com.nanemo.service;

import com.nanemo.entity.Person;
import com.nanemo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public Optional<Person> getAllPerson() {
        personRepository.getAllPerson();
    }
}
