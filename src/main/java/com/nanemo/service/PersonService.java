package com.nanemo.service;

import com.nanemo.entity.Person;
import com.nanemo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Lazy
public class PersonService {
    private long time;

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllPerson() {
        return personRepository.getAllPerson();
    }

    public void addPeopleWithBatchMethod() {
        personRepository.addPeopleWithBatchMethod(getPeople());
    }

    public void addPeopleWithSimpleUpdateMethod() {
        personRepository.addPeopleWithSimpleUpdateMethod(getPeople());
    }

    public void dropAndCreatePersonTable() {
        personRepository.dropAndCreatePersonTable();
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public List<Person> getPeople() {
        List<Person> people = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            people.add(new Person("name" + (i + 1), "test@email.com", (byte) 30, "Country, City, 00000"));
        }

        return people;
    }

}
