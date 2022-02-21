package se.lexicon.recipeassignmentapi.service.facade;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.recipeassignmentapi.model.dto.IngredientDto;
import se.lexicon.recipeassignmentapi.repository.IngredientRepository;
import se.lexicon.recipeassignmentapi.service.repository.IngredientCrudRepository;

@Service
@Transactional
public class IngredientServiceImpl extends DtoAssembler implements IngredientService{

    private final IngredientCrudRepository crudRepository;
    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientCrudRepository crudRepository, IngredientRepository ingredientRepository) {
        this.crudRepository = crudRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public IngredientDto create(IngredientDto form) {
        return toIngredientDto(crudRepository.create(form));
    }

    @Override
    public IngredientDto findById(String id) {
        return toIngredientDto(crudRepository.findById(id));
    }

    @Override
    public IngredientDto update(String id, IngredientDto form) {
        return toIngredientDto(crudRepository.update(id, form));
    }

    @Override
    public boolean delete(String id) {
        return crudRepository.delete(id);
    }

    @Override
    public IngredientDto findByIngredientName(String ingredientName) {
        return ingredientRepository.findByIngredientName(ingredientName)
                .map(this::toIngredientDto)
                .orElseThrow(getException("Could not find ingredient with ingredientName: " + ingredientName));
    }

    @Override
    public Slice<IngredientDto> searchByIngredientName(String search, Pageable pageable) {
        return ingredientRepository.searchByIngredientName(search, pageable)
                .map(this::toIngredientDto);
    }
}
