package com.zidani.gestioncv;

import com.zidani.gestioncv.authenticationManagment.AuthenticationService;
import com.zidani.gestioncv.authenticationManagment.RegisterRequest;
import com.zidani.gestioncv.curriculumVitaeManagment.CurriculumVitaeService;
import com.zidani.gestioncv.experienceManagment.ExperienceRequest;
import com.zidani.gestioncv.experienceManagment.ExperienceService;
import com.zidani.gestioncv.personManagment.Person;
import com.zidani.gestioncv.personManagment.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;


import java.time.LocalDate;

import static com.zidani.gestioncv.personManagment.Role.*;

@SpringBootApplication
public class GestionCvApplication  {

    public static void main(String[] args) {
        SpringApplication.run(GestionCvApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService service,
            CurriculumVitaeService curriculumVitaeService,
            ExperienceService experienceService
    ){
        return args -> {

            /*============ EXPERIENCES ===================*/
            ExperienceRequest experience1 = ExperienceRequest.builder()
                    .year(2020)
                    .nature("Work")
                    .title("Software Engineer")
                    .description("Developed and maintained software applications.")
                    .website("example.com")
                    .build();

            ExperienceRequest experience2 = ExperienceRequest.builder()
                    .year(2018)
                    .nature("Internship")
                    .title("IT Intern")
                    .description("Assisted with IT support and troubleshooting.")
                    .website("internship.com")
                    .build();

            ExperienceRequest experience3 = ExperienceRequest.builder()
                    .year(2019)
                    .nature("Project")
                    .title("Project Manager")
                    .description("Managed project timelines and resources.")
                    .website("projectmanager.com")
                    .build();

            ExperienceRequest experience4 = ExperienceRequest.builder()
                    .year(2017)
                    .nature("Freelance")
                    .title("Web Developer")
                    .description("Created websites for various clients.")
                    .website("webdeveloper.com")
                    .build();

            /*============ PERSONS ===================*/
            var user1 = RegisterRequest.builder()
                    .firstname("John")
                    .lastname("Doe")
                    .email("john.doe@mail.com")
                    .birthDay(LocalDate.of(1990, 5, 15))
                    .webSite("johndoe.com")
                    .password("password123")
                    .role(USER)
                    .build();
            service.register(user1);
            var id1  =curriculumVitaeService.createCV(user1.getEmail());
            experienceService.addExperience(id1, experience1);
            experienceService.addExperience(id1, experience2);
            experienceService.addExperience(id1, experience3);
            experienceService.addExperience(id1, experience4);


            var user2 = RegisterRequest.builder()
                    .firstname("Alice")
                    .lastname("Johnson")
                    .email("alice.johnson@mail.com")
                    .birthDay(LocalDate.of(1985, 11, 28))
                    .webSite("alicejohnson.com")
                    .password("securepass")
                    .role(USER)
                    .build();
            service.register(user2);
            var id2  =curriculumVitaeService.createCV(user2.getEmail());
            experienceService.addExperience(id2, experience1);
            experienceService.addExperience(id2, experience2);
            experienceService.addExperience(id2, experience3);
            experienceService.addExperience(id2, experience4);

            var user3 = RegisterRequest.builder()
                    .firstname("Bob")
                    .lastname("Smith")
                    .email("bob.smith@mail.com")
                    .birthDay(LocalDate.of(1993, 4, 8))
                    .webSite("bobsmith.com")
                    .password("bobspassword")
                    .role(USER)
                    .build();
            service.register(user3);
            var id3  =curriculumVitaeService.createCV(user3.getEmail());
            experienceService.addExperience(id3, experience1);
            experienceService.addExperience(id3, experience2);
            experienceService.addExperience(id3, experience3);
            experienceService.addExperience(id3, experience4);

            var user4 = RegisterRequest.builder()
                    .firstname("Emma")
                    .lastname("Williams")
                    .email("emma.williams@mail.com")
                    .birthDay(LocalDate.of(1988, 9, 21))
                    .webSite("emmawilliams.com")
                    .password("password123")
                    .role(USER)
                    .build();
            service.register(user4);
            var id4  =curriculumVitaeService.createCV(user4.getEmail());
            experienceService.addExperience(id4, experience1);
            experienceService.addExperience(id4, experience2);
            experienceService.addExperience(id4, experience3);
            experienceService.addExperience(id4, experience4);

            var user5 = RegisterRequest.builder()
                    .firstname("Emma")
                    .lastname("Jones")
                    .email("emma.jones@mail.com")
                    .birthDay(LocalDate.of(1985, 9, 20))
                    .webSite("emmajones.com")
                    .password("securepassword")
                    .role(USER)
                    .build();
            service.register(user5);
            var id5 = curriculumVitaeService.createCV(user5.getEmail());
            experienceService.addExperience(id5, experience1);
            experienceService.addExperience(id5, experience2);
            experienceService.addExperience(id5, experience3);
            experienceService.addExperience(id5, experience4);

            var user6 = RegisterRequest.builder()
                    .firstname("David")
                    .lastname("Brown")
                    .email("david.brown@mail.com")
                    .birthDay(LocalDate.of(1988, 3, 10))
                    .webSite("davidbrown.com")
                    .password("davidpass")
                    .role(USER)
                    .build();
            service.register(user6);
            var id6 = curriculumVitaeService.createCV(user6.getEmail());
            experienceService.addExperience(id6, experience1);
            experienceService.addExperience(id6, experience2);
            experienceService.addExperience(id6, experience3);
            experienceService.addExperience(id6, experience4);

            var user7 = RegisterRequest.builder()
                    .firstname("Sophie")
                    .lastname("Miller")
                    .email("sophie.miller@mail.com")
                    .birthDay(LocalDate.of(1992, 7, 8))
                    .webSite("sophiemiller.com")
                    .password("sophiepass")
                    .role(USER)
                    .build();
            service.register(user7);
            var id7 = curriculumVitaeService.createCV(user7.getEmail());
            experienceService.addExperience(id7, experience1);
            experienceService.addExperience(id7, experience2);
            experienceService.addExperience(id7, experience3);
            experienceService.addExperience(id7, experience4);

            var fahed = RegisterRequest.builder()
                    .firstname("fahed")
                    .lastname("zidani")
                    .email("fahed@mail.com")
                    .birthDay(LocalDate.of(1999,8,3))
                    .webSite("fahed.com")
                    .password("fahed")
                    .role(ADMIN)
                    .build();
            service.register(fahed);
            var id8  =curriculumVitaeService.createCV(fahed.getEmail());
            experienceService.addExperience(id8, experience1);
            experienceService.addExperience(id8, experience2);
            experienceService.addExperience(id8, experience3);
            experienceService.addExperience(id8, experience4);
            /*============ ADMIN / MANAGEMENT ===================*/
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
