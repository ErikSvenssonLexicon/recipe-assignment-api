package se.lexicon.recipeassignmentapi.model.dto;

import org.springframework.validation.annotation.Validated;
import se.lexicon.recipeassignmentapi.validation.OnPost;
import se.lexicon.recipeassignmentapi.validation.OnPut;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

import static se.lexicon.recipeassignmentapi.validation.ValidationMessages.MANDATORY_FIELD;

@Validated
public class IngredientDto implements Serializable {
    private String id;
    @NotBlank(message = MANDATORY_FIELD, groups = {OnPost.class, OnPut.class})
    private String ingredientName;

    public IngredientDto() {
    }

    public IngredientDto(String id, String ingredientName) {
        this.id = id;
        this.ingredientName = ingredientName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}
