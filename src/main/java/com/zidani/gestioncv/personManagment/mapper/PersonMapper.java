package com.zidani.gestioncv.personManagment.mapper;

import com.zidani.gestioncv.personManagment.Person;
import com.zidani.gestioncv.personManagment.PersonRequest;
import com.zidani.gestioncv.personManagment.PersonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    @Mapping(target = "firstName", source = "person.firstName")
    @Mapping(target = "lastName", source = "person.lastName")
    @Mapping(target = "email", source = "person.email")
    @Mapping(target = "webSite", source = "person.webSite")
    @Mapping(target = "birthDay", source = "person.birthDay")
    PersonResponse personToPersonResponse(Person person);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "curriculumVitae", ignore = true)
    @Mapping(target = "role", ignore = true)
    Person personRequestToPerson(PersonRequest personRequest);

    @Mapping(target = "firstName", source = "person.firstName")
    @Mapping(target = "lastName", source = "person.lastName")
    @Mapping(target = "email", source = "person.email")
    @Mapping(target = "webSite", source = "person.webSite")
    @Mapping(target = "birthDay", source = "person.birthDay")
    @Mapping(target = "password", ignore = true)
    PersonRequest personToPersonRequest(Person person);
}

