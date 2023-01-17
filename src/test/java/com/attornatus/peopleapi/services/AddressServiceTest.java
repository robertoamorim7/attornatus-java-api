package com.attornatus.peopleapi.services;

import com.attornatus.peopleapi.entities.Address;
import com.attornatus.peopleapi.entities.Person;
import com.attornatus.peopleapi.repositories.AddressRepository;
import com.attornatus.peopleapi.repositories.PersonRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;
    @Mock
    private PersonRepository personRepository;
    private AddressService underTest;

    @BeforeEach
    void setUp() {
        underTest = new AddressService(addressRepository);
    }

    @Test
    void canFindAllAddressesByPersonId() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Long id = 1L;
        //given
        Person person = personRepository.save(new Person(id, "Teste 1", sdf.parse("09/08/2000")));

        Address address = new Address(id, "Rua Teste 1", "A1", "54321-123", "Teste City", true, person);

        addressRepository.save(address);

        //when
        underTest.findAll(id);

        //then
        ArgumentCaptor<Address> addressArgumentCaptor = ArgumentCaptor.forClass(Address.class);
        verify(addressRepository).findAllByPersonId(addressArgumentCaptor.capture().getId());

        Address capturedAddress = addressArgumentCaptor.getValue();

        assertThat(capturedAddress.getId()).isEqualTo(address.getId());

    }

    @Test
    @Disabled
    void findPersonMainAddress() {
    }

    @Test
    @Disabled
    void insert() {
    }
}