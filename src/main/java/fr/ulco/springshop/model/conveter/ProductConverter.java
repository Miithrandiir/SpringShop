package fr.ulco.springshop.model.conveter;

import fr.ulco.springshop.model.AbstractBOEntityConverter;
import fr.ulco.springshop.model.bo.ProductBO;
import fr.ulco.springshop.model.entities.ProductEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductConverter extends AbstractBOEntityConverter<ProductEntity, ProductBO> {

    private final CategoryConverter categoryConverter;

    public static ProductConverter create(final CategoryConverter categoryConverter) {
        return new ProductConverter(categoryConverter);
    }

    @Override
    public ProductEntity convertToEntity(ProductBO productBO) {
        ProductEntity p = new ProductEntity();
        p.setId(productBO.getId());
        p.setName(productBO.getName());
        p.setCreatedAt(productBO.getCreatedAt());
        p.setUpdatedAt(productBO.getUpdatedAt());
        p.setPrice(productBO.getPrice());
        p.setQuantity(productBO.getQuantity());
        p.setThumbnail(productBO.getThumbnail());
        p.setCategories(productBO.getCategories().stream().map(categoryConverter::convertToEntity).collect(Collectors.toSet()));
        p.setHighlighted(productBO.isHighlighted());

        return p;
    }

    @Override
    public ProductBO convertToBO(ProductEntity productEntity) {
        ProductBO p = new ProductBO();
        p.setId(productEntity.getId());
        p.setName(productEntity.getName());
        p.setCreatedAt(productEntity.getCreatedAt());
        p.setUpdatedAt(productEntity.getUpdatedAt());
        p.setPrice(productEntity.getPrice());
        p.setQuantity(productEntity.getQuantity());
        p.setThumbnail(productEntity.getThumbnail());
        p.setCategories(productEntity.getCategories().stream().map(categoryConverter::convertToBO).collect(Collectors.toList()));
        p.setHighlighted(productEntity.isHighlighted());

        return p;
    }
}
