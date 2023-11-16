package com.zidani.gestioncv.personManagementTests;

import com.zidani.gestioncv.personManagment.Person;
import com.zidani.gestioncv.personManagment.PersonRequest;
import com.zidani.gestioncv.personManagment.PersonResponse;
import com.zidani.gestioncv.personManagment.mapper.PersonMapper;
import com.zidani.gestioncv.personManagment.mapper.PersonMapperImpl;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@RequiredArgsConstructor
public class PersonTestsUtils {
    private static final PersonMapper personMapper = new PersonMapperImpl();
    public static PersonRequest createPersonRequest(String firstName,
                                                    String lastName,
                                                    String email){
        return PersonRequest.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .webSite("https://www.example.com")
                .birthDay(LocalDate.of(1999, 8, 3))
                .password("password123")
                .build();
    }

    public static Person createUniquePerson(String email){
        return Person.builder()
                .firstName("Alice")
                .lastName("Johnson")
                .webSite("aliceWebsite.com")
                .birthDay(LocalDate.of(1995, 5, 15))
                .email(email)
                .password("alicePass")
                .build();
    }

    public static List<Person> createPersons(){
        var person2 = Person.builder()
                .firstName("Alice")
                .lastName("Johnson")
                .webSite("aliceWebsite.com")
                .birthDay(LocalDate.of(1995, 5, 15))
                .email("alice@example.com")
                .password("alicePass")
                .build();
        var person3 = Person.builder()
                .firstName("Bob")
                .lastName("Smith")
                .webSite("bobWebsite.com")
                .birthDay(LocalDate.of(1990, 8, 20))
                .email("bob@example.com")
                .password("bobPass")
                .build();

        var person4 = Person.builder()
                .firstName("Eve")
                .lastName("Brown")
                .webSite("eveWebsite.com")
                .birthDay(LocalDate.of(1987, 3, 25))
                .email("eve@example.com")
                .password("evePass")
                .build();

        return List.of(person2, person3, person4);
    }
    public static List<Person> createPersonsWithJohInFirstName(){
        var person2 = Person.builder().firstName("John")
                .lastName("Doe")
                .webSite("johndoe.com")
                .birthDay(LocalDate.of(1995, 5, 15))
                .email("john.doe@example.com")
                .password("secure123")
                .build();

        var person3 = Person.builder().firstName("Johanna")
                .lastName("Smith")
                .webSite("johannasmith.net")
                .birthDay(LocalDate.of(1992, 8, 20))
                .email("johanna.smith@gmail.com")
                .password("mypassword")
                .build();

        var person4 = Person.builder().firstName("Johy")
                .lastName("Walker")
                .webSite("johywalker.org")
                .birthDay(LocalDate.of(1990, 7, 3))
                .email("johnny.walker@example.org")
                .password("johnnyPass")
                .build();

        return List.of(person2, person3, person4);
    }
    public static List<Person> createPersonsWithDoeInLastName(){
        var person5 = Person.builder().firstName("Alice")
                .lastName("Doe")
                .webSite("alicedoe.com")
                .birthDay(LocalDate.of(1993, 4, 28))
                .email("alice.doe@example.com")
                .password("alicePass")
                .build();

        var person6 = Person.builder().firstName("Robert")
                .lastName("Doe")
                .webSite("robertdoe.net")
                .birthDay(LocalDate.of(1987, 9, 12))
                .email("robert.doe@gmail.com")
                .password("robertPass")
                .build();

        var person7 = Person.builder().firstName("Laura")
                .lastName("Dorr")
                .webSite("lauradoerr.org")
                .birthDay(LocalDate.of(1991, 6, 5))
                .email("laura.doerr@example.org")
                .password("lauraPass")
                .build();


        return List.of(person5, person6, person7);
    }

    public static Person createPersonFromPersonRequest(PersonRequest personRequest){
        return personMapper.personRequestToPerson(personRequest);
    }
    public static PersonRequest createPersonRequestFromPerson(Person person) {
        return personMapper.personToPersonRequest(person);
    }

    public static List<PersonResponse> personsResponseListFromPersonsList(List<Person> personList){
        return  personList.stream().map(personMapper::personToPersonResponse).toList();
    }

    public static PersonResponse personsResponseFromPerson(Person person){
        return  personMapper.personToPersonResponse(person);
    }

}
