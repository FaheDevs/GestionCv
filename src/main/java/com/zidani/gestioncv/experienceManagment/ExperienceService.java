package com.zidani.gestioncv.experienceManagment;

import com.zidani.gestioncv.curriculumVitaeManagment.CurriculumVitae;
import com.zidani.gestioncv.curriculumVitaeManagment.CurriculumVitaeRepository;
import com.zidani.gestioncv.curriculumVitaeManagment.Exceptions.CurriculumVitaeNotFoundException;
import com.zidani.gestioncv.experienceManagment.Exceptions.ExperienceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ExperienceService {
    private final CurriculumVitaeRepository curriculumVitaeRepository;
    private final ExperienceMapper experienceMapper;
    private final ExperienceRepository experienceRepository;

    @Transactional
    public Long addExperience(Long cvId, ExperienceRequest experienceRequest) {
        var cv = curriculumVitaeRepository.findById(cvId)
                .orElseThrow(() -> new CurriculumVitaeNotFoundException(cvId));
        var newExperience = experienceMapper.experienceRequestToExperience(experienceRequest);
        newExperience.setCurriculumVitae(cv);
        cv.getExperiences().add(newExperience);
        experienceRepository.save(newExperience);
        curriculumVitaeRepository.save(cv);

        //log.info("Added new experience with id {} to Curriculum Vitae with id {}", newExperience.getId(), cvId);
        return newExperience.getId();
    }

    public Experience updateExperience(Long id, ExperienceRequest experienceRequest) {
        var oldExperience = experienceRepository.findById(id)
                .orElseThrow(() -> new ExperienceNotFoundException(id));

        oldExperience.setYear(experienceRequest.year());
        oldExperience.setNature(experienceRequest.nature());
        oldExperience.setTitle(experienceRequest.title());
        oldExperience.setDescription(experienceRequest.description());
        oldExperience.setWebsite(experienceRequest.website());

        experienceRepository.save(oldExperience);
        //log.info("Updated experience with id {}", id);
        return oldExperience;
    }

    public void deleteExperience(Long id) {
        try {
            experienceRepository.deleteById(id);
            // log.info("Deleted experience with id : {}", id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ExperienceNotFoundException(id);
        }
    }

    public Page<?> searchByExperienceTitle(String title, Pageable pageable) {
        return experienceRepository.findByTitle(title, pageable);
    }

    public boolean isOwner(String username, Long expId) {
        // Récupère le Experience par ID
        Optional<Experience> exp = experienceRepository.findById(expId);

        // Vérifie si l'Experience existe
        if (exp.isPresent()) {
            // Récupère le propriétaire de l'Experience
            String expOwner = exp.get().getCurriculumVitae().getPerson().getEmail(); // Supposons que 'getOwner()' renvoie le propriétaire du CV

            // Compare le propriétaire de l'Experience  avec l'utilisateur
            return username.equals(expOwner);
        }

        // l'Experience n'existe pas, donc l'utilisateur ne peut pas être le propriétaire
        return false;
    }
}
