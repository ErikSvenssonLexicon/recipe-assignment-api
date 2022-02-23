package se.lexicon.recipeassignmentapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JWTUtil {

    public static final String USER_ID = "userId";
    public static final String EMAIL = "email";
    public static final String AUTHORITIES = "authorities";
    public static final String SUSPENDED = "suspended";
    private final String secret;

    public JWTUtil() throws IOException{
        BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("assets/key.properties"));
        Properties properties = new Properties();
        properties.load(bufferedReader);
        secret = properties.getProperty("secret");
    }

    public Claims parseClaims(String token){
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public RecipeUserDetails fromClaims(Claims claims){
        String userId = claims.get(USER_ID, String.class);
        String username = claims.getSubject();
        String email = claims.get(EMAIL, String.class);
        String authorities = claims.get(AUTHORITIES, String.class);
        Boolean suspended = claims.get(SUSPENDED, Boolean.class);
        Collection<GrantedAuthority> grantedAuthorities = Arrays.stream(authorities.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

        return new RecipeUserDetails(
                userId, username, null, email, grantedAuthorities, suspended
        );
    }

    public String buildToken(RecipeUserDetails userDetails){
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setIssuer("recipeassignmentapi")
                .setHeaderParam("typ", "JWT")
                .setSubject(userDetails.getUsername())
                .claim(USER_ID, userDetails.getUserId())
                .claim(EMAIL, userDetails.getEmail())
                .claim(AUTHORITIES, getAuthorityString(userDetails.getAuthorities()))
                .claim(SUSPENDED, userDetails.isEnabled())
                .setIssuedAt(new Date())
                .setExpiration(new Date(
                        System.currentTimeMillis() + (3_600_000 * 24)
                ))
                .signWith(key, SignatureAlgorithm.HS512).compact();
    }

    private String getAuthorityString(Collection<? extends GrantedAuthority> authorities) {
        Set<String> authoritySet = new HashSet<>();
        for(GrantedAuthority authority : authorities){
            authoritySet.add(authority.getAuthority());
        }
        return String.join(",", authoritySet);
    }


}
