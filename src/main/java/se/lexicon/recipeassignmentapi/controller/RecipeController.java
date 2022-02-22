package se.lexicon.recipeassignmentapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import se.lexicon.recipeassignmentapi.model.dto.RecipeDto;
import se.lexicon.recipeassignmentapi.service.facade.RecipeService;
import se.lexicon.recipeassignmentapi.validation.OnPost;

@RestController
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("api/v1/recipes")
    public ResponseEntity<RecipeDto> create(@RequestBody @Validated(value = OnPost.class) RecipeDto recipeDto){
        return ResponseEntity.status(201).body(
                recipeService.create(recipeDto)
        );
    }
}
