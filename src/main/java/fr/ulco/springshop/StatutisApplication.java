package fr.ulco.springshop;

import fr.ulco.springshop.service.core.CategoryServiceInterface;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StatutisApplication {

    public static void main(String[] args) {
        SpringApplication.run(StatutisApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(final CategoryServiceInterface category) {
        return (String[] args) -> {



        };
    }

}
