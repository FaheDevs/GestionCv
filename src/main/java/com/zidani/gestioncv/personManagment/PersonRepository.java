package com.zidani.gestioncv.personManagment;

import com.zidani.gestioncv.curriculumVitaeManagment.CurriculumVitae;
import com.zidani.gestioncv.personManagment.Person;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@Hidden
public interface PersonRepository extends  JpaRepository<Person, Long> {
    Optional<Person> findByEmail(String email);
    Optional<Person> findByFirstName(String firstName);
    List<Person> findByFirstNameContainingIgnoreCase(String firstName);
    List<Person> findByLastNameContainingIgnoreCase(String lastName);
    Person deleteByEmail(String email);

    @Query("SELECT p FROM Person p WHERE LOWER(p.firstName) <> LOWER(:firstName)")
    Page<Person> findAllExcludeFirstName(@Param("firstName") String firstName, Pageable pageable);

    Page<Person> findByLastNameContainingIgnoreCase(Pageable pageable,String Prenom);
    Page<Person> findByFirstNameContainingAndLastNameContainingIgnoreCase(Pageable pageable,String Nom , String Prenom);
    Page<Person> findByFirstNameContainingIgnoreCase(Pageable pageable,String Nom);}
