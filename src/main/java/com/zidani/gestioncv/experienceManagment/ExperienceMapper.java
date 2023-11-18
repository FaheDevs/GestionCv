package com.zidani.gestioncv.experienceManagment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ExperienceMapper {

    @Mapping(target = "curriculumVitae", ignore = true)
    Experience experienceRequestToExperience(ExperienceRequest experienceRequest);
}
