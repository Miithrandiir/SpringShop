package fr.ulco.springshop.configurations;

import fr.ulco.springshop.model.conveter.CategoryConverter;
import fr.ulco.springshop.model.conveter.ProductConverter;
import fr.ulco.springshop.model.conveter.UserConverter;
import fr.ulco.springshop.model.dao.CategoryRepository;
import fr.ulco.springshop.model.dao.ProductRepository;
import fr.ulco.springshop.model.dao.UserRepository;
import fr.ulco.springshop.properties.StorageProperty;
import fr.ulco.springshop.service.*;
import fr.ulco.springshop.service.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    public UserServiceInterface userService(final UserRepository userRepository, final UserConverter userConverter) {
        return UserService.create(userRepository, userConverter);
    }

    @Bean
    public HashPasswordServiceInterface hashPasswordService() {
        return HashPasswordService.create();
    }

    @Bean
    public SluggerServiceInterface sluggerService() {
        return SlugService.create();
    }

    @Bean
    public CategoryServiceInterface categoryService(final CategoryRepository categoryRepository, final CategoryConverter categoryConverter, final SluggerServiceInterface sluggerService) {
        return CategoryService.create(categoryRepository, categoryConverter, sluggerService);
    }

    @Bean
    public ProductServiceInterface productService(final ProductRepository productRepository, final ProductConverter productConverter) {
        return ProductService.create(productRepository, productConverter);
    }

    @Bean
    public StorageServiceInterface storageService(final StorageProperty storageProperty) {
        return FileSystemStorageService.create(storageProperty);
    }
}
