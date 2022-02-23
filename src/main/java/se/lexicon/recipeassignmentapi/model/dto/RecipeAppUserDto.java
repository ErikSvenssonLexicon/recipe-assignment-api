package se.lexicon.recipeassignmentapi.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.validation.annotation.Validated;
import se.lexicon.recipeassignmentapi.model.constants.AppRole;
import se.lexicon.recipeassignmentapi.validation.OnPost;
import se.lexicon.recipeassignmentapi.validation.OnPut;
import se.lexicon.recipeassignmentapi.validation.UniqueEmail;
import se.lexicon.recipeassignmentapi.validation.UniqueUsername;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import static se.lexicon.recipeassignmentapi.validation.ValidationMessages.*;

@Validated
public class RecipeAppUserDto implements Serializable {

    @NotBlank(groups = OnPut.class)
    private String id;
    @NotBlank(message = MANDATORY_FIELD, groups = {OnPut.class, OnPost.class})
    @UniqueUsername(message = USERNAME_ALREADY_TAKEN, groups = OnPost.class)
    private String username;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotBlank(message = MANDATORY_FIELD, groups = OnPost.class)
    private String password;
    @NotBlank(message = MANDATORY_FIELD, groups = OnPost.class)
    private String passwordConfirm;
    private AppRole appRole;
    @NotBlank(message = MANDATORY_FIELD, groups = {OnPut.class, OnPost.class})
    @UniqueEmail(message = EMAIL_NOT_AVAILABLE, groups = OnPost.class)
    private String email;
    private LocalDate registrationDate;
    private boolean suspended;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<RecipeDto> recipes;

    public RecipeAppUserDto() {
    }

    public RecipeAppUserDto(String id, String username, String password, String passwordConfirm, AppRole appRole, String email, LocalDate registrationDate, boolean suspended) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.appRole = appRole;
        this.email = email;
        this.registrationDate = registrationDate;
        this.suspended = suspended;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AppRole getAppRole() {
        return appRole;
    }

    public void setAppRole(AppRole appRole) {
        this.appRole = appRole;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean getSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public List<RecipeDto> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeDto> recipes) {
        this.recipes = recipes;
    }
}
