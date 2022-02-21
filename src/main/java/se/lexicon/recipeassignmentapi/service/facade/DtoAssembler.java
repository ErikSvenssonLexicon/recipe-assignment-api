package se.lexicon.recipeassignmentapi.service.facade;

import se.lexicon.recipeassignmentapi.model.dto.CategoryDto;
import se.lexicon.recipeassignmentapi.model.entity.Category;

public class DtoAssembler {
    public CategoryDto toCategoryDto(Category category){
        CategoryDto categoryDto = null;
        if(category != null){
            categoryDto = new CategoryDto(category.getId(), category.getCategoryName());
        }
        return categoryDto;
    }
}
