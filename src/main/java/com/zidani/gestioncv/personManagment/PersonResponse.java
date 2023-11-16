package com.zidani.gestioncv.personManagment;


import java.time.LocalDate;
public record PersonResponse(
        String firstName,
        String lastName,
        String email,
        String webSite,
        LocalDate birthDay
) {}
