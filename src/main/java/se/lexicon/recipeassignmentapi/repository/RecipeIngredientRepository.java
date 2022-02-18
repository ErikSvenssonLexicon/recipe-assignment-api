package se.lexicon.recipeassignmentapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.recipeassignmentapi.model.entity.RecipeIngredient;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, String> {
}