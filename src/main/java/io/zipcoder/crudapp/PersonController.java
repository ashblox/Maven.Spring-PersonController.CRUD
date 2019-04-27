package io.zipcoder.crudapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {

    @Autowired
    private PersonRepo personRepo;

    @PostMapping("/people")
    public ResponseEntity<Person> createPerson(@RequestBody Person p) {
        return new ResponseEntity<>(personRepo.save(p), HttpStatus.CREATED);
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id) {
        return new ResponseEntity<>(personRepo.findOne(id), HttpStatus.OK);
    }

    @GetMapping("/people")
    public ResponseEntity<Iterable<Person>> getPersonList() {
        return new ResponseEntity<>(personRepo.findAll(), HttpStatus.OK);
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person p) {
        Person person = personRepo.findOne(id);
        person.setFirstName(p.getFirstName());
        person.setLastName(p.getLastName());
        return new ResponseEntity<>(personRepo.save(person), HttpStatus.OK);
    }

    @DeleteMapping("people/{id}")
    public ResponseEntity<Boolean> deletePerson(@PathVariable Long id) {
        personRepo.delete(id);
        return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
    }
}
