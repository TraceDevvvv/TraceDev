package com.example.service;

import com.example.dto.RegisterDto;
import com.example.exception.ConnectionException;

/**
 * Service interface for register operations.
 */
public interface RegisterService {
    RegisterDto getClassRegister(String classId) throws ConnectionException;
}