package com.zidani.gestioncv.experienceManagementTests;

import com.zidani.gestioncv.curriculumVitaeManagment.CurriculumVitae;
import com.zidani.gestioncv.experienceManagment.Experience;
import com.zidani.gestioncv.experienceManagment.ExperienceMapper;
import com.zidani.gestioncv.experienceManagment.ExperienceRequest;
import com.zidani.gestioncv.personManagment.PersonRequest;

public final class ExperienceTestsUtils {


    public static ExperienceRequest createExperienceRequest(){
        return ExperienceRequest.builder()
                .year(2022)
                .nature("Work")
                .title("Software Engineer")
                .description("Developed and maintained software applications.")
                .website("https://www.example.com")
                .build();
    }

    public static Experience createExperience(CurriculumVitae cv){
        return Experience.builder()
                .curriculumVitae(cv)
                .year(2022)
                .nature("Work")
                .title("Software Engineer")
                .description("Developed and maintained software applications.")
                .website("https://www.example.com")
                .build();
    }
}
