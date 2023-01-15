package com.attornatus.peopleapi.config;

import com.attornatus.peopleapi.entities.Address;
import com.attornatus.peopleapi.entities.Person;
import com.attornatus.peopleapi.repositories.AddressRepository;
import com.attornatus.peopleapi.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Configuration
public class TestConfig implements CommandLineRunner {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressRepository addressRepository;


    @Override
    public void run(String... args) throws Exception {

        Person p1 = new Person(null, "Teste 1", sdf.parse("09/08/2000"));
        Person p2 = new Person(null, "Teste 2", sdf.parse("10/08/2002"));
        Person p3 = new Person(null, "Teste 3", sdf.parse("11/10/2001"));

        Address a1 = new Address(null, "Rua Teste 1", "A1", "54321-123", "Teste City", true, p1);
        Address a2 = new Address(null, "Rua Teste 2", "A2", "54321-121", "Teste City 2", true, p2);
        Address a3 = new Address(null, "Rua Teste 3", "A3", "54321-122", "Teste City 3", true, p3);
        Address a4 = new Address(null, "Rua Teste 4", "A4", "54321-124", "Teste City 4", false, p1);
        Address a5 = new Address(null, "Rua Teste 5", "A5", "54321-125", "Teste City 5", false, p1);
        Address a6 = new Address(null, "Rua Teste 6", "A6", "54321-126", "Teste City 6", false, p2);

        personRepository.saveAll(Arrays.asList(p1, p2, p3));
        addressRepository.saveAll(Arrays.asList(a1, a2, a3, a4, a5, a6));

    }
}
