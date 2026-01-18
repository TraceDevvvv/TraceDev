package smos.presentation;

import smos.system.Address;
import java.util.List;

/**
 * Response object for address deletion operation.
 * Satisfies requirement REQ-004.
 */
public class DeleteAddressResponse {
    private boolean success;
    private String message;
    private List<Address> updatedAddresses;

    public DeleteAddressResponse(boolean success, String message, List<Address> updatedAddresses) {
        this.success = success;
        this.message = message;
        this.updatedAddresses = updatedAddresses;
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

    public List<Address> getUpdatedAddresses() {
        return updatedAddresses;
    }

    public void setUpdatedAddresses(List<Address> updatedAddresses) {
        this.updatedAddresses = updatedAddresses;
    }
}