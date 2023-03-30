package fr.ulco.springshop.configurations;

import fr.ulco.springshop.model.conveter.CategoryConverter;
import fr.ulco.springshop.model.conveter.OrderConverter;
import fr.ulco.springshop.model.conveter.ProductConverter;
import fr.ulco.springshop.model.conveter.UserConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConverterConfig {

    @Bean
    public UserConverter userConverter() {
        return UserConverter.create();
    }

    @Bean
    public ProductConverter productConverter(final CategoryConverter categoryConverter) {
        return ProductConverter.create(categoryConverter);
    }

    @Bean
    public OrderConverter orderConverter(final ProductConverter productConverter, final UserConverter userConverter) {
        return OrderConverter.create(productConverter, userConverter);
    }

    @Bean
    public CategoryConverter categoryConverter() {
        return CategoryConverter.create();
    }


}
