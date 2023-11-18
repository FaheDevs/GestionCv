package com.zidani.gestioncv.personManagment;


import com.zidani.gestioncv.curriculumVitaeManagment.CurriculumVitae;
import com.zidani.gestioncv.authenticationManagment.tokenManagement.Token;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Hidden
public class Person implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String webSite;
    private LocalDate birthDay;
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "curriculumVitae_id", referencedColumnName = "id")
    private CurriculumVitae curriculumVitae;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "person")
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", webSite='" + webSite + '\'' +
                ", birthDay=" + birthDay +
                ", password='" + password + '\'' +
                  ", curriculumVitae=" + curriculumVitae.getId() +
                ", role=" + role +
                '}';
    }
}
