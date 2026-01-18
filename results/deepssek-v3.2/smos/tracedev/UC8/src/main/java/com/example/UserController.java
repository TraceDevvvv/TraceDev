package com.example;

/**
 * Controller interface for user operations.
 */
public interface UserController {
    EditUserResponse handleEditUserRequest(EditUserRequest request);
}