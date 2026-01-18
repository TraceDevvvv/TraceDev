package com.example.etour;

/**
 * Data Transfer Object for authentication result.
 */
public class AuthResultDTO {
    public Boolean success;
    public String message;
    public String token;
    public UserDTO user;
}