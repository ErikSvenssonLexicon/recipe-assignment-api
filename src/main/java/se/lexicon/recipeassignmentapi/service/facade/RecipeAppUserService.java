package se.lexicon.recipeassignmentapi.service.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se.lexicon.recipeassignmentapi.model.constants.AppRole;
import se.lexicon.recipeassignmentapi.model.dto.RecipeAppUserDto;
import se.lexicon.recipeassignmentapi.service.GenericCrudRepository;

public interface RecipeAppUserService extends GenericCrudRepository<RecipeAppUserDto, String, RecipeAppUserDto> {
    RecipeAppUserDto create(RecipeAppUserDto form, AppRole appRole);
    RecipeAppUserDto findByUsername(String username);
    RecipeAppUserDto findByEmail(String email);
    Page<RecipeAppUserDto> findAll(Pageable pageable);
}
