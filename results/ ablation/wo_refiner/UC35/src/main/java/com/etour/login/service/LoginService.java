package com.etour.login.service;

import com.etour.login.dto.LoginRequestDTO;
import com.etour.login.dto.LoginResponseDTO;
import com.etour.login.exception.ETOURConnectionException;

/**
 * Service interface for authentication operations.
 */
public interface LoginService {
    /**
     * Authenticates user credentials.
     *
     * @param credentials the login request DTO
     * @return login response DTO
     * @throws ETOURConnectionException if there is a connection error
     */
    LoginResponseDTO authenticate(LoginRequestDTO credentials) throws ETOURConnectionException;
}