package com.zidani.gestioncv.personManagementTests;

import com.zidani.gestioncv.curriculumVitaeManagment.CurriculumVitae;
import com.zidani.gestioncv.personManagment.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PersonServiceShould {

    @MockBean
    private PersonRepository personRepository;
    @Autowired
    private PersonService personService;

    @Test
    void add_a_person_given_a_person_request(){
        var personRequest = PersonTestsUtils.createPersonRequest("fnTest", "lnTest", "unique@test.com");
        var uniquePerson = PersonTestsUtils.createPersonFromPersonRequest(personRequest);

        personService.addPerson(personRequest);

        verify(personRepository, times(1)).save(uniquePerson);
    }
    @Test
    void return_all_registered_persons(){
        var personsList = PersonTestsUtils.createPersons();
        var actual = PersonTestsUtils.personsResponseListFromPersonsList(personsList);

        when(personRepository.findAll()).thenReturn(personsList);
        var expected = personService.getAllPersons();

        assertEquals(expected, actual);
    }
    @Test
    void return_list_of_persons_given_full_firstName(){
        var list = List.of(PersonTestsUtils.createPersonsWithJohInFirstName().get(0));
        var actual = PersonTestsUtils.personsResponseListFromPersonsList(list);

        when(personRepository.findByFirstNameContainingIgnoreCase("john")).thenReturn(list);
        var expected = personService.searchPersonByFirstName("john");

        assertEquals(actual, expected);
    }

    @Test
    void return_list_of_persons_given_part_of_firstName(){
        var list = PersonTestsUtils.createPersonsWithJohInFirstName();
        var actual = PersonTestsUtils.personsResponseListFromPersonsList(list);

        when(personRepository.findByFirstNameContainingIgnoreCase("joh")).thenReturn(list);
        var expected = personService.searchPersonByFirstName("joh");

        assertEquals(actual, expected);
    }

    @Test
    void return_list_of_persons_given_full_lastName(){
        var list = PersonTestsUtils.createPersonsWithDoeInLastName().subList(0, 2);
        var actual = PersonTestsUtils.personsResponseListFromPersonsList(list);

        when(personRepository.findByLastNameContainingIgnoreCase("Doe")).thenReturn(list);
        var expected = personService.searchPersonByLastName("Doe");

        assertEquals(actual, expected);
    }

    @Test
    void return_list_of_persons_given_part_of_lastName(){
        var list = PersonTestsUtils.createPersonsWithDoeInLastName();
        var actual = PersonTestsUtils.personsResponseListFromPersonsList(list);

        when(personRepository.findByLastNameContainingIgnoreCase("Do")).thenReturn(list);
        var expected = personService.searchPersonByLastName("Do");

        assertEquals(actual, expected);
    }

    @Test
    void delete_person_by_email(){
        String email = "unique@email.com";
        var uniquePerson = PersonTestsUtils.createUniquePerson(email);
        var actual = PersonTestsUtils.personsResponseFromPerson(uniquePerson);

        when(personRepository.deleteByEmail(email)).thenReturn(uniquePerson);
        var expected = personService.deletePerson(email);

        assertEquals(expected, actual);
    }

    @Test
    void update_person_details(){
        String email = "unique@email.com";
        var oldPerson = PersonTestsUtils.createUniquePerson(email);
        oldPerson.setFirstName("fahed");
        var newPersonRequest = PersonTestsUtils.createPersonRequestFromPerson(oldPerson);
        var actual = PersonTestsUtils.personsResponseFromPerson(oldPerson);

        when(personRepository.findByEmail(email)).thenReturn(Optional.of(oldPerson));
        var expected = personService.updatePersonDetails(newPersonRequest);

        assertEquals(expected, actual);
    }

    @Test
    void return_curriculum_vitae_given_person(){
        String email = "unique@email.com";
        var person = PersonTestsUtils.createUniquePerson(email);
        var cv = CurriculumVitae.builder()
                .person(person)
                .experiences(new ArrayList<>())
                .build();
        person.setCurriculumVitae(cv);
        var actual  = person.getCurriculumVitae();

        when(personRepository.findByEmail(email)).thenReturn(Optional.of(person));
        var expected = personService.getPersonCv(email);

        verify(personRepository, times(1)).findByEmail(email);
        assertEquals(expected, actual);
    }

    @Test
    void retrieve_person_given_email(){
        var person = PersonTestsUtils.createUniquePerson("fahed@gmail.com");
        when(personRepository.findByEmail("fahed@gmail.com")).thenReturn(Optional.ofNullable(person));
        var personResponse = PersonTestsUtils.personsResponseFromPerson(person);

        var expected = personService.getPersonDetails("fahed@gmail.com");

        verify(personRepository, times(1)).findByEmail("fahed@gmail.com");
        assertEquals(expected, personResponse);

    }
}
