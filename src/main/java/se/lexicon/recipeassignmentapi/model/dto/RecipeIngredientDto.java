package se.lexicon.recipeassignmentapi.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.validation.annotation.Validated;
import se.lexicon.recipeassignmentapi.validation.OnPost;
import se.lexicon.recipeassignmentapi.validation.OnPut;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

import static se.lexicon.recipeassignmentapi.validation.ValidationMessages.MANDATORY_FIELD;
import static se.lexicon.recipeassignmentapi.validation.ValidationMessages.POSITIVE_VALUE;

@Validated
public class RecipeIngredientDto implements Serializable {

    private String id;
    @NotNull(message = MANDATORY_FIELD, groups = {OnPut.class, OnPost.class})
    @Positive(message = POSITIVE_VALUE, groups = {OnPut.class, OnPost.class})
    private Double amount;
    @NotBlank(message = MANDATORY_FIELD, groups = {OnPut.class, OnPost.class})
    private String measurement;
    @NotNull(message = MANDATORY_FIELD, groups = {OnPut.class, OnPost.class})
    @Valid
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
