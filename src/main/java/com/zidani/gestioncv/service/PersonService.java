package com.zidani.gestioncv.service;

import com.zidani.gestioncv.dto.person.PersonRequest;
import com.zidani.gestioncv.dto.person.PersonResponse;
import com.zidani.gestioncv.mapper.person.PersonMapper;
import com.zidani.gestioncv.model.Person;
import com.zidani.gestioncv.repo.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonService {
    private final PersonRepository personRepository;

    public void addPerson(PersonRequest personRequest){
        var newPerson =  Person.builder().firstName(personRequest.getFirstName())
                .lastName(personRequest.getLastName())
                .webSite(personRequest.getWebSite())
                .email(personRequest.getEmail())
                .password(personRequest.getPassword())
                .birthDay(personRequest.getBirthDay()).build();
        personRepository.save(newPerson);
        log.info("person {} is saved", newPerson.getFirstName()+newPerson.getLastName());
    }

    public List<PersonResponse> getAllPersons(){
        var personsList = personRepository.findAll();

        return personsList.stream().map(PersonMapper::mapToPersonResponse).toList();
    }

    public List<PersonResponse> searchPersonByFirstName(String firstName){
        return personRepository.findByFirstNameContainingIgnoreCase(firstName)
                .stream().map(PersonMapper::mapToPersonResponse).toList();
    }

    public List<PersonResponse> searchPersonByLastName(String lastName){
        return personRepository.findByLastNameContainingIgnoreCase(lastName)
                .stream().map(PersonMapper::mapToPersonResponse).toList();
    }
}
