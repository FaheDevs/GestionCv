package com.zidani.gestioncv;

import com.zidani.gestioncv.model.CurriculumVitae;
import com.zidani.gestioncv.model.Experience;
import com.zidani.gestioncv.model.Person;
import com.zidani.gestioncv.repo.CurriculumVitaeRepository;
import com.zidani.gestioncv.repo.ExperienceRepository;
import com.zidani.gestioncv.repo.PersonRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Slf4j
class CurriculumVitaeRepositoryTests {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CurriculumVitaeRepository curriculumVitaeRepository;

    @Autowired
    private ExperienceRepository experienceRepository;

    private Person testPerson;
    private CurriculumVitae testCurriculumVitae;
    private Experience testExperience;


    @BeforeEach
    void setup() {
        testPerson = Person.builder()
                .firstName("Alice")
                .lastName("Johnson")
                .email("alice.johnson@example.com")
                .webSite("https://alicejohnson.com")
                .birthDay(new Date(1990, Calendar.AUGUST, 15))
                .password("alicepassword")
                .build();

        testExperience = Experience.builder()
                .year(2022)
                .nature("Work")
                .title("Software Developer")
                .description("Worked on web application development.")
                .website("https://example.com")
                .build();

        testCurriculumVitae = CurriculumVitae.builder()
                .experiences(List.of(testExperience))
                .build();

        testPerson.setCurriculumVitae(testCurriculumVitae);
        testExperience.setCurriculumVitae(testCurriculumVitae);

        personRepository.save(testPerson);
        experienceRepository.save(testExperience);
        curriculumVitaeRepository.save(testCurriculumVitae);
    }

    @AfterEach
    @Transactional
    void cleanUp() {
        experienceRepository.deleteAll();
        curriculumVitaeRepository.deleteAll();
        personRepository.deleteAll();
    }

    @Test
    void testSaveAndRetrieveCurriculumVitae() {
        var savedCV = curriculumVitaeRepository.findById(testCurriculumVitae.getId());

        assertTrue(savedCV.isPresent());

        savedCV.ifPresent(cv -> {
            assertEquals(testCurriculumVitae.getId(), cv.getId());
            assertEquals(1, cv.getExperiences().size());
        });
    }

    @Test
    void testUpdateCurriculumVitae() {
        var savedCV = curriculumVitaeRepository.findById(testCurriculumVitae.getId());

        assertTrue(savedCV.isPresent());

        savedCV.ifPresent(cv -> cv.getExperiences().get(0).setYear(2023));
        curriculumVitaeRepository.save(savedCV.orElse(null));

        var updatedCV = curriculumVitaeRepository.findById(testCurriculumVitae.getId());

        updatedCV.ifPresent(cv -> {
            assertEquals(2022, cv.getExperiences().get(0).getYear());
        });
    }
}
