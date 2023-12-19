package com.zidani.gestioncv.personManagment;

import com.zidani.gestioncv.curriculumVitaeManagment.CurriculumVitae;
import com.zidani.gestioncv.experienceManagment.Experience;
import com.zidani.gestioncv.personManagment.Exceptions.PersonNotFoundException;
import com.zidani.gestioncv.personManagment.mapper.PersonMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public void addPerson(PersonRequest personRequest){
        var newPerson =  Person.builder()
                .firstName(personRequest.firstName())
                .lastName(personRequest.lastName())
                .webSite(personRequest.webSite())
                .email(personRequest.email())
                .password(personRequest.password())
                .birthDay(personRequest.birthDay())
                .build();
        log.info(String.valueOf(newPerson));

        personRepository.save(newPerson);
        log.info("Person {} {} is saved", newPerson.getFirstName(), newPerson.getLastName());
    }

    public List<PersonResponse> getAllPersons(){
        var personsList = personRepository.findAll();
        log.info("Retrieved {} persons", personsList.size());

        return personsList.stream().map(personMapper::personToPersonResponse).toList();
    }

    public List<PersonResponse> searchPersonByFirstName(String firstName){
        var result = personRepository.findByFirstNameContainingIgnoreCase(firstName)
                .stream().map(personMapper::personToPersonResponse).toList();
        log.info("Found {} persons with first name '{}'", result.size(), firstName);

        return result;
    }

    public List<PersonResponse> searchPersonByLastName(String lastName){
        var result = personRepository.findByLastNameContainingIgnoreCase(lastName)
                .stream().map(personMapper::personToPersonResponse).toList();
        log.info("Found {} persons with last name '{}'", result.size(), lastName);

        return result;
    }

    public PersonResponse deletePerson(String email){
        var deletedPerson = personRepository.deleteByEmail(email);
        log.info("Deleted person with email '{}'", email);

        return personMapper.personToPersonResponse(deletedPerson);
    }

    public PersonResponse updatePersonDetails(PersonRequest updatedPerson){
        var oldPerson = personRepository.findByEmail(updatedPerson.email())
                .orElseThrow(() -> new PersonNotFoundException(updatedPerson.email()));
        oldPerson.setFirstName(updatedPerson.firstName());
        oldPerson.setLastName(updatedPerson.lastName());
        oldPerson.setBirthDay(updatedPerson.birthDay());
        oldPerson.setWebSite(updatedPerson.webSite());

        personRepository.save(oldPerson);
        log.info("Updated details for person with email '{}'", updatedPerson.email());

        return personMapper.personToPersonResponse(oldPerson);
    }

    public CurriculumVitae getPersonCv(String email){
        var person = personRepository.findByEmail(email)
                .orElseThrow(() -> new PersonNotFoundException(email));
        log.info("retrieving {} cv from the database, the cv :  \n  {} ", email, person.getCurriculumVitae());
        return person.getCurriculumVitae();
    }

    public PersonResponse getPersonDetails(String email) {
       var retrievedPerson = personRepository.findByEmail(email)
                .orElseThrow(() -> new PersonNotFoundException(email));

       return personMapper.personToPersonResponse(retrievedPerson);
    }

    public Page<PersonResponse> getPersonsPagination(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("lastName"));
        String excludeFirstName = "admin";

        var personsPage = personRepository.findAllExcludeFirstName(excludeFirstName, pageable);
        log.info("Retrieved page {} with {} persons per page, excluding first name '{}'", page, size, excludeFirstName);

        return personsPage.map(personMapper::personToPersonResponse);
    }


    public Page<Person> searchByFirstName(Pageable pageable, String firstName) {
        return personRepository.findByFirstNameContainingIgnoreCase(pageable,firstName);
    }

    public Page<Person> searchByLastName(Pageable pageable, String lastName) {
        return personRepository.findByLastNameContainingIgnoreCase(pageable,lastName);
    }

    public Page<Person> searchByFirstNameContainingAndLastName(Pageable pageable, String firstName, String lastName) {
        return personRepository.findByFirstNameContainingAndLastNameContainingIgnoreCase(pageable,firstName, lastName);
    }
    public Optional<Person> getPersonByUsername(String username){
        return personRepository.findByEmail(username);
    }


    public boolean isOwner(String username, Long id) {
        // Récupère la personne par ID
        Optional<Person> person = personRepository.findById(id);

        // Vérifie si l'Experience existe
        if (person.isPresent()) {
            String owner = person.get().getEmail();

            return username.equals(owner);
        }

        // l'Experience n'existe pas, donc l'utilisateur ne peut pas être le propriétaire
        return false;
    }

}

