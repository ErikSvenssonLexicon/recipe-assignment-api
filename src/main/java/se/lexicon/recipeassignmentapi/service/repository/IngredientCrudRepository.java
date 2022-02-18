package se.lexicon.recipeassignmentapi.service.repository;

import se.lexicon.recipeassignmentapi.model.dto.IngredientDto;
import se.lexicon.recipeassignmentapi.model.entity.Ingredient;

public interface IngredientCrudRepository extends GenericCrudRepository<Ingredient, String, IngredientDto>{
}
