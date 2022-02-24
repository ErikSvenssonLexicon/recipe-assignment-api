package se.lexicon.recipeassignmentapi.model.dto;

import org.springframework.validation.annotation.Validated;
import se.lexicon.recipeassignmentapi.validation.OnPost;
import se.lexicon.recipeassignmentapi.validation.OnPut;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

import static se.lexicon.recipeassignmentapi.validation.ValidationMessages.MANDATORY_FIELD;

@Validated
public class CategoryDto implements Serializable {


    private String id;
    @NotBlank(message = MANDATORY_FIELD, groups = {OnPut.class, OnPost.class})
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
