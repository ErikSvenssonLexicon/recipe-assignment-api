package se.lexicon.recipeassignmentapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.recipeassignmentapi.model.entity.Recipe;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, String> {
    @Query(value = "SELECT r FROM Recipe r WHERE UPPER(r.recipeTitle) = UPPER(:title)")
    List<Recipe> findByRecipeTitle(@Param("title") String title);

    Page<Recipe> findByHiddenFalseAndPublishedTrue(Pageable pageable);

    @Query(value = "SELECT r FROM Recipe r JOIN FETCH r.recipeIngredients AS ri WHERE UPPER(ri.ingredient.ingredientName) LIKE UPPER(CONCAT('%',:ingredientName,'%')) AND r.hidden = false AND r.published = true"
    , countQuery = "SELECT r FROM Recipe r  JOIN FETCH r.recipeIngredients AS ri  WHERE UPPER(ri.ingredient.ingredientName) LIKE UPPER(CONCAT('%',:ingredientName,'%')) AND r.hidden = false AND r.published = true")
    Page<Recipe> findByIngredientName(@Param("ingredientName") String ingredientName, Pageable pageable);

    @Query(value = "SELECT r FROM Recipe r JOIN FETCH r.categories AS c WHERE UPPER(c.categoryName) = UPPER(:category) AND r.hidden = false AND r.published = true",
            countQuery = "SELECT r FROM Recipe r JOIN FETCH r.categories AS c WHERE UPPER(c.categoryName) = UPPER(:category) AND r.hidden = false AND r.published = true")
    Page<Recipe> searchByCategoryName(@Param("category") String categoryName, Pageable pageable);

}