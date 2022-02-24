package se.lexicon.recipeassignmentapi.service.repository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.recipeassignmentapi.model.dto.RecipeIngredientDto;
import se.lexicon.recipeassignmentapi.model.entity.Ingredient;
import se.lexicon.recipeassignmentapi.model.entity.Recipe;
import se.lexicon.recipeassignmentapi.model.entity.RecipeIngredient;
import se.lexicon.recipeassignmentapi.repository.RecipeIngredientRepository;

@Service
@Transactional
public class RecipeIngredientCrudRepositoryImpl implements RecipeIngredientCrudRepository{

    private final RecipeIngredientRepository repository;
    private final IngredientCrudRepository ingredientCrudRepository;

    public RecipeIngredientCrudRepositoryImpl(RecipeIngredientRepository repository, IngredientCrudRepository ingredientCrudRepository) {
        this.repository = repository;
        this.ingredientCrudRepository = ingredientCrudRepository;
    }

    @Override
    public RecipeIngredient create(RecipeIngredientDto form) {
        if(form == null) throw new IllegalArgumentException("RecipeIngredientDto was null");
        if(form.getIngredient() == null) throw new IllegalArgumentException("RecipeIngredientDto.ingredient was null");
        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setAmount(form.getAmount());
        recipeIngredient.setMeasurement(form.getMeasurement().trim());
        recipeIngredient.setIngredient(
                ingredientCrudRepository.create(form.getIngredient())
        );
        return repository.save(recipeIngredient);
    }

    @Override
    public RecipeIngredient findById(String id) {
        return repository.findById(id)
                .orElseThrow(getException("Couldn't find RecipeIngredient with id: " + id));
    }

    @Override
    public RecipeIngredient update(String id, RecipeIngredientDto form) {
        if(id == null) throw new IllegalArgumentException("Id was null");
        if(form == null) throw new IllegalArgumentException("RecipeIngredientDto was null");
        if(form.getIngredient() == null) throw new IllegalArgumentException("RecipeIngredientDto.ingredient was null");
        if(!id.equals(form.getId())){
            throw new IllegalStateException("Id didn't match RecipeIngredientDto.id");
        }
        RecipeIngredient recipeIngredient = findById(id);
        Ingredient originalIngredient = recipeIngredient.getIngredient();
        Ingredient ingredient = null;

        if(!form.getIngredient().getIngredientName().trim().equalsIgnoreCase(originalIngredient.getIngredientName())){
            ingredient = ingredientCrudRepository.create(form.getIngredient());
        }

        recipeIngredient.setMeasurement(form.getMeasurement().trim());
        recipeIngredient.setAmount(form.getAmount());
        if(ingredient != null) recipeIngredient.setIngredient(ingredient);
        recipeIngredient =  repository.save(recipeIngredient);

        if(ingredient != null && repository.countUsagesByIngredientId(originalIngredient.getId()) == 0){
            ingredientCrudRepository.delete(originalIngredient.getId());
        }
        return recipeIngredient;
    }

    @Override
    public boolean delete(String id) {
        repository.deleteById(id);
        return !repository.existsById(id);
    }

    @Override
    public RecipeIngredient create(RecipeIngredientDto form, Recipe recipe) {
        RecipeIngredient recipeIngredient = create(form);
        recipeIngredient.setRecipe(recipe);
        return recipeIngredient;
    }
}
