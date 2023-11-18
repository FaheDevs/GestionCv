package com.zidani.gestioncv.curriculumVitaeManagment;

import com.zidani.gestioncv.curriculumVitaeManagment.CurriculumVitae;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Hidden
@Transactional
public interface CurriculumVitaeRepository extends JpaRepository<CurriculumVitae, Long> {
    @Query("SELECT c FROM CurriculumVitae c WHERE c.person.email = :email")
    Optional<CurriculumVitae> findByPersonEmail(@Param("email") String email);

    @NonNull
    Optional<CurriculumVitae> findById(Long id);
    void deleteById(Long id);
}
