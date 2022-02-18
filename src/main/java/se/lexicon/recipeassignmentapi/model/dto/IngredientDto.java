package se.lexicon.recipeassignmentapi.model.dto;

import java.io.Serializable;

public class IngredientDto implements Serializable {
    private String id;
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
