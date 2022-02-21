package se.lexicon.recipeassignmentapi.service.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.recipeassignmentapi.model.dto.CategoryDto;
import se.lexicon.recipeassignmentapi.repository.CategoryRepository;
import se.lexicon.recipeassignmentapi.service.repository.CategoryCrudRepository;

@Service
@Transactional
public class CategoryServiceImpl extends DtoAssembler implements CategoryService{

    private final CategoryCrudRepository crudRepository;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryCrudRepository crudRepository, CategoryRepository categoryRepository) {
        this.crudRepository = crudRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto create(CategoryDto form) {
        return toCategoryDto(crudRepository.create(form));
    }

    @Override
    public CategoryDto findById(String id) {
        return toCategoryDto(crudRepository.findById(id));
    }

    @Override
    public CategoryDto update(String id, CategoryDto form) {
        return toCategoryDto(crudRepository.update(id, form));
    }

    @Override
    public boolean delete(String id) {
        return crudRepository.delete(id);
    }

    @Override
    public CategoryDto findByCategory(String category) {
        return categoryRepository.findByCategory(category)
                .map(this::toCategoryDto)
                .orElseThrow(getException("Couldn't find category with categoryName: " + category));
    }

    @Override
    public Page<CategoryDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable)
                .map(this::toCategoryDto);
    }
}
