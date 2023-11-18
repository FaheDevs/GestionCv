package com.zidani.gestioncv.experienceManagment.Exceptions;

public class ExperienceNotFoundException extends RuntimeException{
    public ExperienceNotFoundException(Long id) {
        super("experience with id : " + id + " not found");
    }
}
