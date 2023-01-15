package com.attornatus.peopleapi.controllers;

import com.attornatus.peopleapi.entities.Address;
import com.attornatus.peopleapi.entities.Person;
import com.attornatus.peopleapi.services.AddressService;
import com.attornatus.peopleapi.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/address")
public class AddressController {

    @Autowired
    AddressService service;

    @Autowired
    PersonService personService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<Address>> findAllByPersonId(@PathVariable Long id) {
        List<Address> address = service.findAll(id);
        return ResponseEntity.ok().body(address);
    }

    @GetMapping(value = "/{id}/main-address")
    public ResponseEntity<Address> findPersonMainAddress(@PathVariable Long id) {
        Address address = service.findPersonMainAddress(id);
        return ResponseEntity.ok().body(address);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Address> createAddressByPersonId(@RequestBody Address address, @PathVariable Long id) {
        Person person = personService.findById(id);
        address.setPerson(person);
        address = service.insert(address, person.getId());
        return ResponseEntity.ok().body(address);
    }

}
