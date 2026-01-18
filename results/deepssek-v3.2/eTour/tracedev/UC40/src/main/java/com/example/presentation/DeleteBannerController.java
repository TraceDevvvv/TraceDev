
package com.example.presentation;

import com.example.application.DeleteBannerResult;
import com.example.application.DeleteBannerUseCase;
import com.example.domain.Banner;

import java.util.List;

/**
 * REST controller handling banner deletion requests.
 */
public class DeleteBannerController {
    private final DeleteBannerUseCase deleteBannerUseCase;

    public DeleteBannerController(DeleteBannerUseCase deleteBannerUseCase) {
        this.deleteBannerUseCase = deleteBannerUseCase;
    }

    /**
     * GET endpoint to list banners for a point of rest (Flow of Events: 2).
     */
    public List<Banner> getBanners(
            String pointOfRestId,
            String authToken) {
        // Auth check performed in use case
        List<Banner> banners = deleteBannerUseCase.getBannersForPointOfRest(pointOfRestId);
        return banners;
    }

    /**
     * DELETE endpoint to handle banner deletion.
     */
    public DeleteBannerResponse handleDeleteRequest(
            DeleteBannerRequest request) {
        // Execute deletion use case
        DeleteBannerResult result = deleteBannerUseCase.execute(
                request.getPointOfRestId(),
                request.getBannerId(),
                request.getOperatorId(),
                request.getAuthToken()
        );

        // Map result to HTTP response
        if (result.isSuccess()) {
            DeleteBannerResponse response = new DeleteBannerResponse(true, result.getMessage());
            return response;
        } else {
            DeleteBannerResponse response = new DeleteBannerResponse(false, result.getMessage());
            return response;
        }
    }

    /**
     * Helper to map error messages to HTTP status codes.
     */
    private String determineHttpStatus(String message) {
        if (message.contains("Unauthorized")) {
            return "UNAUTHORIZED";
        } else if (message.contains("not found")) {
            return "NOT_FOUND";
        } else if (message.contains("Invalid")) {
            return "BAD_REQUEST";
        } else {
            return "INTERNAL_SERVER_ERROR";
        }
    }

    /**
     * New endpoint to initiate delete (from sequence diagram message m14).
     */
    public DeleteBannerResponse initiateDelete(
            String pointOfRestId,
            String bannerId,
            String authToken) {
        // This method corresponds to sequence message: 13. initiateDelete(bannerId, pointOfRestId)
        // For simplicity, we call the same execution as delete but return a confirmation dialog response.
        DeleteBannerResult result = deleteBannerUseCase.execute(
                pointOfRestId,
                bannerId,
                "", // operatorId is not used in this flow
                authToken
        );
        if (result.isSuccess()) {
            // Show confirmation dialog (sequence message m35)
            DeleteBannerResponse response = new DeleteBannerResponse(false, "Confirm deletion?");
            return response;
        } else {
            // Return error
            DeleteBannerResponse response = new DeleteBannerResponse(false, result.getMessage());
            return response;
        }
    }

    /**
     * Cancel operation (from sequence diagram).
     */
    public DeleteBannerResponse cancelOperation(
            String pointOfRestId,
            String bannerId) {
        DeleteBannerResult result = deleteBannerUseCase.cancel();
        DeleteBannerResponse response = new DeleteBannerResponse(false, result.getMessage());
        return response;
    }

    public static class DeleteBannerRequest {
        private String pointOfRestId;
        private String bannerId;
        private String operatorId;
        private String authToken;

        public String getPointOfRestId() {
            return pointOfRestId;
        }

        public void setPointOfRestId(String pointOfRestId) {
            this.pointOfRestId = pointOfRestId;
        }

        public String getBannerId() {
            return bannerId;
        }

        public void setBannerId(String bannerId) {
            this.bannerId = bannerId;
        }

        public String getOperatorId() {
            return operatorId;
        }

        public void setOperatorId(String operatorId) {
            this.operatorId = operatorId;
        }

        public String getAuthToken() {
            return authToken;
        }

        public void setAuthToken(String authToken) {
            this.authToken = authToken;
        }
    }

    public static class DeleteBannerResponse {
        private boolean success;
        private String message;

        public DeleteBannerResponse(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
