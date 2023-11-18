package com.zidani.gestioncv.curriculumVitaeManagement;

import com.zidani.gestioncv.curriculumVitaeManagment.CurriculumVitae;
import com.zidani.gestioncv.curriculumVitaeManagment.CurriculumVitaeRepository;
import com.zidani.gestioncv.curriculumVitaeManagment.CurriculumVitaeService;
import com.zidani.gestioncv.personManagementTests.PersonTestsUtils;
import com.zidani.gestioncv.personManagment.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CurriculumVitaeServiceShould {
    @MockBean
    private CurriculumVitaeRepository curriculumVitaeRepository;
    @MockBean
    private PersonRepository personRepository;
    @Autowired
    private CurriculumVitaeService curriculumVitaeService;

    @Test
    void create_cv_for_user_given_person_email(){
        var person = PersonTestsUtils.createUniquePerson("unique@email.com");
        when(personRepository.findByEmail("unique@email.com")).thenReturn(Optional.ofNullable(person));
        var newCv = CurriculumVitae.builder()
                .person(person)
                .build();
        when(curriculumVitaeRepository.save(newCv)).thenReturn(newCv);
        var actualID = newCv.getId();
        var expectedID = curriculumVitaeService.createCV("unique@email.com");

        assertEquals(expectedID, actualID);
    }

    @Test
   void delete_cv_given_cv_id(){
        var person = PersonTestsUtils.createUniquePerson("unique@email.com");
        var actualCv = CurriculumVitae.builder()
                .person(person)
                .build();
        when(curriculumVitaeRepository.findById(actualCv.getId())).thenReturn(Optional.of(actualCv));

        curriculumVitaeService.deleteCv(actualCv.getId());

        verify(curriculumVitaeRepository, times(1)).deleteById(actualCv.getId());
    }
}
