package com.example.application;

import com.example.dataaccess.IAddressRepository;
import com.example.domain.Address;
import com.example.dto.AddressDTO;
import com.example.dto.AddressListDTO;
import com.example.exceptions.DataAccessException;
import com.example.exceptions.ServiceException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service layer for Address management.
 * Contains business logic and orchestrates data retrieval from the repository.
 * It transforms domain entities into DTOs suitable for the presentation layer.
 */
public class AddressService {
    private IAddressRepository addressRepository;

    /**
     * Constructs an AddressService with a given IAddressRepository.
     * @param addressRepository The repository to use for address data access.
     */
    public AddressService(IAddressRepository addressRepository) {
        this.addressRepository = addressRepository;
        System.out.println("[AddressService] Initialized with AddressRepository.");
    }

    /**
     * Retrieves all addresses from the system and packages them into an AddressListDTO.
     * Handles potential DataAccessException from the repository and rethrows it as a ServiceException.
     *
     * @return An AddressListDTO containing all available addresses.
     * @throws ServiceException If there's an issue retrieving or processing addresses.
     */
    public List<AddressDTO> getAllAddresses() throws ServiceException {
        System.out.println("[AddressService] Request to get all addresses.");
        try {
            List<Address> addresses = addressRepository.findAll();
            System.out.println("[AddressService] Retrieved " + addresses.size() + " domain addresses. Mapping to DTOs.");
            return addresses.stream()
                    .map(AddressDTO::new) // Map each Address domain entity to an AddressDTO
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            // Translate DataAccessException to ServiceException for the presentation layer
            System.err.println("[AddressService] Data access failed: " + e.getMessage());
            throw new ServiceException("Failed to retrieve addresses: " + e.getMessage(), e);
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            System.err.println("[AddressService] Unexpected error in service layer: " + e.getMessage());
            throw new ServiceException("An unexpected error occurred while getting all addresses.", e);
        }
    }
}