package com.example.controller;

import com.example.dto.RegisterDto;
import com.example.dto.RegisterViewDto;
import com.example.service.RegisterService;
import com.example.exception.ConnectionException;

/**
 * Controller for class register operations.
 */
public class ClassRegisterController {
    private RegisterService registerService;

    public ClassRegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    public RegisterService getRegisterService() {
        return registerService;
    }

    public void setRegisterService(RegisterService registerService) {
        this.registerService = registerService;
    }

    /**
     * Retrieves the register view for a given class.
     * As per sequence diagram: viewClassRegister(classId)
     */
    public RegisterViewDto viewClassRegister(String classId) throws ConnectionException {
        // Delegate to service to get RegisterDto
        RegisterDto dto = registerService.getClassRegister(classId);
        // Convert to view DTO using static method
        return RegisterViewDto.fromRegisterDto(dto);
    }
}