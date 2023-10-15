package com.zidani.gestioncv.repo;

import com.zidani.gestioncv.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface PersonRepository extends  JpaRepository<Person, Long> {
    Optional<Person> findByEmail(String email);
}
