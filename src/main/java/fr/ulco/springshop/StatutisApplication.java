package fr.ulco.springshop;

import fr.ulco.springshop.model.dto.UserDTO;
import fr.ulco.springshop.service.HashPasswordService;
import fr.ulco.springshop.service.UserService;
import fr.ulco.springshop.service.core.CategoryServiceInterface;
import fr.ulco.springshop.service.core.HashPasswordServiceInterface;
import fr.ulco.springshop.service.core.UserServiceInterface;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

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
