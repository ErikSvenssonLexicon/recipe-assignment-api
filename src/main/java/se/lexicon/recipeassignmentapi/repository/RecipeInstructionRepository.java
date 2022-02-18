package se.lexicon.recipeassignmentapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.recipeassignmentapi.model.RecipeInstruction;

public interface RecipeInstructionRepository extends JpaRepository<RecipeInstruction, String> {
}