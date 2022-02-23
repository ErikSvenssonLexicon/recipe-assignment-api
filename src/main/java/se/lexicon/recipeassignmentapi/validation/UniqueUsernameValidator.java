package se.lexicon.recipeassignmentapi.validation;

import org.springframework.stereotype.Component;
import se.lexicon.recipeassignmentapi.repository.AppUserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final AppUserRepository appUserRepository;

    public UniqueUsernameValidator(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if(username == null) return true;
        return appUserRepository.findByUsernameIgnoreCase(username).isEmpty();
    }
}
