package com.zidani.gestioncv.experienceManagment;

import com.zidani.gestioncv.curriculumVitaeManagment.CurriculumVitaeRepository;
import com.zidani.gestioncv.curriculumVitaeManagment.Exceptions.CurriculumVitaeNotFoundException;
import com.zidani.gestioncv.personManagment.Exceptions.PersonNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperienceService {
    private final CurriculumVitaeRepository curriculumVitaeRepository;
    private final ExperienceMapper experienceMapper;
    private final ExperienceRepository experienceRepository;


    public Long addExperience(Long cvId, ExperienceRequest experienceRequest ) {
        var cv = curriculumVitaeRepository.findById(cvId)
                .orElseThrow(() -> new CurriculumVitaeNotFoundException(cvId));
        var newExperience = experienceMapper.experienceRequestToExperience(experienceRequest);
        newExperience.setCurriculumVitae(cv);
        cv.getExperiences().add(newExperience);
        experienceRepository.save(newExperience);
        curriculumVitaeRepository.save(cv);

        return newExperience.getId();
    }
}
