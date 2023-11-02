package com.zidani.gestioncv.repo;

import com.zidani.gestioncv.model.Experience;
import com.zidani.gestioncv.repo.ExperienceRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ExperienceRepositoryTests {
    @Autowired
    private ExperienceRepository experienceRepository;
    private Experience testExperience;

    @BeforeEach
    void setup() {
        testExperience = Experience.builder()
                .year(2022)
                .nature("Work")
                .title("Software Developer")
                .description("Worked on web application development.")
                .website("https://example.com")
                .build();
    }

    @AfterEach
    @Transactional
    void cleanUp() {
        experienceRepository.deleteAll();
    }

    @Test
    void testSaveAndRetrieveExperience() {
        experienceRepository.save(testExperience);
        var savedExperience = experienceRepository.findById(testExperience.getId());

        assertTrue(savedExperience.isPresent());

        savedExperience.ifPresent(saved -> {
            assertEquals(2022, saved.getYear());
            assertEquals("Work", saved.getNature());
            assertEquals("Software Developer", saved.getTitle());
            assertEquals("Worked on web application development.", saved.getDescription());
            assertEquals("https://example.com", saved.getWebsite());
        });
    }

    @Test
    void testUpdateExperience() {
        experienceRepository.save(testExperience);
        var savedExperience = experienceRepository.findById(testExperience.getId());

        assertTrue(savedExperience.isPresent());

        savedExperience.ifPresent(saved -> saved.setYear(2023));
        experienceRepository.save(savedExperience.orElse(null));
        var updatedExperience = experienceRepository.findById(testExperience.getId());

        updatedExperience.ifPresent(updated -> {
            assertEquals(2023, updated.getYear());
        });
    }

    @Test
    void testDeleteExperience() {
        experienceRepository.save(testExperience);
        experienceRepository.delete(testExperience);
        var deletedExperience = experienceRepository.findById(testExperience.getId());

        assertTrue(deletedExperience.isEmpty());
    }


}
