package com.example;

import java.util.List;
import java.util.ArrayList;

/**
 * ViewModel for the address list view.
 * Using a simple List as ObservableList for simplicity.
 */
public class AddressListViewModel {
    private AddressService addressService;
    private List<Address> addresses; // Simulating ObservableList

    // Constructor
    public AddressListViewModel(AddressService addressService) {
        this.addressService = addressService;
        this.addresses = new ArrayList<>();
    }

    /**
     * Loads addresses via the controller (which uses the service).
     * In this implementation, we directly call the service for simplicity.
     * According to sequence diagram, this method is called by the View.
     */
    public void loadAddresses() {
        // In real scenario, this would call the controller, but here we simulate
        // the flow by calling service directly for brevity.
        // The controller is used in the sequence diagram; we assume the ViewModel
        // has a reference to the controller or service.
        addresses = addressService.getAllAddresses();
        System.out.println("AddressListViewModel: Addresses loaded.");
    }

    public List<Address> getAddresses() {
        return addresses;
    }
}