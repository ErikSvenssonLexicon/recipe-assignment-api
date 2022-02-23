package se.lexicon.recipeassignmentapi.model.entity;

import org.hibernate.annotations.GenericGenerator;
import se.lexicon.recipeassignmentapi.model.constants.AppRole;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "app_users")
public class RecipeAppUser {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name ="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, name = "id")
    private String id;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "app_role")
    private AppRole appRole;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "reg_date")
    private LocalDate registrationDate;
    @Column(name = "suspended")
    private boolean suspended;

    public RecipeAppUser(String id, String username, String password, String email, LocalDate registrationDate, boolean suspended) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.registrationDate = registrationDate;
        this.suspended = suspended;
    }

    public RecipeAppUser() {
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

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    @PrePersist
    void prePersist(){
        registrationDate = LocalDate.now();
    }
}
