package com.zidani.gestioncv;

import com.zidani.gestioncv.authenticationManagment.AuthenticationService;
import com.zidani.gestioncv.authenticationManagment.RegisterRequest;
import com.zidani.gestioncv.personManagment.Person;
import com.zidani.gestioncv.personManagment.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;


import java.time.LocalDate;

import static com.zidani.gestioncv.personManagment.Role.ADMIN;
import static com.zidani.gestioncv.personManagment.Role.MANAGER;

@SpringBootApplication
public class GestionCvApplication  {

    public static void main(String[] args) {
        SpringApplication.run(GestionCvApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService service
    ){
        return args -> {
            var fahed = RegisterRequest.builder()
                    .firstname("fahed")
                    .lastname("zidani")
                    .email("fahed@mail.com")
                    .password("fahed")
                    .role(ADMIN)
                    .build();
            service.register(fahed);
            var admin = RegisterRequest.builder()
                    .firstname("Admin")
                    .lastname("Admin")
                    .email("admin@mail.com")
                    .password("password")
                    .role(ADMIN)
                    .build();
            System.out.println("Admin token: " + service.register(admin).getAccessToken());

            var manager = RegisterRequest.builder()
                    .firstname("Admin")
                    .lastname("Admin")
                    .email("manager@mail.com")
                    .password("password")
                    .role(MANAGER)
                    .build();
            System.out.println("Manager token: " + service.register(manager).getAccessToken());

        };
    }

}
