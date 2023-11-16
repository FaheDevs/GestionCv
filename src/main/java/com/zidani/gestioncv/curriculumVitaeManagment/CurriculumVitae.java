package com.zidani.gestioncv.curriculumVitaeManagment;

import com.zidani.gestioncv.experienceManagment.Experience;
import com.zidani.gestioncv.personManagment.Person;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class CurriculumVitae {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne(mappedBy = "curriculumVitae")
    private Person person;
    @OneToMany(mappedBy = "curriculumVitae", fetch = FetchType.EAGER)
    private List<Experience> experiences;
}
