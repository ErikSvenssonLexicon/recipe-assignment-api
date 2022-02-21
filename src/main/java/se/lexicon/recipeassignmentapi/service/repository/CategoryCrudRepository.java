package se.lexicon.recipeassignmentapi.service.repository;

import se.lexicon.recipeassignmentapi.model.dto.CategoryDto;
import se.lexicon.recipeassignmentapi.model.entity.Category;
import se.lexicon.recipeassignmentapi.service.GenericCrudRepository;

public interface CategoryCrudRepository extends GenericCrudRepository<Category, String, CategoryDto> {
}
