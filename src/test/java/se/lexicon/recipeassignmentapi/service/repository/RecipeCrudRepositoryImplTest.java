package se.lexicon.recipeassignmentapi.service.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.recipeassignmentapi.model.dto.*;
import se.lexicon.recipeassignmentapi.model.entity.Ingredient;
import se.lexicon.recipeassignmentapi.model.entity.Recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
@DirtiesContext
class RecipeCrudRepositoryImplTest {

    @Autowired
    private RecipeCrudRepositoryImpl testObject;

    @Autowired
    private TestEntityManager em;

    public List<Ingredient> ingredients(){
        return Arrays.asList(
                new Ingredient(null, "Ingredient 1"),
                new Ingredient(null, "Ingredient 2"),
                new Ingredient(null, "Ingredient 3")
        );
    }

    @BeforeEach
    void setUp() {
        ingredients().forEach(em::persist);

    }

    @Test
    @DisplayName("Given title and list of instructions should return expected result")
    void create_title_and_instructions_only() {
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setRecipeTitle("Test title ");
        List<RecipeInstructionDto> instructionDtos = new ArrayList<>();
        instructionDtos.add(new RecipeInstructionDto(null, "Do this"));
        instructionDtos.add(new RecipeInstructionDto(null, "Do that"));
        recipeDto.setRecipeInstructions(instructionDtos);

        Recipe result = testObject.create(recipeDto);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("Test title", result.getRecipeTitle());
        assertEquals("", result.getRecipeDescription());
        assertFalse(result.isHidden());
        assertFalse(result.isPublished());
        assertNotNull(result.getLastUpdate());
        assertNotNull(result.getRecipeIngredients());
        assertEquals(2, result.getRecipeInstructions().size());
        assertNotNull(result.getCategories());
    }

    @Test
    void create_full() {
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setRecipeTitle("Test title");
        recipeDto.setRecipeDescription("Test description");
        recipeDto.setRecipeInstructions(Arrays.asList(new RecipeInstructionDto(null, "Do this"), new RecipeInstructionDto(null,"Do that")));
        recipeDto.setCategories(new HashSet<>(Arrays.asList(new CategoryDto(null, "cheap"), new CategoryDto(null, "indian"))));
        List<RecipeIngredientDto> recipeIngredientDtos = new ArrayList<>();
        recipeIngredientDtos.add(new RecipeIngredientDto(null, 2.5, "dl", new IngredientDto(null, "sugar")));
        recipeIngredientDtos.add(new RecipeIngredientDto(null, 10.0, "g", new IngredientDto(null, "Ingredient 1")));
        recipeDto.setRecipeIngredients(recipeIngredientDtos);

        Recipe result = testObject.create(recipeDto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("Test title", result.getRecipeTitle());
        assertEquals("Test description", result.getRecipeDescription());
        assertFalse(result.isHidden());
        assertFalse(result.isPublished());
        assertNotNull(result.getLastUpdate());
        assertEquals(2, result.getRecipeInstructions().size());
        assertEquals(2, result.getCategories().size());
        assertEquals(2, result.getRecipeIngredients().size());

    }

    @Test
    void update() {
        Recipe recipe = em.persist(new Recipe(null, "Some title", "Some description", null, null, null, null));

        RecipeDto recipeDto = new RecipeDto(recipe.getId(), recipe.getRecipeTitle(), recipe.getRecipeDescription(), null, true, false, null, null, null);
        recipeDto.setRecipeInstructions(Arrays.asList(new RecipeInstructionDto(null, "Do this"), new RecipeInstructionDto(null, "Do that")));
        recipeDto.setCategories(new HashSet<>(Arrays.asList(new CategoryDto(null, "Mexican"), new CategoryDto(null, "Spicy"))));
        List<RecipeIngredientDto> recipeIngredientDtos = new ArrayList<>();
        recipeIngredientDtos.add(new RecipeIngredientDto(null, 2.5, "tsk", new IngredientDto(null, "chipotle")));
        recipeIngredientDtos.add(new RecipeIngredientDto(null, 10.50, "tsk", new IngredientDto(null, "spam")));
        recipeDto.setRecipeIngredients(recipeIngredientDtos);

        Recipe result = testObject.update(recipe.getId(), recipeDto);
        assertEquals(recipe.getId(), result.getId());
        assertTrue(result.isPublished());
        assertEquals("Some title", result.getRecipeTitle());
        assertEquals("Some description", result.getRecipeDescription());
        assertEquals(2, result.getRecipeInstructions().size());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getRecipeIngredients().size());
        assertFalse(result.isHidden());
    }
}