package com.example.view;

import com.example.controller.AddressController;
import com.example.model.Address;
import com.example.model.AddressDto;
import com.example.model.Result;

import java.util.List;

/**
 * Boundary class representing the user interface for address management.
 */
public class AddressUI {
    private AddressController controller;

    // Constructor with dependency injection
    public AddressUI(AddressController controller) {
        this.controller = controller;
    }

    /**
     * Displays the list of addresses.
     */
    public void displayAddressList(List<Address> addressList) {
        System.out.println("=== Address List ===");
        if (addressList.isEmpty()) {
            System.out.println("No addresses found.");
        } else {
            for (Address addr : addressList) {
                System.out.println("ID: " + addr.getId() + ", Name: " + addr.getName());
            }
        }
    }

    /**
     * Displays the new address form.
     */
    public void displayNewAddressForm() {
        System.out.println("=== New Address Form ===");
        System.out.println("Please enter address details:");
    }

    /**
     * Shows a notification message to the administrator.
     */
    public void showNotification(String message, boolean isSuccess) {
        String prefix = isSuccess ? "[SUCCESS] " : "[ERROR] ";
        System.out.println(prefix + message);
    }

    /**
     * Handles cancel operation from administrator.
     */
    public void handleCancel() {
        System.out.println("Operation cancelled by user.");
    }

    // The following methods simulate UI actions as per sequence diagram

    /**
     * Simulates administrator viewing address list.
     */
    public void simulateViewAddressList() {
        AddressListView view = controller.showAddressList();
        displayAddressList(view.getAddresses());
    }

    /**
     * Simulates administrator clicking "New address" button.
     */
    public void simulateNewAddressClick() {
        controller.showNewAddressForm();
    }

    /**
     * Simulates administrator viewing empty form.
     */
    public void simulateDisplayEmptyForm() {
        displayNewAddressForm();
    }

    /**
     * Simulates form submission with address data.
     */
    public void simulateSubmitForm(String addressName) {
        AddressDto dto = new AddressDto();
        dto.setName(addressName);
        Result result = controller.createAddress(dto);
        showNotification(result.getMessage(), result.isSuccess());
    }

    /**
     * Simulates administrator cancelling the operation.
     */
    public void simulateCancelOperation(String sessionId) {
        Result result = controller.cancelAddressCreation(sessionId);
        showNotification(result.getMessage(), result.isSuccess());
        handleCancel();
    }

    // Sequence diagram message methods
    public void clicksNewAddressButton() {
        simulateNewAddressClick();
    }

    public void returnAddressForm() {
        // This is a return message from Controller to UI
        // In the sequence diagram this is shown as returning the address form
        // Implementation handled by caller
    }

    public void displayEmptyForm() {
        simulateDisplayEmptyForm();
    }

    public void fillsAndSubmitsForm(String addressName) {
        simulateSubmitForm(addressName);
    }

    public void showSuccessNotification() {
        showNotification("Address created successfully", true);
    }

    public void showErrorNotification(String message) {
        showNotification(message, false);
    }

    public void cancelOperation() {
        handleCancel();
    }

    public void cancelRequest(String sessionId) {
        simulateCancelOperation(sessionId);
    }

    public void operationTerminated() {
        System.out.println("Operation terminated");
    }
}