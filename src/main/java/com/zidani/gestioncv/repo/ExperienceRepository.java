package com.zidani.gestioncv.repo;

import com.zidani.gestioncv.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ExperienceRepository extends JpaRepository<Experience, Long> {
}
