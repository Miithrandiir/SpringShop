package fr.ulco.springshop;

import fr.ulco.springshop.properties.StorageProperty;
import fr.ulco.springshop.service.core.StorageServiceInterface;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableConfigurationProperties(StorageProperty.class)
public class SpringShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringShopApplication.class, args);
    }

    @Bean
    CommandLineRunner initStorage(StorageServiceInterface storageService) {
        return (args) -> {
            storageService.init();
        };
    }

}
