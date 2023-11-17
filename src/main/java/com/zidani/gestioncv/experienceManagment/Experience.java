package com.zidani.gestioncv.experienceManagment;

import com.zidani.gestioncv.curriculumVitaeManagment.CurriculumVitae;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int year;
    @Column(nullable = false)
    private String nature;
    @Column(nullable = false)
    private String title;
    private String description;
    private String website;
    @ManyToOne
    private CurriculumVitae curriculumVitae;
}
