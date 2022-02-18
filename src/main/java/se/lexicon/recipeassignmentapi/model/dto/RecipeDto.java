package se.lexicon.recipeassignmentapi.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class RecipeDto implements Serializable {

    private String id;
    private String recipeTitle;
    private String recipeDescription;
    private LocalDateTime lastUpdate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<RecipeInstructionDto> recipeInstructions;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<RecipeIngredientDto> recipeIngredients;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<CategoryDto> categories;

    public RecipeDto() {
    }

    public RecipeDto(String id, String recipeTitle, String recipeDescription, LocalDateTime lastUpdate, List<RecipeInstructionDto> recipeInstructions, List<RecipeIngredientDto> recipeIngredients, Set<CategoryDto> categories) {
        this.id = id;
        this.recipeTitle = recipeTitle;
        this.recipeDescription = recipeDescription;
        this.lastUpdate = lastUpdate;
        this.recipeInstructions = recipeInstructions;
        this.recipeIngredients = recipeIngredients;
        this.categories = categories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<RecipeInstructionDto> getRecipeInstructions() {
        return recipeInstructions;
    }

    public void setRecipeInstructions(List<RecipeInstructionDto> recipeInstructions) {
        this.recipeInstructions = recipeInstructions;
    }

    public List<RecipeIngredientDto> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(List<RecipeIngredientDto> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public Set<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryDto> categories) {
        this.categories = categories;
    }
}
