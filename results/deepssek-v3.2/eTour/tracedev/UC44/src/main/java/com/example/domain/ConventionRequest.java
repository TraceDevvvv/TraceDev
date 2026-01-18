package com.example.domain;

import java.util.Date;

public class ConventionRequest {
    private String requestId;
    private String restaurantId;
    private ConventionData conventionData;
    private RequestStatus status;

    private ConventionRequest(String requestId, String restaurantId, ConventionData conventionData, RequestStatus status) {
        this.requestId = requestId;
        this.restaurantId = restaurantId;
        this.conventionData = conventionData;
        this.status = status;
    }

    public static ConventionRequest create(String restaurantId, ConventionData data) {
        String requestId = "REQ-" + System.currentTimeMillis() + "-" + restaurantId;
        return new ConventionRequest(requestId, restaurantId, data, RequestStatus.DRAFT);
    }

    public void submit() {
        if (this.status != RequestStatus.DRAFT) {
            throw new IllegalStateException("Cannot submit request that is not in DRAFT state.");
        }
        this.status = RequestStatus.PENDING_VALIDATION;
    }

    public void confirm() {
        if (this.status != RequestStatus.PENDING_VALIDATION) {
            throw new IllegalStateException("Cannot confirm request that is not in PENDING_VALIDATION state.");
        }
        this.status = RequestStatus.AWAITING_CONFIRMATION;
    }

    public void markAsSent() {
        if (this.status != RequestStatus.AWAITING_CONFIRMATION) {
            throw new IllegalStateException("Cannot mark as sent request that is not in AWAITING_CONFIRMATION state.");
        }
        this.status = RequestStatus.SENT;
    }

    public void cancel() {
        if (this.status == RequestStatus.SENT) {
            throw new IllegalStateException("Cannot cancel request that is already SENT.");
        }
        this.status = RequestStatus.CANCELLED;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public ConventionData getConventionData() {
        return conventionData;
    }

    public RequestStatus getStatus() {
        return status;
    }
}