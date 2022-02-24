package se.lexicon.recipeassignmentapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.lexicon.recipeassignmentapi.model.dto.RecipeDto;
import se.lexicon.recipeassignmentapi.service.facade.RecipeService;
import se.lexicon.recipeassignmentapi.validation.OnPost;
import se.lexicon.recipeassignmentapi.validation.OnPut;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/api/v1/recipes")
    public ResponseEntity<RecipeDto> create(@RequestBody @Validated(value = OnPost.class) RecipeDto recipeDto){
        return ResponseEntity.status(201).body(
                recipeService.create(recipeDto)
        );
    }

    @GetMapping("/api/v1/recipes/{id}")
    public ResponseEntity<RecipeDto> findById(@PathVariable("id") String id){
        return ResponseEntity.ok(recipeService.findById(id));
    }

    @PreAuthorize("#form.authorId == authentication.principal.userId || hasRole('APP_ADMIN')")
    @PutMapping("/api/v1/recipes/{id}")
    public ResponseEntity<RecipeDto> update(@PathVariable("id") String id, @Validated(OnPut.class) @RequestBody RecipeDto form){
        return ResponseEntity.ok(recipeService.update(id, form));
    }

    @DeleteMapping("/api/v1/recipes/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable("id") String id) {
        boolean result = recipeService.delete(id);
        Map<String , String> response = new HashMap<>();
        response.put("message", result ? "Recepted är nu borttaget" : "Kunde inte uppfylla din begäran");
        return ResponseEntity.ok(response);
    }
}
