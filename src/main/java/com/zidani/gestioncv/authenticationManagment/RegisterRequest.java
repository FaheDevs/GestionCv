package com.zidani.gestioncv.authenticationManagment;

import com.zidani.gestioncv.personManagment.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String firstname;
  private String lastname;
  private String email;
  private String webSite;
  private LocalDate birthDay;
  private String password;
  private Role role;
}
