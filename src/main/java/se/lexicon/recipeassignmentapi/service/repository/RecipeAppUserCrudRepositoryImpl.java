package se.lexicon.recipeassignmentapi.service.repository;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.recipeassignmentapi.model.constants.AppRole;
import se.lexicon.recipeassignmentapi.model.dto.RecipeAppUserDto;
import se.lexicon.recipeassignmentapi.model.entity.RecipeAppUser;
import se.lexicon.recipeassignmentapi.repository.AppUserRepository;
import se.lexicon.recipeassignmentapi.security.RecipeUserDetails;

@Service
@Transactional
public class RecipeAppUserCrudRepositoryImpl implements RecipeAppUserCrudRepository{

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public RecipeAppUserCrudRepositoryImpl(AppUserRepository appUserRepository, BCryptPasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RecipeAppUser create(RecipeAppUserDto dto) {
        if(dto == null) throw new IllegalStateException("RecipeAppUserDto dto was null");
        if(!dto.getPassword().equals(dto.getPasswordConfirm())){
            throw new IllegalArgumentException("Provided password did not match the confirmation password");
        }
        RecipeAppUser recipeAppUser = new RecipeAppUser();
        recipeAppUser.setUsername(dto.getUsername().trim());
        recipeAppUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        recipeAppUser.setEmail(dto.getEmail().trim());
        recipeAppUser.setSuspended(false);
        return appUserRepository.save(recipeAppUser);
    }

    @Override
    public RecipeAppUser create(RecipeAppUserDto dto, AppRole appRole) {
        RecipeAppUser recipeAppUser = create(dto);
        recipeAppUser.setAppRole(appRole);
        return appUserRepository.save(recipeAppUser);
    }

    @Override
    public RecipeAppUser findById(String id) {
        return appUserRepository.findById(id)
                .orElseThrow(getException("Could not find user with id: " + id));
    }

    @Override
    public RecipeAppUser update(String id, RecipeAppUserDto form) {
        if(id == null) throw new IllegalArgumentException("id was null");
        if(form == null) throw new IllegalArgumentException("RecipeAppUser form was null");
        if(!id.equals(form.getId())){
            throw new IllegalStateException("Id didn't match form.id");
        }
        RecipeAppUser recipeAppUser = findById(id);
        var optionalByEmail = appUserRepository.findByEmailIgnoreCase(form.getEmail().trim());
        if(optionalByEmail.isPresent() && !optionalByEmail.get().getId().equals(id)){
            throw new IllegalArgumentException("Email " + form.getEmail() + " is already taken");
        }else{
            recipeAppUser.setEmail(form.getEmail().trim());
        }

        var optionalByUsername = appUserRepository.findByUsernameIgnoreCase(form.getUsername().trim());
        if(optionalByUsername.isPresent() && !optionalByUsername.get().getId().equals(id)){
            throw new IllegalArgumentException("Username " + form.getUsername() + " is taken by another");
        }else {
            recipeAppUser.setUsername(form.getUsername().trim());
        }

        if(notNullOrBlank(form.getPassword()) && notNullOrBlank(form.getNewPassword()) && notNullOrBlank(form.getPasswordConfirm())){
            if(!form.getNewPassword().equals(form.getPassword()) && form.getPasswordConfirm().equals(form.getNewPassword())){
                if(passwordEncoder.matches(form.getPassword(), recipeAppUser.getPassword())){
                    recipeAppUser.setPassword(passwordEncoder.encode(form.getNewPassword()));
                }
            }
        }
        return appUserRepository.save(recipeAppUser);
    }

    public boolean notNullOrBlank(String string){
        return string != null && !string.isBlank();
    }

    @Override
    public boolean delete(String id) {
        appUserRepository.deleteById(id);
        return !appUserRepository.existsById(id);
    }
}
