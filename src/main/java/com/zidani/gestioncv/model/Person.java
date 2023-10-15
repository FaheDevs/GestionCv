package com.zidani.gestioncv.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @Column(unique = true)
    @NotBlank(message = "email cannot be blank")
    @Email(message = "Invalid email format. The email should contain '@'.")
    private String email;
    private String webSite;
    private Date birthDay;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "curriculumVitae_id", referencedColumnName = "id")
    private CurriculumVitae curriculumVitae;
}
