package com.example.insertdelayadmin.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Utility class for JSON Web Token (JWT) operations.
 * This class handles the creation, parsing, and validation of JWT tokens
 * used for authentication in the application.
 */
@Component
public class JwtUtil {

    // Secret key for signing JWT tokens. Loaded from application properties.
    // In a production environment, this should be a strong, randomly generated key
    // and securely stored (e.g., environment variable, key vault).
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    // Token expiration time in milliseconds (e.g., 10 hours)
    @Value("${jwt.expiration}")
    private long EXPIRATION_TIME;

    /**
     * Extracts the username from a JWT token.
     *
     * @param token The JWT token string.
     * @return The username (subject) extracted from the token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts a specific claim from a JWT token using a claims resolver function.
     *
     * @param token The JWT token string.
     * @param claimsResolver A function to resolve the desired claim from the Claims object.
     * @param <T> The type of the claim to be extracted.
     * @return The extracted claim.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extracts all claims from a JWT token.
     *
     * @param token The JWT token string.
     * @return A {@link Claims} object containing all claims from the token.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Checks if a JWT token is expired.
     *
     * @param token The JWT token string.
     * @return true if the token's expiration date is before the current date, false otherwise.
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extracts the expiration date from a JWT token.
     *
     * @param token The JWT token string.
     * @return The expiration {@link Date} of the token.
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Generates a JWT token for a given username and role.
     *
     * @param username The username (subject) for the token.
     * @param role The role of the user to be included as a claim.
     * @return The generated JWT token string.
     */
    public String generateToken(String username, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role); // Add role as a custom claim
        return createToken(claims, username);
    }

    /**
     * Creates the JWT token with specified claims, subject, and expiration.
     *
     * @param claims Custom claims to be included in the token.
     * @param subject The subject of the token (typically the username).
     * @return The JWT token string.
     */
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Token valid for EXPIRATION_TIME
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Validates a JWT token against user details.
     * Checks if the username in the token matches the user details' username
     * and if the token is not expired.
     *
     * @param token The JWT token string.
     * @param userDetails The {@link UserDetails} object to validate against.
     * @return true if the token is valid for the given user, false otherwise.
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Validates a JWT token for general validity (not expired, correctly signed).
     * This method can be used for initial token validation before loading user details.
     *
     * @param token The JWT token string.
     * @return true if the token is valid and not expired, false otherwise.
     */
    public Boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return !isTokenExpired(token);
        } catch (Exception e) {
            // Log the exception for debugging (e.g., SignatureException, ExpiredJwtException)
            // logger.error("JWT validation error: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves the signing key from the base64 encoded secret.
     *
     * @return The {@link Key} used for signing and verifying JWT tokens.
     */
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}