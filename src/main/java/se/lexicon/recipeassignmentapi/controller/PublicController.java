package se.lexicon.recipeassignmentapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import se.lexicon.recipeassignmentapi.model.constants.AppRole;
import se.lexicon.recipeassignmentapi.model.dto.RecipeAppUserDto;
import se.lexicon.recipeassignmentapi.service.facade.RecipeAppUserService;
import se.lexicon.recipeassignmentapi.validation.OnPost;

import java.nio.file.Paths;

@RestController
public class PublicController {

    private final RecipeAppUserService service;

    public PublicController(RecipeAppUserService service) {
        this.service = service;
    }

    @PostMapping("/api/v1/public/register")
    public ResponseEntity<RecipeAppUserDto> register(@Validated(OnPost.class) @RequestBody RecipeAppUserDto form){
        return ResponseEntity.created(Paths.get("/api/v1/public/register").toUri()).body(
                service.create(form, AppRole.ROLE_APP_USER)
        );
    }
}
