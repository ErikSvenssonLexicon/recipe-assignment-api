package se.lexicon.recipeassignmentapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.recipeassignmentapi.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, String> {
}