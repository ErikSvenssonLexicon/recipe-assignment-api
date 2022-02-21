package se.lexicon.recipeassignmentapi.service.facade;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import se.lexicon.recipeassignmentapi.model.dto.IngredientDto;
import se.lexicon.recipeassignmentapi.service.GenericCrudRepository;

public interface IngredientService extends GenericCrudRepository<IngredientDto, String, IngredientDto> {
    IngredientDto findByIngredientName(String ingredientName);
    Slice<IngredientDto> searchByIngredientName(String search, Pageable pageable);
}
