package se.lexicon.recipeassignmentapi.service.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se.lexicon.recipeassignmentapi.model.dto.RecipeDto;
import se.lexicon.recipeassignmentapi.service.GenericCrudRepository;

import java.util.List;

public interface RecipeService extends GenericCrudRepository<RecipeDto, String, RecipeDto> {
    List<RecipeDto> findByRecipeTitle(String title);
    Page<RecipeDto> findByHiddenFalseAndPublishedTrue(Pageable pageable);
    Page<RecipeDto> findByIngredientName(String ingredientName, Pageable pageable);
    Page<RecipeDto> searchByCategoryName(String categoryName, Pageable pageable);
    Page<RecipeDto> findAll(Pageable pageable);
}
