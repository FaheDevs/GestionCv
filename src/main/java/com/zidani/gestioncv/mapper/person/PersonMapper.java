package com.zidani.gestioncv.mapper.person;

import com.zidani.gestioncv.dto.person.PersonResponse;
import com.zidani.gestioncv.model.Person;

public final class PersonMapper {

    public PersonMapper() {
    }

    public static PersonResponse mapToPersonResponse(Person person){
        return PersonResponse.builder()
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .email(person.getEmail())
                .webSite(person.getWebSite())
                .birthDay(person.getBirthDay()).build();
    }
}
