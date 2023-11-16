package com.zidani.gestioncv;

import com.zidani.gestioncv.personManagment.Person;
import com.zidani.gestioncv.personManagment.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
public class GestionCvApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionCvApplication.class, args);
    }
    @Bean
    public CommandLineRunner loadData(PersonRepository personRepository){
        return args -> {
           var person1 = Person.builder().firstName("person1")
                   .lastName("person2")
                   .webSite("personWebsite.com")
                   .birthDay(LocalDate.of(2000,10,10))
                   .email("person@gmail.com")
                   .password("pass")
                   .build();

           personRepository.save(person1);

        };
    }

}
