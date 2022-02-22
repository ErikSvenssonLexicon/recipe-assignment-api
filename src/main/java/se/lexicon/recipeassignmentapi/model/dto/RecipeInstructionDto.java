package se.lexicon.recipeassignmentapi.model.dto;

import org.springframework.validation.annotation.Validated;
import se.lexicon.recipeassignmentapi.validation.OnPost;
import se.lexicon.recipeassignmentapi.validation.OnPut;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

import static se.lexicon.recipeassignmentapi.validation.ValidationMessages.MANDATORY_FIELD;

@Validated
public class RecipeInstructionDto implements Serializable {
    private String id;
    @NotBlank(message = MANDATORY_FIELD, groups = {OnPut.class, OnPost.class})
    private String instruction;

    public RecipeInstructionDto() {
    }

    public RecipeInstructionDto(String id, String instruction) {
        this.id = id;
        this.instruction = instruction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}
