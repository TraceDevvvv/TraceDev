package com.example.repository;

import java.util.HashSet;
import java.util.Set;

/**
 * Simulated Redis token repository.
 */
public class RedisTokenRepository implements TokenRepository {
    // In-memory set simulating Redis (assumption: real Redis client in production)
    private Set<String> blacklist = new HashSet<>();
    private RedisClient redisClient;

    public RedisTokenRepository(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    @Override
    public void addToBlacklist(String token) {
        blacklist.add(token);
    }

    @Override
    public boolean isBlacklisted(String token) {
        return blacklist.contains(token);
    }

    /**
     * Sets an expiration time for the token in the blacklist.
     * @param token The token.
     * @param ttl Time to live in seconds (simulated).
     */
    public void setExpiration(String token, int ttl) {
        // In a real Redis implementation, we would set TTL.
        // Here we just add to the set; expiration is not simulated.
        addToBlacklist(token);
    }
}