package com.zidani.gestioncv.service;


import com.zidani.gestioncv.mapper.person.PersonCreator;
import com.zidani.gestioncv.repo.PersonRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
 class PersonServiceTest {
     @Autowired
    public PersonService personService;
     @Autowired
    public PersonRepository personRepository;

     @BeforeEach
     @AfterEach
     void emptyDataBase(){
         personRepository.deleteAll();
     }

    @Test
    void WhenNewPersonAdded_FoundInDB(){
       var personRequest = PersonCreator.createPersonRequest("firstName",
               "lastName",
               "fl@gmail.com");
        personService.addPerson(personRequest);

        var addedPerson = personRepository.findByFirstName(personRequest.getFirstName());
        if (addedPerson.isPresent()){
            assertEquals(personRequest.getFirstName(), addedPerson.get().getFirstName());
            assertEquals(personRequest.getLastName(), addedPerson.get().getLastName());
            assertEquals(personRequest.getEmail(), addedPerson.get().getEmail());
            assertEquals(personRequest.getWebSite(), addedPerson.get().getWebSite());
            assertEquals(personRequest.getBirthDay(), addedPerson.get().getBirthDay());
            assertEquals(personRequest.getPassword(), addedPerson.get().getPassword());
        }
    }

    @Test
    void getAllPersons_ShouldReturnAllPersons(){
        var personsList = PersonCreator.createPersons().stream().map(personRepository::save).toList();
        var expectedReturnedList = personService.getAllPersons();

        assertEquals(personsList.size(), expectedReturnedList.size());
    }

    @Test
    void searchByFirstName_ShouldGetAllPersonsWithFullFirstName(){
         var personsList = PersonCreator.createPersonsWithJohInFirstName().stream().map(personRepository::save).toList();
         var personListWithFistNameJohn = personService.searchPersonByFirstName("John");

         assertEquals(1, personListWithFistNameJohn.size());
    }

    @Test
    void searchByFirstName_ShouldGetAllPersonsWithPartOfFirstName(){
        var personsList = PersonCreator.createPersonsWithJohInFirstName().stream().map(personRepository::save).toList();
        var personListWithFistNameJohn = personService.searchPersonByFirstName("Joh");

        assertEquals(3, personListWithFistNameJohn.size());
    }

    @Test
    void searchByLastName_ShouldGetAllPersonsWithFullLastName(){
        var personsList = PersonCreator.createPersonsWithDoeInLastName().stream().map(personRepository::save).toList();
        var searchPersonByLastName = personService.searchPersonByLastName("doe");

        assertEquals(2, searchPersonByLastName.size());
    }

    @Test
    void searchByLastName_ShouldGetAllPersonsWithPartOfLastName(){
        var personsList = PersonCreator.createPersonsWithDoeInLastName().stream().map(personRepository::save).toList();
        var searchPersonByLastName = personService.searchPersonByLastName("Do");

        assertEquals(3, searchPersonByLastName.size());
    }

}
