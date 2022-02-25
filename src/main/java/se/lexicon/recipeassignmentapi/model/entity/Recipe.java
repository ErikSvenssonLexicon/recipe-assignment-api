package se.lexicon.recipeassignmentapi.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

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
    private String authorId;

    @OneToMany(
            cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY,
            mappedBy = "recipe",
            orphanRemoval = true
    )
    private List<RecipeInstruction> recipeInstructions;

    @OneToMany(
            cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY,
            mappedBy = "recipe",
            orphanRemoval = true
    )
    private List<RecipeIngredient> recipeIngredients;

    @ManyToMany(
            cascade = {CascadeType.DETACH, CascadeType.REFRESH},
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

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String author) {
        this.authorId = author;
    }

    public List<RecipeInstruction> getRecipeInstructions() {
        if(recipeInstructions == null) recipeInstructions = new ArrayList<>();
        return recipeInstructions;
    }

    public void setRecipeInstructions(List<RecipeInstruction> recipeInstructions) {
        if(recipeInstructions == null) recipeInstructions = new ArrayList<>();
        if(this.recipeInstructions == null) this.recipeInstructions = new ArrayList<>();
        this.recipeInstructions.clear();
        this.recipeInstructions.addAll(recipeInstructions);
    }

    public List<RecipeIngredient> getRecipeIngredients() {
        if(recipeIngredients == null) recipeIngredients = new ArrayList<>();
        return recipeIngredients;
    }

    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
        if(recipeIngredients == null) recipeIngredients = new ArrayList<>();
        if(this.recipeIngredients == null) this.recipeIngredients = new ArrayList<>();
        this.recipeIngredients.clear();
        this.recipeIngredients.addAll(recipeIngredients);
    }

    public Set<Category> getCategories() {
        if(categories == null) categories = new HashSet<>();
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        if(categories == null) categories = new HashSet<>();
        if(categories.isEmpty()){
            if(this.categories != null){
                this.categories.forEach(category -> category.getRecipes().remove(this));
            }
        }else{
            categories.forEach(category -> category.getRecipes().add(this));
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(id, recipe.id) && Objects.equals(recipeTitle, recipe.recipeTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, recipeTitle);
    }
}
