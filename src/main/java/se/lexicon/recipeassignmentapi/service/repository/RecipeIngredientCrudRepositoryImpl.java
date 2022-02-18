package se.lexicon.recipeassignmentapi.service.repository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.recipeassignmentapi.model.dto.RecipeIngredientDto;
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
        recipeIngredient.setMeasurement(form.getMeasurement().trim());
        recipeIngredient.setAmount(form.getAmount());
        recipeIngredient.setIngredient(
                form.getIngredient().getId() == null ? ingredientCrudRepository.create(form.getIngredient()) : ingredientCrudRepository.findById(form.getIngredient().getId())
        );
        return repository.save(recipeIngredient);
    }

    @Override
    public boolean delete(String id) {
        repository.deleteById(id);
        return !repository.existsById(id);
    }
}
