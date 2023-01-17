package com.attornatus.peopleapi.services;

import com.attornatus.peopleapi.entities.Person;
import com.attornatus.peopleapi.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;
    private PersonService underTest;

    @BeforeEach
    void setUp() {
        underTest = new PersonService(personRepository);
    }

    @Test
    void shouldFindAll() {
        //when
        underTest.findAll();

        //then
        verify(personRepository).findAll();
    }

    @Test
    void shouldFindById() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //given
        Long id = 1L;
        Person person = new Person(id, "Teste 1", sdf.parse("09/08/2000"));
        doReturn(Optional.of(person)).when(personRepository).findById(id);

        //when
        Person expectedPerson = underTest.findById(id);

        //then
        assertThat(expectedPerson).isEqualTo(person);
    }

    @Test
    void shouldInsertPerson() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Long id = 1L;
        //given
        Person person = new Person(id, "Teste 1", sdf.parse("09/08/2000"));

        //when
        underTest.insert(person);

        //then
        ArgumentCaptor<Person> personArgumentCaptor = ArgumentCaptor.forClass(Person.class);

        verify(personRepository).save(personArgumentCaptor.capture());

        Person capturedPerson = personArgumentCaptor.getValue();

        assertThat(capturedPerson).isEqualTo(person);

    }

    @Test
    void shouldDeletePersonById() throws ParseException {
        //when
        Long id = 1L;
        underTest.delete(id);

        //then
        verify(personRepository, times(1)).deleteById(id);
    }

    @Test
    void shouldUpdatePerson() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Long id = 1L;

        //given
        Person person = new Person(id, "Teste 1", sdf.parse("09/08/2000"));

        when(personRepository.getReferenceById(person.getId())).thenReturn(person);

        ArgumentCaptor<Person> personArgumentCaptor = ArgumentCaptor.forClass(Person.class);

        //when
        Person updatedPerson = underTest.update(id, person);
        verify(personRepository).save(personArgumentCaptor.capture());

        //then
        assertThat(personArgumentCaptor.getValue()).isEqualTo(updatedPerson);
    }
}