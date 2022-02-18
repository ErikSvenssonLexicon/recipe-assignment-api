package se.lexicon.recipeassignmentapi.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.recipeassignmentapi.model.entity.Ingredient;

import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, String> {
    @Query("SELECT i FROM Ingredient i WHERE UPPER(i.ingredientName) = UPPER(:ingredientName)")
    Optional<Ingredient> findByIngredientName(@Param("ingredientName") String ingredientName);
    @Query("SELECT i FROM Ingredient i WHERE UPPER(i.ingredientName) LIKE UPPER(CONCAT(:search,'%'))")
    Slice<Ingredient> searchByIngredientName(@Param("search") String search, Pageable pageable);
}