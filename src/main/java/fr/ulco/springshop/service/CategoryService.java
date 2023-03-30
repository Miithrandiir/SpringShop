package fr.ulco.springshop.service;

import fr.ulco.springshop.model.bo.CategoryBO;
import fr.ulco.springshop.model.conveter.CategoryConverter;
import fr.ulco.springshop.model.dao.CategoryRepository;
import fr.ulco.springshop.model.entities.CategoryEntity;
import fr.ulco.springshop.service.core.CategoryServiceInterface;
import fr.ulco.springshop.service.core.SluggerServiceInterface;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Optional;
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
                .map(categoryConverter::convertToBO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryBO create(String name) {
        CategoryEntity ce = new CategoryEntity();
        ce.setName(name);
        ce.setSlug(sluggerService.toSlug(name));

        repository.saveAndFlush(ce);
        return categoryConverter.convertToBO(ce);
    }

    @Override
    public CategoryBO update(CategoryBO categoryDTO) {
        CategoryEntity ce = categoryConverter.convertToEntity(categoryDTO);
        repository.saveAndFlush(ce);
        return categoryDTO;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Optional<CategoryBO> findBySlug(String slug) {
        Optional<CategoryEntity> categoryEntity = repository.findBySlug(slug);
        return categoryEntity.map(categoryConverter::convertToBO);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Optional<CategoryBO> save(CategoryBO categoryBO) {
        CategoryEntity categoryEntity = categoryConverter.convertToEntity(categoryBO);
        repository.saveAndFlush(categoryEntity);
        return Optional.of(categoryConverter.convertToBO(categoryEntity));
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean deleteBySlug(String slug) {
        Optional<CategoryEntity> categoryEntity = repository.findBySlug(slug);
        if (categoryEntity.isPresent()) {
            repository.delete(categoryEntity.get());
            return true;
        }
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Optional<CategoryBO> updateBySlug(String slug, CategoryBO categoryBO) {
        Optional<CategoryEntity> categoryEntity = repository.findBySlug(slug);
        if (categoryEntity.isPresent()) {
            CategoryEntity ce = categoryEntity.get();
            ce.setName(categoryBO.getName());
            ce.setSlug(sluggerService.toSlug(categoryBO.getName()));
            repository.saveAndFlush(ce);
            return Optional.of(categoryConverter.convertToBO(ce));
        }
        return Optional.empty();
    }
}
