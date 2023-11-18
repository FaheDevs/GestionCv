package com.zidani.gestioncv.curriculumVitaeManagment;

import com.zidani.gestioncv.curriculumVitaeManagment.Exceptions.CurriculumVitaeNotFoundException;
import com.zidani.gestioncv.personManagment.Exceptions.PersonNotFoundException;
import com.zidani.gestioncv.personManagment.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


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
}

