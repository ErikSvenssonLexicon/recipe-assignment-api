package se.lexicon.recipeassignmentapi.service.repository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.recipeassignmentapi.exception.AppResourceNotFoundException;
import se.lexicon.recipeassignmentapi.model.dto.CategoryDto;
import se.lexicon.recipeassignmentapi.model.entity.Category;
import se.lexicon.recipeassignmentapi.repository.CategoryRepository;

@Service
@Transactional
public class CategoryCrudRepositoryImpl implements CategoryCrudRepository{

    private final CategoryRepository categoryRepository;

    public CategoryCrudRepositoryImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(CategoryDto form) {
        if(form == null) throw new IllegalArgumentException("CategoryDto was null");
        form.setCategoryName(form.getCategoryName().trim());
        return categoryRepository.findByCategory(form.getCategoryName())
                .orElseGet(() -> categoryRepository.save(new Category(null, form.getCategoryName())));
    }

    @Override
    public Category findById(String id) {
        return categoryRepository.findById(id)
                .orElseThrow(getException("Could not find category with id " + id));
    }

    @Override
    public Category update(String id, CategoryDto form) {
        if(id == null) throw new IllegalArgumentException("Id was null");
        if(form == null) throw new IllegalArgumentException("CategoryDto was null");
        if(!id.equals(form.getId())){
            throw new IllegalStateException("Id didn't match CategoryDto.id");
        }
        Category category = findById(id);
        var optional = categoryRepository.findByCategory(form.getCategoryName().trim());
        if(optional.isPresent() && !optional.get().getId().equals(id)){
            throw new IllegalArgumentException("category: " + form.getCategoryName().trim() + " already exists");
        }
        category.setCategoryName(form.getCategoryName().trim());

        return categoryRepository.save(category);
    }

    @Override
    public boolean delete(String id) {
        categoryRepository.deleteById(id);
        return !categoryRepository.existsById(id);
    }
}
