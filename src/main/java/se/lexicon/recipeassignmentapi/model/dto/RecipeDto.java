package se.lexicon.recipeassignmentapi.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.validation.annotation.Validated;
import se.lexicon.recipeassignmentapi.validation.OnPost;
import se.lexicon.recipeassignmentapi.validation.OnPut;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static se.lexicon.recipeassignmentapi.validation.ValidationMessages.MANDATORY_FIELD;

@Validated
public class RecipeDto implements Serializable {

    @NotBlank(groups = OnPut.class)
    private String id;
    @NotBlank(message = MANDATORY_FIELD, groups = {OnPut.class, OnPost.class})
    private String recipeTitle;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String recipeDescription;
    private LocalDateTime lastUpdate;
    private boolean published;
    private boolean hidden;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<@Valid RecipeInstructionDto> recipeInstructions;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<@Valid RecipeIngredientDto> recipeIngredients;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<@Valid CategoryDto> categories;

    public RecipeDto() {
    }

    public RecipeDto(String id, String recipeTitle, String recipeDescription, LocalDateTime lastUpdate, boolean published, boolean hidden, List<RecipeInstructionDto> recipeInstructions, List<RecipeIngredientDto> recipeIngredients, Set<CategoryDto> categories) {
        this.id = id;
        this.recipeTitle = recipeTitle;
        this.recipeDescription = recipeDescription;
        this.lastUpdate = lastUpdate;
        this.published = published;
        this.hidden = hidden;
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

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
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
