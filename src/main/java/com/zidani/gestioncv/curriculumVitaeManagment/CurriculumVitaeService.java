package com.zidani.gestioncv.curriculumVitaeManagment;

import com.zidani.gestioncv.curriculumVitaeManagment.Exceptions.CurriculumVitaeNotFoundException;
import com.zidani.gestioncv.personManagment.Exceptions.PersonNotFoundException;
import com.zidani.gestioncv.personManagment.Person;
import com.zidani.gestioncv.personManagment.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class CurriculumVitaeService {
    private final PersonRepository personRepository;
    private final CurriculumVitaeRepository curriculumVitaeRepository;

    public Long createCV(String email) {
        var  person = personRepository.findByEmail(email)
                .orElseThrow(() -> new PersonNotFoundException(email));

        var newCv = CurriculumVitae.builder()
                .person(person)
                .experiences(new ArrayList<>())
                .build();

        var addedCv = curriculumVitaeRepository.save(newCv);
        person.setCurriculumVitae(addedCv);
        personRepository.save(person);

        log.info("Created Curriculum Vitae with id {} for person with email: {}", addedCv.getId(), email);
        return addedCv.getId();
    }

    public void deleteCv(Long id) {
        var cvToDelete = curriculumVitaeRepository.findById(id)
                .orElseThrow(() -> new CurriculumVitaeNotFoundException(id));
        log.info("Deleting Curriculum Vitae with id {} related to person with email: {}", id, cvToDelete.getPerson().getEmail());
        curriculumVitaeRepository.deleteById(id);
    }

    public boolean isOwner(String username, Long cvId) {
        // Récupère le CV par ID
        Optional<CurriculumVitae> cv =curriculumVitaeRepository.findById(cvId) ;

        // Vérifie si le CV existe
        if (cv.isPresent()) {
            // Récupère le propriétaire du CV
            String cvOwner = cv.get().getPerson().getEmail(); // Supposons que 'getOwner()' renvoie le propriétaire du CV

            // Compare le propriétaire du CV avec l'utilisateur extrait du token
            return username.equals(cvOwner);
        }

        // Le CV n'existe pas, donc l'utilisateur ne peut pas être le propriétaire
        return false;
    }


}

