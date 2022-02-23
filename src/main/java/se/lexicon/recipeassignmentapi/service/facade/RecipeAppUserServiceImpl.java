package se.lexicon.recipeassignmentapi.service.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.recipeassignmentapi.model.constants.AppRole;
import se.lexicon.recipeassignmentapi.model.dto.RecipeAppUserDto;
import se.lexicon.recipeassignmentapi.model.entity.RecipeAppUser;
import se.lexicon.recipeassignmentapi.repository.AppUserRepository;
import se.lexicon.recipeassignmentapi.repository.RecipeRepository;
import se.lexicon.recipeassignmentapi.service.repository.RecipeAppUserCrudRepository;

@Service
@Transactional
public class RecipeAppUserServiceImpl extends DtoAssembler implements RecipeAppUserService{

    private final RecipeAppUserCrudRepository crudRepository;
    private final AppUserRepository appUserRepository;
    private final RecipeRepository recipeRepository;

    public RecipeAppUserServiceImpl(RecipeAppUserCrudRepository crudRepository, AppUserRepository appUserRepository, RecipeRepository recipeRepository) {
        this.crudRepository = crudRepository;
        this.appUserRepository = appUserRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public RecipeAppUserDto create(RecipeAppUserDto form) {
        return create(form, AppRole.ROLE_APP_USER);
    }

    @Override
    public RecipeAppUserDto findById(String id) {
        return toFullRecipeAppUserDto(
                crudRepository.findById(id),
                recipeRepository.findByAuthorId(id)
        );
    }

    @Override
    public RecipeAppUserDto update(String id, RecipeAppUserDto form) {
        return toFullRecipeAppUserDto(
                crudRepository.update(id, form),
                recipeRepository.findByAuthorId(id)
        );
    }

    @Override
    public boolean delete(String id) {
        return crudRepository.delete(id);
    }

    @Override
    public RecipeAppUserDto create(RecipeAppUserDto form, AppRole appRole) {
        RecipeAppUser appUser = crudRepository.create(form, appRole);
        return toFullRecipeAppUserDto(appUser, null);
    }

    @Override
    public RecipeAppUserDto findByUsername(String username) {
        return appUserRepository.findByUsernameIgnoreCase(username)
                .map(this::toBasicRecipeAppUserDto)
                .orElseThrow(getException("Could not find user with provided username"));
    }

    @Override
    public RecipeAppUserDto findByEmail(String email) {
        return appUserRepository.findByEmailIgnoreCase(email)
                .map(this::toBasicRecipeAppUserDto)
                .orElseThrow(getException("Could not find user with email " + email));
    }

    @Override
    public Page<RecipeAppUserDto> findAll(Pageable pageable) {
        return appUserRepository.findAll(pageable)
                .map(this::toBasicRecipeAppUserDto);
    }
}
