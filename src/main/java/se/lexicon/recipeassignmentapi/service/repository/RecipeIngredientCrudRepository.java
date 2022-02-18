package se.lexicon.recipeassignmentapi.service.repository;

import se.lexicon.recipeassignmentapi.model.dto.RecipeIngredientDto;
import se.lexicon.recipeassignmentapi.model.entity.RecipeIngredient;

public interface RecipeIngredientCrudRepository extends GenericCrudRepository<RecipeIngredient, String, RecipeIngredientDto>{
}
