
package com.example.presentation.controller;

import com.example.application.usecase.InsertJustificationUseCase;
import com.example.application.command.InsertJustificationCommand;
import com.example.application.result.JustificationResult;
import com.example.presentation.dto.InsertJustificationDTO;
import com.example.presentation.dto.JustificationResponseDTO;
import java.net.http.HttpResponse;
import java.util.UUID;

/**
 * REST Controller for justification operations.
 */

public class JustificationController {
    private InsertJustificationUseCase insertUseCase;

    public JustificationController(InsertJustificationUseCase insertUseCase) {
        this.insertUseCase = insertUseCase;
    }

    public HttpResponse<JustificationResponseDTO> insertJustification(InsertJustificationDTO dto) {
        // Assumption: administrator ID is obtained from security context; here using a dummy ID.
        UUID administratorId = UUID.fromString("333e4567-e89b-12d3-a456-426614174000");
        InsertJustificationCommand command = new InsertJustificationCommand(
                dto.getDate(), dto.getReason(), dto.getAbsenceId(), administratorId);
        JustificationResult result = insertUseCase.execute(command);

        if (result.isSuccess()) {
            JustificationResponseDTO response = new JustificationResponseDTO(
                    true, result.getJustificationId(), result.getMessage());
            return new HttpResponse<>(201, response);
        } else {
            // Determine appropriate status code based on error message
            int status = 400;
            if (result.getMessage().contains("not found")) {
                status = 404;
            } else if (result.getMessage().contains("Failed to save")) {
                status = 500;
            }
            JustificationResponseDTO response = new JustificationResponseDTO(
                    false, null, result.getMessage());
            return new HttpResponse<>(status, response);
        }
    }
    
    // Simple HttpResponse wrapper to replace ResponseEntity
    public static class HttpResponse<T> {
        private int statusCode;
        private T body;
        
        public HttpResponse(int statusCode, T body) {
            this.statusCode = statusCode;
            this.body = body;
        }
        
        public int getStatusCode() {
            return statusCode;
        }
        
        public T getBody() {
            return body;
        }
    }
}
