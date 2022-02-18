package se.lexicon.recipeassignmentapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.recipeassignmentapi.model.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, String> {
}