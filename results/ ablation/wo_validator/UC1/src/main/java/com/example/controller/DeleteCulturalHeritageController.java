package com.example.controller;

import com.example.command.DeleteHeritageCommand;
import com.example.command.IDeleteHandler;
import com.example.domain.CulturalHeritage;
import com.example.dto.HeritageDTO;
import com.example.dto.OperationResult;
import com.example.repository.CulturalHeritageRepository;
import com.example.repository.CulturalHeritageRepositoryImpl;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for delete cultural heritage operations
 */
public class DeleteCulturalHeritageController implements IDeleteHandler {
    private boolean blockSubmission = false;
    private CulturalHeritageRepository repository;

    public DeleteCulturalHeritageController(CulturalHeritageRepository repository) {
        this.repository = repository;
    }

    /**
     * Gets list of all cultural heritage items as DTOs
     */
    public List<HeritageDTO> getHeritageList() {
        List<CulturalHeritage> heritageList = repository.findAll();
        List<HeritageDTO> dtoList = new ArrayList<>();
        
        // Convert to DTOs using repository's convertToDTO method
        CulturalHeritageRepositoryImpl repoImpl = (CulturalHeritageRepositoryImpl) repository;
        for (CulturalHeritage heritage : heritageList) {
            dtoList.add(repoImpl.convertToDTO(heritage));
        }
        
        return dtoList;
    }

    @Override
    public OperationResult executeDelete(String id) {
        return deleteHeritage(id);
    }

    /**
     * Deletes cultural heritage with given ID
     */
    public OperationResult deleteHeritage(String id) {
        // Check if operation is valid (not blocked)
        if (!isValidOperation()) {
            return new OperationResult(false, "Operation is currently blocked");
        }
        
        // Block input to prevent multiple submissions
        blockInput();
        
        try {
            // Create and execute delete command
            DeleteHeritageCommand command = new DeleteHeritageCommand(id, repository);
            OperationResult result = command.execute();
            
            return result;
        } finally {
            // Always unblock input after operation completes
            unblockInput();
        }
    }

    /**
     * Blocks input to prevent multiple submissions
     */
    public void blockInput() {
        this.blockSubmission = true;
    }

    /**
     * Unblocks input after operation completion
     */
    public void unblockInput() {
        this.blockSubmission = false;
    }

    /**
     * Validates if operation can proceed
     */
    private boolean isValidOperation() {
        return !blockSubmission;
    }
}