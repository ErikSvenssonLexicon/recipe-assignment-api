package se.lexicon.recipeassignmentapi.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name ="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, name = "id")
    private String id;
    @Column(name = "recipe_title")
    private String recipeTitle;
    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name = "recipe_description", length = 2000)
    private String recipeDescription;
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
    @Column(name = "published")
    private boolean published;
    @Column(name = "hidden")
    private boolean hidden;

    @OneToMany(
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY,
            mappedBy = "recipe",
            orphanRemoval = true
    )
    private List<RecipeInstruction> recipeInstructions;

    @OneToMany(
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY,
            mappedBy = "recipe",
            orphanRemoval = true
    )
    private List<RecipeIngredient> recipeIngredients;

    @ManyToMany(
            cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "recipe_category",
            joinColumns = @JoinColumn(name = "fk_recipe_id", table = "recipe_category"),
            inverseJoinColumns = @JoinColumn(name = "fk_category_id", table = "recipe_category")
    )
    private Set<Category> categories;

    public Recipe(String id,
                  String recipeTitle,
                  String recipeDescription,
                  LocalDateTime lastUpdate,
                  List<RecipeInstruction> recipeInstructions,
                  List<RecipeIngredient> recipeIngredients,
                  Set<Category> categories) {
        this.id = id;
        this.recipeTitle = recipeTitle;
        this.recipeDescription = recipeDescription;
        this.lastUpdate = lastUpdate;
        this.recipeInstructions = recipeInstructions;
        this.recipeIngredients = recipeIngredients;
        this.categories = categories;
    }

    public Recipe() {
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

    public void setRecipeDescription(String recipeInstruction) {
        this.recipeDescription = recipeInstruction;
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

    public List<RecipeInstruction> getRecipeInstructions() {
        if(recipeInstructions == null) recipeInstructions = new ArrayList<>();
        return recipeInstructions;
    }

    public void setRecipeInstructions(List<RecipeInstruction> recipeInstructions) {
        if(recipeInstructions == null) recipeInstructions = new ArrayList<>();
        if(recipeInstructions.isEmpty()){
            if(this.recipeInstructions != null){
                this.recipeInstructions.forEach(recipeInstruction -> recipeInstruction.setRecipe(null));
            }
        }else{
            recipeInstructions.forEach(recipeInstruction -> recipeInstruction.setRecipe(this));
        }
        this.recipeInstructions = recipeInstructions;
    }

    public List<RecipeIngredient> getRecipeIngredients() {
        if(recipeIngredients == null) recipeIngredients = new ArrayList<>();
        return recipeIngredients;
    }

    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
        if(recipeIngredients == null) recipeIngredients = new ArrayList<>();
        if(recipeIngredients.isEmpty()){
            if(this.recipeIngredients != null){
                this.recipeIngredients.forEach(recipeIngredient -> recipeIngredient.setRecipe(null));
            }
        }else{
            recipeIngredients.forEach(recipeIngredient -> recipeIngredient.setRecipe(this));
        }
        this.recipeIngredients = recipeIngredients;
    }

    public Set<Category> getCategories() {
        if(categories == null) categories = new HashSet<>();
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @PrePersist
    void prePersist(){
        setLastUpdate(lastUpdate != null ? lastUpdate : LocalDateTime.now());
    }

    @PreUpdate
    void preUpdate(){
        setLastUpdate(LocalDateTime.now());
    }
}
