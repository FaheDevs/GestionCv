package com.zidani.gestioncv.experienceManagment;

import com.zidani.gestioncv.experienceManagment.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    List<Experience> findByTitle(String title);

    List<Experience> findByYear(int year);
}
