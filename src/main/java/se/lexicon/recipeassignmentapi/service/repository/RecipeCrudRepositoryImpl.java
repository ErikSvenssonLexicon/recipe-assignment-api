package se.lexicon.recipeassignmentapi.service.repository;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.recipeassignmentapi.model.dto.RecipeDto;
import se.lexicon.recipeassignmentapi.model.entity.Recipe;
import se.lexicon.recipeassignmentapi.model.entity.RecipeInstruction;
import se.lexicon.recipeassignmentapi.repository.RecipeRepository;
import se.lexicon.recipeassignmentapi.security.RecipeUserDetails;

import java.util.stream.Collectors;

@Service
@Transactional
public class RecipeCrudRepositoryImpl implements RecipeCrudRepository{

    private final RecipeRepository repository;
    private final RecipeIngredientCrudRepository recipeIngredientCrudRepository;
    private final CategoryCrudRepository categoryCrudRepository;

    public RecipeCrudRepositoryImpl(
            RecipeRepository repository,
            RecipeIngredientCrudRepository recipeIngredientCrudRepository,
            CategoryCrudRepository categoryCrudRepository)
    {
        this.repository = repository;
        this.recipeIngredientCrudRepository = recipeIngredientCrudRepository;
        this.categoryCrudRepository = categoryCrudRepository;
    }

    public Recipe internalUpdate(RecipeDto form, Recipe recipe){
        if(recipe == null) recipe = new Recipe();
        recipe.setRecipeTitle(form.getRecipeTitle().trim());
        //Non required field when creating a Recipe, conditionally set if present
        recipe.setRecipeDescription(form.getRecipeDescription() == null ? "" : form.getRecipeDescription().trim());
        recipe.setPublished(form.isPublished());
        recipe.setHidden(form.isHidden());
        //Non required collection when creating set only if defined
        if(form.getRecipeInstructions() != null && form.getRecipeInstructions().size() > 0){
            /*
            Streaming through dtos and converting them to entities.
            Will work with detached recipeIngredient entities because of CascadeType.Persist
            */
            recipe.setRecipeInstructions(form.getRecipeInstructions().stream()
                    .map(dto -> new RecipeInstruction(dto.getId(), dto.getInstruction()))
                    .collect(Collectors.toList())
            );

            if(form.getCategories() != null && form.getCategories().size() > 0){
                recipe.setCategories(form.getCategories().stream()
                        //Will find or save new Category with unique categoryName for each dto
                        .map(categoryCrudRepository::create)
                        .collect(Collectors.toSet())
                );
            }
        }
        return recipe;
    }

    @Override
    public Recipe create(RecipeDto form) {
        if(form == null) throw new IllegalArgumentException("RecipeDto was null");
        Recipe recipe = new Recipe();
        recipe = internalUpdate(form, recipe);

        if(form.getRecipeIngredients() != null && form.getRecipeIngredients().size() > 0){
            //Streaming through dtos and converting them to entities by delegating to RecipeIngredientCrudRepository
            recipe.setRecipeIngredients(form.getRecipeIngredients().stream()
                    .map(recipeIngredientCrudRepository::create)
                    .collect(Collectors.toList())
            );
        }
        RecipeUserDetails authorDetails = (RecipeUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        recipe.setAuthorId(authorDetails.getUserId());
        return repository.save(recipe);
    }

    @Override
    public Recipe findById(String id) {
        return repository.findById(id)
                .orElseThrow(getException("Couldn't find recipe with id: " + id));
    }

    @Override
    public Recipe update(String id, RecipeDto form) {
        if(id == null) throw new IllegalArgumentException("id was null");
        if(form == null) throw new IllegalArgumentException("form.id was null");
        if(!id.equals(form.getId())) throw new IllegalStateException("Id did not match form.id");
        Recipe recipe = findById(id);
        recipe = internalUpdate(form, recipe);

        if(form.getRecipeIngredients() != null && form.getRecipeIngredients().size() > 0){
            recipe.setRecipeIngredients(form.getRecipeIngredients().stream()
                    .map(dto -> dto.getId() == null ? recipeIngredientCrudRepository.create(dto) : recipeIngredientCrudRepository.update(dto.getId(), dto))
                    .collect(Collectors.toList())
            );
        }

        return repository.save(recipe);
    }

    @Override
    public boolean delete(String id) {
        Recipe recipe = findById(id);
        recipe.setCategories(null);
        recipe.setRecipeInstructions(null);
        recipe.setRecipeIngredients(null);
        recipe = repository.save(recipe);
        repository.delete(recipe);
        return !repository.existsById(id);
    }
}
