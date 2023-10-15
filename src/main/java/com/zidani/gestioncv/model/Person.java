package com.zidani.gestioncv.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "Details about a person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "The unique ID of the person")
    private Long id;
    @ApiModelProperty(value = "First name of the person")
    private String firstName;
    @ApiModelProperty(value = "Last name of the person")
    private String lastName;
    @ApiModelProperty(value = "Email address of the person")
    private String email;
    @ApiModelProperty(value = "Website URL of the person")
    private String webSite;
    @ApiModelProperty(value = "Date of birth of the person")
    private Date birthDay;
    @ApiModelProperty(value = "Password of the person")
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    private CurriculumVitae curriculumVitae;
}
