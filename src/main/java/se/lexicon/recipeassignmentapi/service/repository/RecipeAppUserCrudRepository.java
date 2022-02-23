package se.lexicon.recipeassignmentapi.service.repository;

import se.lexicon.recipeassignmentapi.model.constants.AppRole;
import se.lexicon.recipeassignmentapi.model.dto.RecipeAppUserDto;
import se.lexicon.recipeassignmentapi.model.entity.RecipeAppUser;
import se.lexicon.recipeassignmentapi.service.GenericCrudRepository;

public interface RecipeAppUserCrudRepository extends GenericCrudRepository<RecipeAppUser, String, RecipeAppUserDto> {
    RecipeAppUser create(RecipeAppUserDto dto, AppRole appRole);
}
