package se.lexicon.recipeassignmentapi.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

public class RecipeIngredientDto implements Serializable {
    private String id;
    private Double amount;
    private String measurement;
    private IngredientDto ingredient;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private RecipeDto recipe;

    public RecipeIngredientDto() {
    }

    public RecipeIngredientDto(String id, Double amount, String measurement, IngredientDto ingredient) {
        this.id = id;
        this.amount = amount;
        this.measurement = measurement;
        this.ingredient = ingredient;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public IngredientDto getIngredient() {
        return ingredient;
    }

    public void setIngredient(IngredientDto ingredient) {
        this.ingredient = ingredient;
    }

    public RecipeDto getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipeDto recipe) {
        this.recipe = recipe;
    }
}
