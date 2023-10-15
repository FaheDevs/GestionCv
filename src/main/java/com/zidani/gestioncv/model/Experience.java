package com.zidani.gestioncv.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
@ApiModel(description = "Details about an experience")
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "The unique ID of the experience")
    private Long id;

    @ApiModelProperty(value = "Year of the experience")
    private int year;

    @ApiModelProperty(value = "Nature of the experience")
    private String nature;

    @ApiModelProperty(value = "Title of the experience")
    private String title;

    @ApiModelProperty(value = "Description of the experience")
    private String description;

    @ApiModelProperty(value = "Website for the experience")
    private String website;

    @ManyToOne
    private CurriculumVitae curriculumVitae;
}
