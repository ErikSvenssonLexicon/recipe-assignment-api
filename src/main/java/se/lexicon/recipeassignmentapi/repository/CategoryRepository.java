package se.lexicon.recipeassignmentapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.recipeassignmentapi.model.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
}