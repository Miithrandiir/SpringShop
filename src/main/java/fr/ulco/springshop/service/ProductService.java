package fr.ulco.springshop.service;

import fr.ulco.springshop.model.bo.ProductBO;
import fr.ulco.springshop.model.conveter.ProductConverter;
import fr.ulco.springshop.model.dao.ProductRepository;
import fr.ulco.springshop.service.core.ProductServiceInterface;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ProductService implements ProductServiceInterface {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    public static ProductService create(final ProductRepository repository, final ProductConverter productConverter) {
        return new ProductService(repository, productConverter);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Collection<ProductBO> findAll() {
        return this.productRepository.findAll().stream().map(productConverter::convertToBO).collect(Collectors.toList());
    }

    /**
     * @inheritDoc
     */
    @Override
    public Optional<ProductBO> findById(int id) {
        return this.productRepository.findById(id).map(productConverter::convertToBO);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Collection<ProductBO> findByCategory(String slugCategory) {
        return this.
                productRepository.
                findProductEntitiesByCategories_Slug(slugCategory).stream().map(productConverter::convertToBO).collect(Collectors.toList());
    }

    /**
     * @inheritDoc
     */
    @Override
    public Collection<ProductBO> findByHighlighted() {
        return this.productRepository.findByHighlightedTrue().stream().map(productConverter::convertToBO).collect(Collectors.toList());
    }

    /**
     * @inheritDoc
     */
    @Override
    public ProductBO save(ProductBO productBO) {
        return productConverter.convertToBO(this.productRepository.save(productConverter.convertToEntity(productBO)));
    }
}
