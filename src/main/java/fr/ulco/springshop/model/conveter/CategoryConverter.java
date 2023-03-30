package fr.ulco.springshop.model.conveter;

import fr.ulco.springshop.model.AbstractBOEntityConverter;
import fr.ulco.springshop.model.bo.CategoryBO;
import fr.ulco.springshop.model.entities.CategoryEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryConverter extends AbstractBOEntityConverter<CategoryEntity, CategoryBO> {

    public static CategoryConverter create() {
        return new CategoryConverter(   );
    }

    @Override
    public CategoryEntity convertToEntity(CategoryBO categoryDTO) {
        CategoryEntity c = new CategoryEntity();
        c.setId(categoryDTO.getId());
        c.setName(categoryDTO.getName());
        c.setSlug(categoryDTO.getSlug());


        return c;
    }

    @Override
    public CategoryBO convertToBO(CategoryEntity categoryEntity) {
        CategoryBO c = new CategoryBO();
        c.setId(categoryEntity.getId());
        c.setName(categoryEntity.getName());
        c.setSlug(categoryEntity.getSlug());

        return c;
    }
}
