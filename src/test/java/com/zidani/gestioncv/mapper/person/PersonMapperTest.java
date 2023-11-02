package com.zidani.gestioncv.mapper.person;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PersonMapperTest {

    @Test
    void WhenMappingPersonToPersonRequest_FieldsShouldMatch(){
        var person = PersonCreator.createUniquePerson("test@Email.com");
        var personRequest = PersonMapper.mapToPersonResponse(person);

        Assertions.assertEquals(person.getFirstName(), personRequest.getFirstName());
        Assertions.assertEquals(person.getLastName(), personRequest.getLastName());
        Assertions.assertEquals(person.getEmail(), personRequest.getEmail());
        Assertions.assertEquals(person.getBirthDay(), personRequest.getBirthDay());
        Assertions.assertEquals(person.getWebSite(), personRequest.getWebSite());
    }
}
