package com.zidani.gestioncv.experienceManagementTests;

import com.zidani.gestioncv.curriculumVitaeManagment.CurriculumVitae;
import com.zidani.gestioncv.curriculumVitaeManagment.CurriculumVitaeRepository;
import com.zidani.gestioncv.experienceManagment.Experience;
import com.zidani.gestioncv.experienceManagment.ExperienceRepository;
import com.zidani.gestioncv.experienceManagment.ExperienceRequest;
import com.zidani.gestioncv.experienceManagment.ExperienceService;
import com.zidani.gestioncv.personManagementTests.PersonTestsUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ExperienceServiceShould {
    @MockBean
    private ExperienceRepository experienceRepository;
    @MockBean
    private CurriculumVitaeRepository curriculumVitaeRepository;
    @Autowired
    private ExperienceService experienceService;

    @Test
    void add_an_experience_given_cv_id(){
        var person = PersonTestsUtils.createUniquePerson("unique@email.com");
        var cv = CurriculumVitae.builder()
                .person(person)
                .experiences(new ArrayList<>())
                .build();

        when(curriculumVitaeRepository.findById(cv.getId())).thenReturn(Optional.of(cv));

        var experience = ExperienceTestsUtils.createExperience(cv);
        var experienceRequest = ExperienceTestsUtils.createExperienceRequest();
        var actualId = experience.getId();

        when(experienceRepository.save(experience)).thenReturn(experience);
        var expectedExperienceID = experienceService.addExperience(cv.getId(), experienceRequest);
        experience.setTitle("newTitle");

        verify(experienceRepository, times(1)).save(experience);
        assertEquals(expectedExperienceID, actualId);
    }

    @Test
    void update_experience_given_experience_id(){
        var actual = setupExperience();
        when(experienceRepository.findById(actual.getId())).thenReturn(Optional.of(actual));
        var experienceRequest = ExperienceTestsUtils.createExperienceRequest();
        actual.setTitle(experienceRequest.title());

        var expected = experienceService.updateExperience(actual.getId(), experienceRequest);

        verify(experienceRepository, times(1)).save(actual);
        assertEquals(expected, actual);
    }

    @Test
    void delete_experience_given_experience_id(){
        var actual = setupExperience();

        experienceService.deleteExperience(actual.getId());

        verify(experienceRepository, times(1)).deleteById(actual.getId());
    }

    private static Experience setupExperience() {
        var person = PersonTestsUtils.createUniquePerson("unique@email.com");
        var cv = CurriculumVitae.builder()
                .person(person)
                .experiences(new ArrayList<>())
                .build();
        return ExperienceTestsUtils.createExperience(cv);
    }

}
