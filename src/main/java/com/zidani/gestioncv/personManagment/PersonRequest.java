package com.zidani.gestioncv.personManagment;



import lombok.Builder;

import java.time.LocalDate;
@Builder
public record PersonRequest(
        String firstName,
        String lastName,
        String email,
        String webSite,
        LocalDate birthDay,
        String password
) {
}






