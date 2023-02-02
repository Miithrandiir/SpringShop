package fr.ulco.springshop.service;

import fr.ulco.springshop.model.conveter.CategoryConverter;
import fr.ulco.springshop.model.dao.CategoryRepository;
import fr.ulco.springshop.model.bo.CategoryBO;
import fr.ulco.springshop.model.entities.CategoryEntity;
import fr.ulco.springshop.service.core.CategoryServiceInterface;
import fr.ulco.springshop.service.core.SluggerServiceInterface;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryService implements CategoryServiceInterface {

    private final CategoryRepository repository;
    private final CategoryConverter categoryConverter;
    private final SluggerServiceInterface sluggerService;

    public static CategoryService create(final CategoryRepository repository, final CategoryConverter categoryConverter, final SluggerServiceInterface sluggerService) {
        return new CategoryService(repository, categoryConverter, sluggerService);
    }

    @Override
    public Collection<CategoryBO> findAll() {
        return repository.findAll()
                .stream()
                .map(categoryConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryBO create(String name) {
        CategoryEntity ce = new CategoryEntity();
        ce.setName(name);
        ce.setSlug(sluggerService.toSlug(name));

        repository.saveAndFlush(ce);
        return categoryConverter.convertToDTO(ce);
    }

    @Override
    public CategoryBO update(CategoryBO categoryDTO) {
        CategoryEntity ce = categoryConverter.convertToEntity(categoryDTO);
        repository.saveAndFlush(ce);
        return categoryDTO;
    }
}
