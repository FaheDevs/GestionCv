package com.zidani.gestioncv.experienceManagment;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
@Hidden
public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    void deleteById(Long id);
}
