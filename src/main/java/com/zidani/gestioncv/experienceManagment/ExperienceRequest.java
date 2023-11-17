package com.zidani.gestioncv.experienceManagment;

import lombok.Builder;

@Builder
public record ExperienceRequest(
        int year,
        String nature,
        String title,
        String description,
        String website
) {
}
