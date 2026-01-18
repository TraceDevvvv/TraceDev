package com.example.service;

import com.example.dto.TouristDTO;
import com.example.entity.TouristEntity;
import com.example.exception.ConnectionException;
import com.example.repository.ITouristRepository;
import com.example.validation.IDataValidator;
import com.example.validation.ValidationResult;
import java.util.Date;

/**
 * Service implementation with transactional save and validation (REQ-015).
 */
public class TouristService implements ITouristService {
    private ITouristRepository touristRepository;
    private IDataValidator validator;

    public TouristService(ITouristRepository touristRepository, IDataValidator validator) {
        this.touristRepository = touristRepository;
        this.validator = validator;
    }

    @Override
    public TouristDTO loadTouristData(String userId) {
        TouristEntity entity = touristRepository.findById(userId);
        if (entity == null) {
            return null;
        }
        return new TouristDTO(entity.getUserId(), entity.getName(), entity.getEmail(), entity.getPhone(), entity.getAddress());
    }

    @Override
    public ValidationResult validateTouristData(TouristDTO data) {
        // Delegate to the validator (REQ-015)
        return validator.validate(data);
    }

    @Override
    public boolean saveTouristData(TouristDTO data) throws ConnectionException {
        // Convert DTO to Entity
        TouristEntity entity = touristRepository.findById(data.getUserId());
        if (entity == null) {
            return false;
        }
        entity.setName(data.getName());
        entity.setEmail(data.getEmail());
        entity.setPhone(data.getPhone());
        entity.setAddress(data.getAddress());
        entity.setUpdatedAt(new Date());
        // Perform transactional update (REQ-015)
        return touristRepository.update(entity);
    }

    @Override
    public boolean retry() {
        // Simple retry logic for REQ-014
        // In a real scenario, this would attempt to re-establish connection.
        System.out.println("Retrying operation...");
        return true;
    }
}