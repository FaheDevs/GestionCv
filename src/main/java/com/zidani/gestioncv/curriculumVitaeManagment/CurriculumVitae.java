package com.zidani.gestioncv.curriculumVitaeManagment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zidani.gestioncv.experienceManagment.Experience;
import com.zidani.gestioncv.personManagment.Person;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
@Hidden
public class CurriculumVitae {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne(mappedBy = "curriculumVitae")
    @JsonIgnore
    private Person person;
    @OneToMany(mappedBy = "curriculumVitae", fetch = FetchType.LAZY)
    private List<Experience> experiences;
    @Override
    public String toString() {
        return "CurriculumVitae{" +
                "id=" + id +
                ", person=" + (person != null ? person.getEmail() : null) +
                ", experiences=" + (experiences != null ? experiences.stream().map(Experience::getTitle).toList() : null) +
                '}';
    }
}
