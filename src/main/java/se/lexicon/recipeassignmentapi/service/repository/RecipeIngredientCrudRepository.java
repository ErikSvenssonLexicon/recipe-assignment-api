package se.lexicon.recipeassignmentapi.service.repository;

import se.lexicon.recipeassignmentapi.model.dto.RecipeIngredientDto;
import se.lexicon.recipeassignmentapi.model.entity.Recipe;
import se.lexicon.recipeassignmentapi.model.entity.RecipeIngredient;
import se.lexicon.recipeassignmentapi.service.GenericCrudRepository;

public interface RecipeIngredientCrudRepository extends GenericCrudRepository<RecipeIngredient, String, RecipeIngredientDto> {
    RecipeIngredient create(RecipeIngredientDto form, Recipe recipe);
}
