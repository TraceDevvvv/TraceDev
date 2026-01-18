package com.example.usecase;

import com.example.dto.LoginRequest;
import com.example.dto.LoginResponse;

/**
 * Input Port / Use Case Interactor interface.
 */
public interface LoginUseCase {
    LoginResponse execute(LoginRequest request);
}