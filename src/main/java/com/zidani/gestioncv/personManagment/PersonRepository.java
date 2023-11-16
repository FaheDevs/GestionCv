package com.zidani.gestioncv.personManagment;

import com.zidani.gestioncv.personManagment.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface PersonRepository extends  JpaRepository<Person, Long> {
    Optional<Person> findByEmail(String email);
    Optional<Person> findByFirstName(String firstName);
    List<Person> findByFirstNameContainingIgnoreCase(String firstName);
    List<Person> findByLastNameContainingIgnoreCase(String lastName);
    Person deleteByEmail(String email);
}
