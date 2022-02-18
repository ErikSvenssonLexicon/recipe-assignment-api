package se.lexicon.recipeassignmentapi.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "recipe_instructions")
public class RecipeInstruction {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name ="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, name = "id")
    private String id;
    @Column(name = "instruction", length = 500)
    private String instruction;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH} , fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_recipe_id")
    private Recipe recipe;

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

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
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
