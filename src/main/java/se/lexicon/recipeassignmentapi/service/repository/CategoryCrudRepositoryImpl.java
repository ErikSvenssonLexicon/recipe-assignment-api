package se.lexicon.recipeassignmentapi.service.repository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.recipeassignmentapi.model.dto.CategoryDto;
import se.lexicon.recipeassignmentapi.model.entity.Category;

@Service
@Transactional
public class CategoryCrudRepositoryImpl implements CategoryCrudRepository{
    @Override
    public Category create(CategoryDto form) {
        return null;
    }

    @Override
    public Category findById(String id) {
        return null;
    }

    @Override
    public Category update(String id, CategoryDto form) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
