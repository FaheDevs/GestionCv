package com.zidani.gestioncv.repo;

import com.zidani.gestioncv.model.CurriculumVitae;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface CurriculumVitaeRepository extends JpaRepository<CurriculumVitae, Long> {
    @Query("SELECT c FROM CurriculumVitae c WHERE c.person.email = :email")
    Optional<CurriculumVitae> findByPersonEmail(@Param("email") String email);
}
