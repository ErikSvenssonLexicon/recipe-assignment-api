package se.lexicon.recipeassignmentapi.service.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.recipeassignmentapi.model.dto.RecipeDto;
import se.lexicon.recipeassignmentapi.model.entity.Recipe;
import se.lexicon.recipeassignmentapi.repository.RecipeRepository;
import se.lexicon.recipeassignmentapi.service.repository.RecipeCrudRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RecipeServiceImpl extends DtoAssembler implements RecipeService{

    private final RecipeCrudRepository crudRepository;
    private final RecipeRepository repository;

    public RecipeServiceImpl(RecipeCrudRepository crudRepository, RecipeRepository repository) {
        this.crudRepository = crudRepository;
        this.repository = repository;
    }

    @Override
    public RecipeDto create(RecipeDto form) {
        return toFullRecipeDto(crudRepository.create(form));
    }

    @Override
    public RecipeDto findById(String id) {
        return toFullRecipeDto(crudRepository.findById(id));
    }

    @Override
    public RecipeDto update(String id, RecipeDto form) {
        return toFullRecipeDto(crudRepository.update(id, form));
    }

    @Override
    public boolean delete(String id) {
        Recipe recipe = crudRepository.findById(id);
        return crudRepository.delete(recipe);
    }

    @Override
    public List<RecipeDto> findByRecipeTitle(String title) {
        return repository.findByRecipeTitle(title).stream()
                .map(this::toBasicRecipeDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<RecipeDto> findByHiddenFalseAndPublishedTrue(Pageable pageable) {
        return repository.findByHiddenFalseAndPublishedTrue(pageable)
                .map(this::toBasicRecipeDto);
    }

    @Override
    public Page<RecipeDto> findByIngredientName(String ingredientName, Pageable pageable) {
        return repository.findByIngredientName(ingredientName, pageable)
                .map(this::toBasicRecipeDto);
    }

    @Override
    public Page<RecipeDto> searchByCategoryName(String categoryName, Pageable pageable) {
        return repository.searchByCategoryName(categoryName, pageable)
                .map(this::toBasicRecipeDto);
    }

    @Override
    public Page<RecipeDto> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(this::toBasicRecipeDto);
    }
}
