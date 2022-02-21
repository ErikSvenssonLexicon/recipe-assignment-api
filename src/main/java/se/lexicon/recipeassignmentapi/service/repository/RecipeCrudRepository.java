package se.lexicon.recipeassignmentapi.service.repository;

import se.lexicon.recipeassignmentapi.model.dto.RecipeDto;
import se.lexicon.recipeassignmentapi.model.entity.Recipe;
import se.lexicon.recipeassignmentapi.service.GenericCrudRepository;

public interface RecipeCrudRepository extends GenericCrudRepository<Recipe, String, RecipeDto> {
}
