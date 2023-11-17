package com.zidani.gestioncv.curriculumVitaeManagment;

import com.zidani.gestioncv.curriculumVitaeManagment.Exceptions.CurriculumVitaeNotFoundException;
import com.zidani.gestioncv.personManagment.Exceptions.PersonNotFoundException;
import com.zidani.gestioncv.personManagment.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CurriculumVitaeService {
    private final PersonRepository personRepository;
    private final CurriculumVitaeRepository curriculumVitaeRepository;

    public Long createCV(String email) {
        var  person = personRepository.findByEmail(email)
                .orElseThrow(() -> new PersonNotFoundException(email));

        var newCv = CurriculumVitae.builder()
                .person(person)
                .build();

        var addedCv = curriculumVitaeRepository.save(newCv);

        return addedCv.getId();
    }

    public void deleteCv(Long id) {
        var cvToDelete = curriculumVitaeRepository.findById(id)
                .orElseThrow(() -> new CurriculumVitaeNotFoundException(id));
        log.info("deleting cv with id {} , and the related to person with email : {}", id, cvToDelete.getPerson().getEmail());
        curriculumVitaeRepository.deleteById(id);
    }
}
