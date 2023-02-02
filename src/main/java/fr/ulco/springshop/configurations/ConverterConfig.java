package fr.ulco.springshop.configurations;

import fr.ulco.springshop.model.conveter.UserConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConverterConfig {

    @Bean
    public UserConverter userConverter() {
        return UserConverter.create();
    }

}
