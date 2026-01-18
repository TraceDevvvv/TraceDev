package com.example.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Data Transfer Object for a list of AddressDTOs.
 * Used to encapsulate a collection of addresses for transfer, typically to the presentation layer.
 */
public class AddressListDTO {
    public List<AddressDTO> addresses;

    public AddressListDTO() {
        this.addresses = new ArrayList<>();
    }

    /**
     * Adds an AddressDTO to the list.
     * @param dto The AddressDTO to add.
     */
    public void addAddress(AddressDTO dto) {
        this.addresses.add(dto);
    }

    /**
     * Returns an unmodifiable list of addresses.
     * @return A list of AddressDTOs.
     */
    public List<AddressDTO> getAddresses() {
        return Collections.unmodifiableList(addresses);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("AddressListDTO:\n");
        if (addresses.isEmpty()) {
            sb.append("  No addresses found.");
        } else {
            for (AddressDTO dto : addresses) {
                sb.append("  ").append(dto).append("\n");
            }
        }
        return sb.toString();
    }
}