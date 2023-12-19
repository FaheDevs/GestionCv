package com.zidani.gestioncv;

import com.github.javafaker.Faker;
import com.zidani.gestioncv.authenticationManagment.AuthenticationService;
import com.zidani.gestioncv.authenticationManagment.RegisterRequest;
import com.zidani.gestioncv.curriculumVitaeManagment.CurriculumVitaeService;
import com.zidani.gestioncv.experienceManagment.ExperienceRequest;
import com.zidani.gestioncv.experienceManagment.ExperienceService;
import com.zidani.gestioncv.personManagment.Person;
import com.zidani.gestioncv.personManagment.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;


import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static com.zidani.gestioncv.personManagment.Role.*;

@SpringBootApplication
@Slf4j
public class GestionCvApplication  {

    public static void main(String[] args) {
        SpringApplication.run(GestionCvApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService authenticationService,
            CurriculumVitaeService curriculumVitaeService,
            ExperienceService experienceService
    ) {
        return args -> {

            Faker faker = new Faker();
<<<<<<< Updated upstream
            int size = 50;
=======
            int size = 100000;
>>>>>>> Stashed changes

            for (int i = 0; i <size; i++) {
                String firstName = faker.name().firstName();
                String lastName = faker.name().lastName();
<<<<<<< Updated upstream
                String uniqueEmailPart = firstName.toLowerCase() + "." + lastName.toLowerCase() + i;
                String email = uniqueEmailPart + "@mail.com";
=======
                String email = firstName.toLowerCase() + "."+ String.valueOf(i)+ lastName.toLowerCase() + "@mail.com";
>>>>>>> Stashed changes
                String website = firstName.toLowerCase() + "." + lastName.toLowerCase() + ".com";
                String password = faker.internet().password();

                // Generate a random birth date
                Date dateOfBirth = faker.date().birthday();
                LocalDate birthDay = dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                RegisterRequest user = RegisterRequest.builder()
                        .firstname(firstName)
                        .lastname(lastName)
                        .email(email)
                        .birthDay(birthDay)
                        .webSite(website)
                        .password(password)
                        .role(USER)
                        .build();
                // Register the user
                authenticationService.register(user);

                // Create a CV for the user
                Long cvId = curriculumVitaeService.createCV(user.getEmail());

                // Add 4 random experiences to the CV
                for (int j = 0; j < 2; j++) {
                    ExperienceRequest experience = createRandomExperience(faker);
                    experienceService.addExperience(cvId, experience);
                }
            }

            log.warn("{} personnes crées ! ",size);


            /*============ USER / ROLE ===================*/
            var user = RegisterRequest.builder()
                    .firstname("USER")
                    .lastname("USER")
                    .email("user@mail.com")
                    .password("password")
                    .role(USER)
                    .build();
            System.out.println("user token: " + authenticationService.register(user).getAccessToken());



            /*============ ADMIN / MANAGEMENT ===================*/
            var admin = RegisterRequest.builder()
                    .firstname("Admin")
                    .lastname("Admin")
                    .email("admin@mail.com")
                    .password("password")
                    .role(ADMIN)
                    .build();
            System.out.println("Admin token: " + authenticationService.register(admin).getAccessToken());

            var manager = RegisterRequest.builder()
                    .firstname("Admin")
                    .lastname("Admin")
                    .email("manager@mail.com")
                    .password("password")
                    .role(MANAGER)
                    .build();
            System.out.println("Manager token: " + authenticationService.register(manager).getAccessToken());


        };
    }

    private static ExperienceRequest createRandomExperience(Faker faker) {
        Random random = new Random();
        List<String> experiences = List.of("Work", "Internship", "Project", "Freelance");

        int year = faker.random().nextInt(2010, 2023);
        String nature = experiences.get(random.nextInt(experiences.size()));
        String title = faker.job().title();

        // Manually construct a website URL
        String websiteUrl = "https://" + faker.internet().domainName();

        return ExperienceRequest.builder()
                .year(year)
                .nature(nature)
                .title(title)
                .description(nature)
                .website(websiteUrl)
                .build();
    }
}

