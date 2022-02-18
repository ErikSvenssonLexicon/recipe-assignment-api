package se.lexicon.recipeassignmentapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.recipeassignmentapi.model.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {
    @Query("SELECT c FROM Category c WHERE UPPER(c.categoryName) = UPPER(:category)")
    Optional<Category> findByCategory(@Param("category") String category);
}