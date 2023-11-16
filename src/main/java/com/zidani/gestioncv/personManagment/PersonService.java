package com.zidani.gestioncv.personManagment;

import com.zidani.gestioncv.personManagment.mapper.PersonMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public void addPerson(PersonRequest personRequest){
        var newPerson =  Person.builder().firstName(personRequest.firstName())
                .lastName(personRequest.lastName())
                .webSite(personRequest.webSite())
                .email(personRequest.email())
                .password(personRequest.password())
                .birthDay(personRequest.birthDay()).build();
        personRepository.save(newPerson);
        log.info("person {} is saved", newPerson.getFirstName()+newPerson.getLastName());
    }

    public List<PersonResponse> getAllPersons(){
        var personsList = personRepository.findAll();

        return personsList.stream().map(personMapper::personToPersonResponse).toList();
    }

    public List<PersonResponse> searchPersonByFirstName(String firstName){
        return personRepository.findByFirstNameContainingIgnoreCase(firstName)
                .stream().map(personMapper::personToPersonResponse).toList();
    }

    public List<PersonResponse> searchPersonByLastName(String lastName){
        return personRepository.findByLastNameContainingIgnoreCase(lastName)
                .stream().map(personMapper::personToPersonResponse).toList();
    }

    public PersonResponse deletePerson(String email){
        var deletedPerson = personRepository.deleteByEmail(email);

        return personMapper.personToPersonResponse(deletedPerson);
    }

    public PersonResponse updatePersonDetails(PersonRequest updatedPerson){
        var oldPerson = personRepository.findByEmail(updatedPerson.email());
        var newPerson = personMapper.personRequestToPerson(updatedPerson);

        return personMapper.personToPersonResponse(newPerson);
    }
}
