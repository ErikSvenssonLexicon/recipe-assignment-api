package se.lexicon.recipeassignmentapi.service.facade;

import se.lexicon.recipeassignmentapi.model.dto.*;
import se.lexicon.recipeassignmentapi.model.entity.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DtoAssembler {
    public CategoryDto toCategoryDto(Category category){
        CategoryDto categoryDto = null;
        if(category != null){
            categoryDto = new CategoryDto();
            categoryDto.setId(category.getId());
            categoryDto.setCategoryName(category.getCategoryName());
        }
        return categoryDto;
    }

    public IngredientDto toIngredientDto(Ingredient ingredient){
        IngredientDto ingredientDto = null;
        if(ingredient != null){
            ingredientDto = new IngredientDto();
            ingredientDto.setId(ingredient.getId());
            ingredientDto.setIngredientName(ingredient.getIngredientName());
        }
        return ingredientDto;
    }

    public RecipeIngredientDto toRecipeIngredientDto(RecipeIngredient recipeIngredient){
        RecipeIngredientDto recipeIngredientDto = null;
        if(recipeIngredient != null){
            recipeIngredientDto = new RecipeIngredientDto();
            recipeIngredientDto.setId(recipeIngredient.getId());
            recipeIngredientDto.setAmount(recipeIngredient.getAmount());
            recipeIngredientDto.setMeasurement(recipeIngredient.getMeasurement());
            recipeIngredientDto.setIngredient(toIngredientDto(recipeIngredient.getIngredient()));
        }
        return recipeIngredientDto;
    }

    public RecipeInstructionDto toRecipeInstructionDto(RecipeInstruction recipeInstruction){
        RecipeInstructionDto recipeInstructionDto = null;
        if(recipeInstruction != null){
            recipeInstructionDto = new RecipeInstructionDto();
            recipeInstructionDto.setId(recipeInstruction.getId());
            recipeInstructionDto.setInstruction(recipeInstruction.getInstruction());
        }
        return recipeInstructionDto;
    }

    public List<RecipeInstructionDto> toRecipeInstructionDtoList(List<RecipeInstruction> recipeInstructions){
        return recipeInstructions.stream()
                .map(this::toRecipeInstructionDto)
                .collect(Collectors.toList());
    }

    public List<RecipeIngredientDto> toRecipeIngredientDtoList(List<RecipeIngredient> recipeIngredients){
        return recipeIngredients.stream()
                .map(this::toRecipeIngredientDto)
                .collect(Collectors.toList());
    }

    public Set<CategoryDto> toCategoryDtoSet(Set<Category> categories){
        return categories.stream()
                .map(this::toCategoryDto)
                .collect(Collectors.toSet());
    }

    public RecipeDto toBasicRecipeDto(Recipe recipe){
        RecipeDto recipeDto = null;
        if(recipe != null){
            recipeDto = new RecipeDto();
            recipeDto.setId(recipe.getId());
            recipeDto.setRecipeTitle(recipe.getRecipeTitle());
            recipeDto.setLastUpdate(recipe.getLastUpdate());
            recipeDto.setPublished(recipe.isPublished());
            recipeDto.setHidden(recipe.isHidden());
        }
        return recipeDto;
    }

    public RecipeDto toFullRecipeDto(Recipe recipe){
        RecipeDto recipeDto = null;
        if(recipe != null){
            recipeDto = toBasicRecipeDto(recipe);
            recipeDto.setRecipeDescription(recipe.getRecipeDescription());
            recipeDto.setRecipeInstructions(toRecipeInstructionDtoList(recipe.getRecipeInstructions()));
            recipeDto.setRecipeIngredients(toRecipeIngredientDtoList(recipe.getRecipeIngredients()));
            recipeDto.setCategories(toCategoryDtoSet(recipe.getCategories()));
        }
        return recipeDto;
    }
}
