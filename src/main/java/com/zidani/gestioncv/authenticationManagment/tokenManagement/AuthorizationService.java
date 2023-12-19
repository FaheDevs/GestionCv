package com.zidani.gestioncv.authenticationManagment.tokenManagement;

import com.zidani.gestioncv.curriculumVitaeManagment.CurriculumVitaeService;
import com.zidani.gestioncv.experienceManagment.ExperienceService;
import com.zidani.gestioncv.personManagment.Person;
import com.zidani.gestioncv.personManagment.PersonService;
import com.zidani.gestioncv.personManagment.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorizationService {
    PersonService personService;
    CurriculumVitaeService curriculumVitaeService;
    ExperienceService experienceService;



    @Autowired
    AuthorizationService(PersonService personService, CurriculumVitaeService curriculumVitaeService,ExperienceService experienceService){
        this.personService = personService;
        this.curriculumVitaeService = curriculumVitaeService;
        this.experienceService = experienceService;
    }

    public boolean isAdmin(String username) {
        Optional<Person> personOptional = personService.getPersonByUsername(username);

        // Si l'utilisateur n'existe pas, ou s'il n'est pas admin, retourne false
        return personOptional.isPresent() && personOptional.get().getRole() == Role.ADMIN;
    }
    public boolean isManager(String username) {
        Optional<Person> personOptional = personService.getPersonByUsername(username);
        // Si l'utilisateur n'existe pas, ou s'il n'est pas admin, retourne false
        return personOptional.isPresent() && personOptional.get().getRole() == Role.MANAGER;
    }

    public boolean hasRight(String username){
        return isAdmin(username) || isManager(username);
    }

    public boolean hasPersonRight(String username, Long id){
        if(hasRight(username)) return true;
        return personService.isOwner(username, id);


    }

    public boolean hasCvRight(String username, Long id){
        if(hasRight(username)) return true;
        return curriculumVitaeService.isOwner(username, id);

    }
    public boolean hasExperienceRight(String username, Long id){
        if(hasRight(username)) return true;
        return experienceService.isOwner(username, id);



    }
}
