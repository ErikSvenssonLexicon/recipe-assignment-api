package se.lexicon.recipeassignmentapi.security;

import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTTokenValidatorFilter extends BasicAuthenticationFilter {

    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    private final JWTUtil jwtUtil;

    public JWTTokenValidatorFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader(AUTHORIZATION);
        if(token != null){
            try{
                if(!token.startsWith(BEARER)){
                    Claims claims = jwtUtil.parseClaims(token);
                    RecipeUserDetails recipeUserDetails = jwtUtil.fromClaims(claims);
                    Authentication authentication = new UsernamePasswordAuthenticationToken(
                            recipeUserDetails, null, recipeUserDetails.getAuthorities()
                    );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }catch (Exception exception){
                throw new BadCredentialsException("Invalid token received");
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/api/v1/public/auth");
    }
}
