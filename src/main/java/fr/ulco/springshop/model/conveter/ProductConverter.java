package fr.ulco.springshop.model.conveter;

import fr.ulco.springshop.model.DTOEntityConverter;
import fr.ulco.springshop.model.dto.ProductDTO;
import fr.ulco.springshop.model.entities.ProductEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductConverter extends DTOEntityConverter<ProductEntity, ProductDTO> {

    public static ProductConverter create(){
        return new ProductConverter();
    }

    @Override
    public ProductEntity convertToEntity(ProductDTO productDTO) {
        ProductEntity p = new ProductEntity();
        p.setId(productDTO.getId());
        p.setName(productDTO.getName());
        p.setCreatedAt(productDTO.getCreatedAt());
        p.setUpdatedAt(productDTO.getUpdatedAt());
        p.setPrice(productDTO.getPrice());
        p.setQuantity(productDTO.getQuantity());
        p.setThumbnail(productDTO.getThumbnail());

        // TODO Catégories

        return p;
    }

    @Override
    public ProductDTO convertToDTO(ProductEntity productEntity) {
        ProductDTO p = new ProductDTO();
        p.setId(productEntity.getId());
        p.setName(productEntity.getName());
        p.setCreatedAt(productEntity.getCreatedAt());
        p.setUpdatedAt(productEntity.getUpdatedAt());
        p.setPrice(productEntity.getPrice());
        p.setQuantity(productEntity.getQuantity());
        p.setThumbnail(productEntity.getThumbnail());

        // TODO Catégories

        return p;
    }
}
