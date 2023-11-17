package com.zidani.gestioncv.curriculumVitaeManagment.Exceptions;

public class CurriculumVitaeNotFoundException extends RuntimeException{

    public CurriculumVitaeNotFoundException(Long id) {
        super("CurriculumVitae with id :  " + id + "is not found ");
    }
}
