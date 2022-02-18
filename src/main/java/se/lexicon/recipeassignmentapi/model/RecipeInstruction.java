package se.lexicon.recipeassignmentapi.model;

import java.util.Objects;

public class RecipeInstruction {
    private String id;
    private String instruction;

    public RecipeInstruction(String id, String instruction) {
        this.id = id;
        this.instruction = instruction;
    }

    public RecipeInstruction() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeInstruction that = (RecipeInstruction) o;
        return Objects.equals(id, that.id) && Objects.equals(instruction, that.instruction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, instruction);
    }
}
