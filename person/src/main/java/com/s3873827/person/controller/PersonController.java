package com.s3873827.person.controller;

import com.s3873827.person.model.Person;
import com.s3873827.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/person")
public class PersonController {
    @Autowired
    PersonRepository repository;

    @GetMapping(path = "/", produces = "application/json")
    public List<Person> getPerson() {
        return repository.findAll();
    }

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public Person addPerson(@RequestBody Person Person) {
        return repository.save(Person);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public Optional<Person> getPerson(@PathVariable String id) {
        return repository.findById(Long.valueOf(id));
    }

    @PutMapping("/{id}")
    Optional<Person> updatePerson(@RequestBody Person newPerson, @PathVariable String id) {
        Optional<Person> person = repository.findById(Long.valueOf(id));

        if (person.isPresent()) {
            if (!newPerson.getName().equals(person.get().getName()))
                person.get().setName(newPerson.getName());
            else if (!newPerson.getAddress().equals(person.get().getAddress()))
                person.get().setAddress(newPerson.getAddress());
            else if (!newPerson.getPostcode().equals(person.get().getPostcode()))
                person.get().setPostcode(newPerson.getPostcode());
            else if (!newPerson.getAge().equals(person.get().getAge()))
                person.get().setAge(newPerson.getAge());
            else if (!newPerson.getJob().equals(person.get().getJob()))
                person.get().setJob(newPerson.getJob());
            else if (!newPerson.getEmail().equals(person.get().getEmail()))
                person.get().setEmail(newPerson.getEmail());
            else if (!newPerson.getPhoneno().equals(person.get().getPhoneno()))
                person.get().setPhoneno(newPerson.getPhoneno());

            repository.save(person.get());
        }

        return Optional.of(new Person());
    }

    @DeleteMapping("/{id}")
    Optional<Person> deletePerson(@PathVariable String id) {
        Optional<Person> Person = repository.findById(Long.valueOf(id));

        if (Person.isPresent())
            repository.deleteById(Long.valueOf(id));

        return Person;
    }
}
