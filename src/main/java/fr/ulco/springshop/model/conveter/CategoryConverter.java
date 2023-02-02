package fr.ulco.springshop.model.conveter;

import fr.ulco.springshop.model.DTOEntityConverter;
import fr.ulco.springshop.model.dto.CategoryDTO;
import fr.ulco.springshop.model.entities.CategoryEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryConverter extends DTOEntityConverter<CategoryEntity, CategoryDTO> {

    private final ProductConverter productConverter;

    public static CategoryConverter create(final ProductConverter productConverter) {
        return new CategoryConverter(productConverter);
    }

    @Override
    public CategoryEntity convertToEntity(CategoryDTO categoryDTO) {
        CategoryEntity c = new CategoryEntity();
        c.setId(categoryDTO.getId());
        c.setName(categoryDTO.getName());
        c.setSlug(categoryDTO.getSlug());
        c.setProducts(categoryDTO.getProducts()
                .stream()
                .map(productConverter::convertToEntity)
                .collect(Collectors.toList())
        );


        return c;
    }

    @Override
    public CategoryDTO convertToDTO(CategoryEntity categoryEntity) {
        CategoryDTO c = new CategoryDTO();
        c.setId(categoryEntity.getId());
        c.setName(categoryEntity.getName());
        c.setSlug(categoryEntity.getSlug());
        c.setProducts(categoryEntity
                .getProducts()
                .stream()
                .map(productConverter::convertToDTO)
                .collect(Collectors.toList())
        );
        return c;
    }
}
