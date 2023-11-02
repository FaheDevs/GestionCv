package com.zidani.gestioncv.mapper.person;

import com.zidani.gestioncv.dto.person.PersonRequest;
import com.zidani.gestioncv.model.Person;

import java.util.Date;
import java.util.List;

public final class PersonCreator {
    public PersonCreator() {
    }

    public static PersonRequest createPersonRequest(String firstName,
                                                    String lastName,
                                                    String email){
        return PersonRequest.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .webSite("https://www.example.com")
                .birthDay(new Date(1999,8,03))
                .password("password123")
                .build();
    }

    public static Person createUniquePerson(String email){
        return Person.builder()
                .firstName("Alice")
                .lastName("Johnson")
                .webSite("aliceWebsite.com")
                .birthDay(new Date(1995, 5, 15))
                .email(email)
                .password("alicePass")
                .build();
    }

    public static List<Person> createPersons(){
        var person2 = Person.builder()
                .firstName("Alice")
                .lastName("Johnson")
                .webSite("aliceWebsite.com")
                .birthDay(new Date(1995, 5, 15))
                .email("alice@example.com")
                .password("alicePass")
                .build();
        var person3 = Person.builder()
                .firstName("Bob")
                .lastName("Smith")
                .webSite("bobWebsite.com")
                .birthDay(new Date(1990, 8, 20))
                .email("bob@example.com")
                .password("bobPass")
                .build();

        var person4 = Person.builder()
                .firstName("Eve")
                .lastName("Brown")
                .webSite("eveWebsite.com")
                .birthDay(new Date(1987, 3, 25))
                .email("eve@example.com")
                .password("evePass")
                .build();

        return List.of(person2, person3, person4);
    }
    public static List<Person> createPersonsWithJohInFirstName(){
        var person2 = Person.builder().firstName("John")
                .lastName("Doe")
                .webSite("johndoe.com")
                .birthDay(new Date(1995, 5, 15))
                .email("john.doe@example.com")
                .password("secure123")
                .build();

        var person3 = Person.builder().firstName("Johanna")
                .lastName("Smith")
                .webSite("johannasmith.net")
                .birthDay(new Date(1992, 8, 20))
                .email("johanna.smith@gmail.com")
                .password("mypassword")
                .build();

        var person4 = Person.builder().firstName("Johy")
                .lastName("Walker")
                .webSite("johywalker.org")
                .birthDay(new Date(1990, 7, 3))
                .email("johnny.walker@example.org")
                .password("johnnyPass")
                .build();

        return List.of(person2, person3, person4);
    }
    public static List<Person> createPersonsWithDoeInLastName(){
        var person5 = Person.builder().firstName("Alice")
                .lastName("Doe")
                .webSite("alicedoe.com")
                .birthDay(new Date(1993, 4, 28))
                .email("alice.doe@example.com")
                .password("alicePass")
                .build();

        var person6 = Person.builder().firstName("Robert")
                .lastName("Doe")
                .webSite("robertdoe.net")
                .birthDay(new Date(1987, 9, 12))
                .email("robert.doe@gmail.com")
                .password("robertPass")
                .build();

        var person7 = Person.builder().firstName("Laura")
                .lastName("Dorr")
                .webSite("lauradoerr.org")
                .birthDay(new Date(1991, 6, 5))
                .email("laura.doerr@example.org")
                .password("lauraPass")
                .build();


        return List.of(person5, person6, person7);
    }

}
