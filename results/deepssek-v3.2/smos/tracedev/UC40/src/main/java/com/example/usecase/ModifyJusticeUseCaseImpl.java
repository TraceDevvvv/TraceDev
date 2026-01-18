package com.example.usecase;

import com.example.entities.Justice;
import com.example.ports.JusticeRepositoryPort;
import com.example.request.ModifyJusticeRequest;
import com.example.response.ModifyJusticeResponse;
import java.util.Optional;

/**
 * Implementation of the modify justice use case.
 */
public class ModifyJusticeUseCaseImpl implements ModifyJusticeUseCase {
    private JusticeRepositoryPort justiceRepository;

    /**
     * Constructor.
     */
    public ModifyJusticeUseCaseImpl(JusticeRepositoryPort justiceRepository) {
        this.justiceRepository = justiceRepository;
    }

    /**
     * Executes the modification.
     */
    @Override
    public ModifyJusticeResponse execute(ModifyJusticeRequest request) {
        // Find the justice by ID
        Optional<Justice> justiceOpt = justiceRepository.findById(request.getJusticeId());
        if (!justiceOpt.isPresent()) {
            return createErrorResponse("Justice not found");
        }

        Justice justice = justiceOpt.get();

        // Validate input data (simplified validation)
        if (!validateInputData(request)) {
            return createErrorResponse("Invalid input data");
        }

        // Update justice entity
        justice.setDate(request.getNewDate());
        justice.setJustificationText(request.getNewJustificationText());

        // Save the updated justice
        Justice savedJustice = justiceRepository.save(justice);
        if (savedJustice != null) {
            return createSuccessResponse("Justice modified successfully");
        } else {
            return createErrorResponse("Failed to save justice");
        }
    }

    /**
     * Validate input data.
     */
    public boolean validateInputData(ModifyJusticeRequest request) {
        return request.getNewDate() != null && request.getNewJustificationText() != null;
    }

    /**
     * Create success response.
     */
    public ModifyJusticeResponse createSuccessResponse(String message) {
        return new ModifyJusticeResponse(true, message);
    }

    /**
     * Create error response.
     */
    public ModifyJusticeResponse createErrorResponse(String message) {
        return new ModifyJusticeResponse(false, message);
    }
}