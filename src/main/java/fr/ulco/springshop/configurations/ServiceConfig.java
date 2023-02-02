package fr.ulco.springshop.configurations;

import fr.ulco.springshop.model.conveter.CategoryConverter;
import fr.ulco.springshop.model.conveter.UserConverter;
import fr.ulco.springshop.model.dao.CategoryRepository;
import fr.ulco.springshop.model.dao.UserRepository;
import fr.ulco.springshop.service.CategoryService;
import fr.ulco.springshop.service.HashPasswordService;
import fr.ulco.springshop.service.SlugService;
import fr.ulco.springshop.service.UserService;
import fr.ulco.springshop.service.core.CategoryServiceInterface;
import fr.ulco.springshop.service.core.HashPasswordServiceInterface;
import fr.ulco.springshop.service.core.SluggerServiceInterface;
import fr.ulco.springshop.service.core.UserServiceInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    public UserServiceInterface userService(final UserRepository userRepository, final UserConverter userConverter) {
        return UserService.create(userRepository, userConverter);
    }
}
