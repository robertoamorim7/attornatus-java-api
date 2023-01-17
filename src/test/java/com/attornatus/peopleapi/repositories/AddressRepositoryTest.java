package com.attornatus.peopleapi.repositories;

import com.attornatus.peopleapi.entities.Address;
import com.attornatus.peopleapi.entities.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class AddressRepositoryTest {

    @Autowired
    private  PersonRepository personRepository;

    @Autowired
    private AddressRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldFindAllAddressesByPersonId() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //given

        Person person = personRepository.save(new Person(null, "Teste 1", sdf.parse("09/08/2000")));
        Long id = person.getId();

        Address address = new Address(id, "Rua Teste 1", "A1", "54321-123", "Teste City", true, person);
        underTest.save(address);

        //when
        Optional<List<Address>> expected = underTest.findAllByPersonId(id);

        //then
        assertThat(expected.get().get(0)).isEqualTo(address);
    }

    @Test
    void itShouldFindMainAddressByPersonId() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //given
        Person person = personRepository.save(new Person(null, "Teste 1", sdf.parse("09/08/2000")));
        Long id = person.getId();

        Address address = new Address(id, "Rua Teste 1", "A1", "54321-123", "Teste City", true, person);
        underTest.save(address);

        //when
        Address expected = underTest.findAllByPersonIdAndIsMainAddressTrue(id).iterator().next();

        //then
        assertThat(expected).isEqualTo(address);

    }
}