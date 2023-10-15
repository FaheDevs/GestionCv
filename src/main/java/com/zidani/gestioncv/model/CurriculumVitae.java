package com.zidani.gestioncv.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.Entity;
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
@ApiModel(description = "Details about a curriculum vitae")
public class CurriculumVitae {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "The unique ID of the curriculum vitae")
    private Long id;

    @OneToOne(mappedBy = "curriculumVitae")
    @ApiModelProperty(value = "Person associated with this curriculum vitae")
    private Person person;

    @OneToMany(mappedBy = "curriculumVitae")
    @ApiModelProperty(value = "List of experiences associated with this curriculum vitae")
    private List<Experience> experiences;
}
