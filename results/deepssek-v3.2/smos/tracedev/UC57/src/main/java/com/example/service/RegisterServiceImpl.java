package com.example.service;

import com.example.dto.RegisterDto;
import com.example.repository.RegisterRepository;
import com.example.model.SmosRegisterData;
import com.example.exception.ConnectionException;

/**
 * Service implementation for register operations.
 */
public class RegisterServiceImpl implements RegisterService {
    private RegisterRepository registerRepository;

    public RegisterServiceImpl(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    public RegisterRepository getRegisterRepository() {
        return registerRepository;
    }

    public void setRegisterRepository(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    @Override
    public RegisterDto getClassRegister(String classId) throws ConnectionException {
        // Fetch raw data from repository
        SmosRegisterData smosData = registerRepository.fetchRegisterData(classId);
        // Convert to DTO using the conversion method
        return smosData.toRegisterDto();
    }
}