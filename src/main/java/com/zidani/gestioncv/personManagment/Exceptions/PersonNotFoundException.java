package com.zidani.gestioncv.personManagment.Exceptions;

public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(String email) {
        super("Person not found for email: " + email);
    }
}