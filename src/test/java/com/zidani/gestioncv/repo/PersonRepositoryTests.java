package com.zidani.gestioncv.repo;

import com.zidani.gestioncv.model.Person;
import com.zidani.gestioncv.repo.PersonRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PersonRepositoryTests {

    @Autowired
    private PersonRepository personRepository;

    private Person testPerson;

    @BeforeEach
    void setup() {
        testPerson = Person.builder()
                .firstName("person1fn")
                .lastName("person1ln")
                .email("person1@example.com")
                .webSite("https://person1.com")
                .birthDay(new Date(1999, Calendar.JANUARY, 1))
                .password("person1pass")
                .build();
    }

    @AfterEach
    @Transactional
    void cleanUp() {
        personRepository.deleteAll();
    }

    @Test
    void testSaveAndRetrievePerson() {
        personRepository.save(testPerson);
        var savedPerson = personRepository.findByEmail("person1@example.com");

        savedPerson.ifPresent(value ->{
            assertEquals("person1fn", value.getFirstName());
            assertEquals("person1ln", value.getLastName());
            assertEquals("https://person1.com", value.getWebSite());


        } );

        var person1Duplicate = Person.builder()
                .firstName("person1fn")
                .lastName("person1ln")
                .email("person1@example.com")
                .webSite("https://person1.com")
                .birthDay(new Date(1999, Calendar.JANUARY, 1))
                .password("person1pass")
                .build();

        assertThrows(DataIntegrityViolationException.class, () -> {
            personRepository.save(person1Duplicate);
        });
    }

    @Test
    void testUpdatePerson() {
        personRepository.save(testPerson);

        var savedPerson = personRepository.findByEmail("person1@example.com");

        assertNotNull(savedPerson.orElse(null));

        savedPerson.ifPresent(saved -> saved.setFirstName("UpdatedFirstName"));

        personRepository.save(savedPerson.orElse(null));

        var updatedPerson = personRepository.findByEmail("person1@example.com");

        updatedPerson.ifPresent(updated -> {
            assertEquals("UpdatedFirstName", updated.getFirstName());
        });
    }

    @Test
    void testDeletePerson() {
        personRepository.save(testPerson);

        personRepository.delete(testPerson);

        var deletedPerson = personRepository.findByEmail("person1@example.com");

        assertTrue(deletedPerson.isEmpty());
    }

}
