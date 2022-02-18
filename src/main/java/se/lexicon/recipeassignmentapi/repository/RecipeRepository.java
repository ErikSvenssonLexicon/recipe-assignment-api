package se.lexicon.recipeassignmentapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.recipeassignmentapi.model.Recipe;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, String> {
    @Query("SELECT r FROM Recipe r WHERE UPPER(r.recipeTitle) = UPPER(:title)")
    List<Recipe> findByRecipeTitle(@Param("title") String title);

    @Query("SELECT r FROM Recipe r JOIN FETCH r.recipeIngredients AS ri WHERE UPPER(ri.ingredient.ingredientName) LIKE UPPER(CONCAT('%',:ingredientName,'%'))")
    Page<Recipe> searchByIngredientName(@Param("ingredientName") String ingredientName, Pageable pageable);

    @Query("SELECT r FROM Recipe r JOIN FETCH r.categories AS c WHERE UPPER(c.category) = UPPER(:category)")
    Page<Recipe> searchByCategoryName(@Param("category") String categoryName, Pageable pageable);

    @Query("SELECT r FROM Recipe r JOIN FETCH r.categories AS c WHERE c.category IN :categories")
    Page<Recipe> searchByCategoriesIn(@Param("categories") List<String> categories, Pageable pageable);
}