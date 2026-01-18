package com.tourist.app.handlers;

import com.tourist.app.commands.UpdateTouristCommand;
import com.tourist.app.dtos.TouristDto;
import com.tourist.app.interfaces.ICommandHandler;
import com.tourist.app.interfaces.ITouristRepository;
import com.tourist.domain.Tourist;

/**
 * Handler for UpdateTouristCommand.
 */
public class UpdateTouristCommandHandler implements ICommandHandler<UpdateTouristCommand> {
    private ITouristRepository touristRepository;

    /**
     * Constructor.
     * @param repo the tourist repository
     */
    public UpdateTouristCommandHandler(ITouristRepository repo) {
        this.touristRepository = repo;
    }

    @Override
    public void Handle(UpdateTouristCommand command) {
        // REQ-Q-001: Response time constraint - ensure operation completes within 5 seconds.
        long startTime = System.currentTimeMillis();

        // Retrieve the tourist from repository.
        Tourist tourist = touristRepository.GetById(command.getTouristId());
        if (tourist == null) {
            throw new IllegalArgumentException("Tourist not found with id: " + command.getTouristId());
        }

        // Validate the tourist data.
        if (!tourist.validate()) {
            // The sequence diagram shows a "Throw ValidationException" message.
            throw new ValidationException("Tourist data is invalid.");
        }

        // Request confirmation (internal step as per sequence diagram).
        boolean confirmed = RequestConfirmation();
        if (!confirmed) {
            // If not confirmed, abort the update (sequence diagram has "Cancels form submission").
            return;
        }

        // Update the tourist profile.
        TouristDto updatedData = command.getUpdatedData();
        tourist.updateProfile(updatedData.getName(), updatedData.getEmail(), updatedData.getPhoneNumber());

        // Save the changes.
        touristRepository.Update(tourist);

        long endTime = System.currentTimeMillis();
        if ((endTime - startTime) > 5000) {
            // Log warning if response time exceeds 5 seconds.
            System.err.println("Warning: UpdateTouristCommandHandler took more than 5 seconds.");
        }
    }

    /**
     * Internal method to request confirmation.
     * @return true if confirmed, false otherwise
     */
    private boolean RequestConfirmation() {
        // In a real application, this might involve user interaction via UI.
        // The sequence diagram shows "System asks for confirmation" and "Tourist confirms in UI".
        // This is an internal step; we assume confirmation is granted for now.
        return true;
    }

    /**
     * Custom exception for validation failures.
     */
    public static class ValidationException extends RuntimeException {
        public ValidationException(String message) {
            super(message);
        }
    }
}