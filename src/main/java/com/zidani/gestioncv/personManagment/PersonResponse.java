package com.zidani.gestioncv.personManagment;


import com.zidani.gestioncv.curriculumVitaeManagment.CurriculumVitae;

import java.time.LocalDate;
public record PersonResponse(
        String firstName,
        String lastName,
        String email,
        String webSite,
        LocalDate birthDay,
        CurriculumVitae curriculumVitae
) {}
