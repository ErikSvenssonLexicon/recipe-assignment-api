package se.lexicon.recipeassignmentapi.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

public class RecipeUserDetails implements UserDetails, Serializable {

    private String userId;
    private String username;
    private String password;
    private String email;
    private Collection<GrantedAuthority> authorities;
    private boolean notSuspended;

    public RecipeUserDetails(String userId, String username, String password, String email, Collection<GrantedAuthority> authorities, boolean notSuspended) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
        this.notSuspended = notSuspended;
    }

    public RecipeUserDetails() {
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setNotSuspended(boolean notSuspended) {
        this.notSuspended = notSuspended;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !notSuspended;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !notSuspended;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !notSuspended;
    }

    @Override
    public boolean isEnabled() {
        return !notSuspended;
    }
}
