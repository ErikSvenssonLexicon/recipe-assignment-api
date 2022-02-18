package se.lexicon.recipeassignmentapi.service.repository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.recipeassignmentapi.model.dto.IngredientDto;
import se.lexicon.recipeassignmentapi.model.entity.Ingredient;
import se.lexicon.recipeassignmentapi.repository.IngredientRepository;

@Service
@Transactional
public class IngredientCrudRepositoryImpl implements IngredientCrudRepository{

    private final IngredientRepository ingredientRepository;

    public IngredientCrudRepositoryImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient create(IngredientDto form) {
        if(form == null) throw new IllegalArgumentException("IngredientDto was null");
        return ingredientRepository.findByIngredientName(form.getIngredientName().trim())
                .orElseGet(() -> ingredientRepository.save(new Ingredient(null, form.getIngredientName().trim())));
    }

    @Override
    public Ingredient findById(String id) {
        return ingredientRepository.findById(id)
                .orElseThrow(getException("Couldn't find Ingredient with id: " + id));
    }

    @Override
    public Ingredient update(String id, IngredientDto form) {
        if(id == null) throw new IllegalArgumentException("Id was null");
        if(form == null) throw new IllegalArgumentException("IngredientDto was null");
        if(!id.equals(form.getId())){
            throw new IllegalStateException("Id didn't match IngredientDto.id");
        }
        var optional = ingredientRepository.findByIngredientName(form.getIngredientName().trim());
        if(optional.isPresent() && !optional.get().getId().equals(id)){
            throw new IllegalArgumentException("ingredientName: " + form.getIngredientName().trim() + " already exists");
        }

        Ingredient ingredient = findById(id);
        ingredient.setIngredientName(form.getIngredientName().trim());
        return ingredientRepository.save(ingredient);
    }

    @Override
    public boolean delete(String id) {
        ingredientRepository.deleteById(id);
        return !ingredientRepository.existsById(id);
    }
}
