package se.lexicon.recipeassignmentapi.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.recipeassignmentapi.model.entity.RecipeAppUser;
import se.lexicon.recipeassignmentapi.repository.AppUserRepository;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class RecipeUserDetailsService implements UserDetailsService {

    private final AppUserRepository repository;

    public RecipeUserDetailsService(AppUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public RecipeUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RecipeAppUser user = repository.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("Could not find user with provided username"));

        RecipeUserDetails recipeUserDetails = new RecipeUserDetails();
        recipeUserDetails.setUserId(user.getId());
        recipeUserDetails.setUsername(user.getUsername());
        recipeUserDetails.setPassword(user.getPassword());
        recipeUserDetails.setEmail(user.getEmail());
        recipeUserDetails.setAuthorities(
                Stream.of(user.getAppRole())
                        .map(appRole -> new SimpleGrantedAuthority(appRole.name()))
                        .collect(Collectors.toSet())
        );
        recipeUserDetails.setSuspended(user.isSuspended());

        return recipeUserDetails;
    }
}
