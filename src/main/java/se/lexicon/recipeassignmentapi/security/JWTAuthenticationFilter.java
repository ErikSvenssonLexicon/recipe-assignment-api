package se.lexicon.recipeassignmentapi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/api/v1/public/auth");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        LoginForm loginForm;
        try{
            loginForm = new ObjectMapper().readValue(request.getInputStream(), LoginForm.class);
        }catch (Exception e){
            throw new AuthenticationCredentialsNotFoundException("Missing credentials");
        }

        if(loginForm != null && loginForm.getUsername() != null && loginForm.getPassword() != null){
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginForm.getUsername(), loginForm.getPassword()
                    )
            );
        }else {
            throw new BadCredentialsException("Bad credentials");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        RecipeUserDetails recipeUserDetails = (RecipeUserDetails) authResult.getPrincipal();
        if(recipeUserDetails != null){
            String token = jwtUtil.buildToken(recipeUserDetails);
            Map<String, String> body = new HashMap<>();
            body.put("accessToken", token);
            ResponseEntity<Map<String, String>> responseEntity = ResponseEntity.ok(body);
            ObjectMapper objectMapper = new ObjectMapper();
            response.getWriter().write(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseEntity));
        }
    }
}
