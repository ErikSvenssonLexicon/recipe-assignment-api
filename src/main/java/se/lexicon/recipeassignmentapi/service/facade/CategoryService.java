package se.lexicon.recipeassignmentapi.service.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se.lexicon.recipeassignmentapi.model.dto.CategoryDto;
import se.lexicon.recipeassignmentapi.service.GenericCrudRepository;

public interface CategoryService extends GenericCrudRepository<CategoryDto, String, CategoryDto> {
    CategoryDto findByCategory(String category);
    Page<CategoryDto> findAll(Pageable pageable);
}
