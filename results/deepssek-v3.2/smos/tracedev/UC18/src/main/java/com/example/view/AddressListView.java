package com.example.view;

import com.example.model.Address;
import java.util.List;

/**
 * View model for address list display.
 */
public class AddressListView {
    private List<Address> addresses;

    public AddressListView(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Address> getAddresses() {
        return addresses;
    }
}