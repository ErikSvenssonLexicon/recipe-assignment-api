package se.lexicon.recipeassignmentapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.recipeassignmentapi.model.entity.RecipeIngredient;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, String> {
    @Query("SELECT COUNT(ri.id) FROM RecipeIngredient ri WHERE ri.ingredient.id = :ingredientId")
    long countUsagesByIngredientId(@Param("ingredientId") String ingredientId);
}