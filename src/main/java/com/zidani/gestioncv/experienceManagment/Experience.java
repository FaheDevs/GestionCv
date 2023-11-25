package com.zidani.gestioncv.experienceManagment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zidani.gestioncv.curriculumVitaeManagment.CurriculumVitae;
import io.swagger.v3.oas.annotations.Hidden;
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
@Hidden
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
    @JsonIgnore
    @ManyToOne
    private CurriculumVitae curriculumVitae;

    @Override
    public String toString() {
        return "Experience{" +
                "id=" + id +
                ", year=" + year +
                ", nature='" + nature + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", website='" + website + '\'' +
                ", curriculumVitae=" + curriculumVitae.getId() +
                '}';
    }
}
