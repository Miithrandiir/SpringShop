package fr.ulco.springshop;

import fr.ulco.springshop.model.dto.UserDTO;
import fr.ulco.springshop.service.UserService;
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
    public CommandLineRunner run(final UserService userService) {
        return (String[] args) -> {
            Optional<UserDTO> userDTO = userService.findByEmail("ludwig@silvain.eu");

            System.out.println(userDTO.get().getPassword());

            userDTO.get().setPassword("salut");

            userService.updateUser(userDTO.get());

        };
    }

}
