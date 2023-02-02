package fr.ulco.springshop.configurations;

import fr.ulco.springshop.model.conveter.UserConverter;
import fr.ulco.springshop.model.dao.UserRepository;
import fr.ulco.springshop.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataBaseConfig {
    @Bean
    public UserService userService(final UserRepository userRepository, final UserConverter userConverter){
        return UserService.create(userRepository, userConverter);
    }
}
