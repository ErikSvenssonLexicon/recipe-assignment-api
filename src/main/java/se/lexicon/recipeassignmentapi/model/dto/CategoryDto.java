package se.lexicon.recipeassignmentapi.model.dto;

import java.io.Serializable;

public class CategoryDto implements Serializable {
    private String id;
    private String categoryName;

    public CategoryDto() {
    }

    public CategoryDto(String id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
