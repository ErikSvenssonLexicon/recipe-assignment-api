package se.lexicon.recipeassignmentapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.recipeassignmentapi.model.entity.RecipeAppUser;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<RecipeAppUser, String> {
    Optional<RecipeAppUser> findByUsernameIgnoreCase(String username);
    Optional<RecipeAppUser> findByEmailIgnoreCase(String email);
}
