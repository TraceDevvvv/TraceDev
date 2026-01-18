package com.example.service;

import com.example.model.Address;
import com.example.model.Teaching;
import com.example.repository.IAddressRepository;
import com.example.repository.ITeachingRepository;
import com.example.uow.IUnitOfWork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service responsible for managing the association of teachings with addresses.
 * It orchestrates interactions between repositories and the Unit of Work.
 */
public class AddressTeachingManagementService {
    private final IAddressRepository addressRepository;
    private final ITeachingRepository teachingRepository;
    private final IUnitOfWork unitOfWork;

    /**
     * Constructs an AddressTeachingManagementService with necessary repositories and Unit of Work.
     *
     * @param addressRepo  Repository for Address entities.
     * @param teachingRepo Repository for Teaching entities.
     * @param uow          Unit of Work for transaction management.
     */
    public AddressTeachingManagementService(IAddressRepository addressRepo, ITeachingRepository teachingRepo, IUnitOfWork uow) {
        this.addressRepository = addressRepo;
        this.teachingRepository = teachingRepo;
        this.unitOfWork = uow;
    }

    /**
     * Retrieves the current teachings associated with an address and all available teachings.
     * This is typically used to prepare a form for managing teachings.
     *
     * @param addressId The ID of the address.
     * @return A Map containing the Address object and a list of all available Teachings.
     * @throws IllegalArgumentException if the address is not found.
     */
    public Map<String, Object> getTeachingsForAddress(String addressId) {
        System.out.println("Teaching Management Service: Getting teachings for address ID: " + addressId);

        // 1. Retrieve the specific address
        Address address = addressRepository.findById(addressId);
        if (address == null) {
            throw new IllegalArgumentException("Address not found with ID: " + addressId);
        }
        System.out.println("Teaching Management Service: Found address: " + address.getName());

        // 2. Retrieve all available teachings
        List<Teaching> allTeachings = teachingRepository.findAll();
        System.out.println("Teaching Management Service: Found " + allTeachings.size() + " total teachings.");

        // Prepare the response map
        Map<String, Object> result = new HashMap<>();
        result.put("address", address);
        result.put("allTeachings", allTeachings);
        return result;
    }

    /**
     * Assigns or removes teachings from a specific address based on a list of selected teaching IDs.
     * This operation is performed within a Unit of Work to ensure atomicity.
     *
     * @param addressId         The ID of the address to update.
     * @param selectedTeachingIds A list of IDs of teachings that should be associated with the address.
     *                            Teachings not in this list but currently associated will be removed.
     *                            Teachings in this list but not currently associated will be added.
     * @throws IllegalArgumentException if the address or any selected teaching is not found.
     */
    public void assignOrRemoveTeachings(String addressId, List<String> selectedTeachingIds) {
        System.out.println("Teaching Management Service: Assigning/Removing teachings for address ID: " + addressId +
                           " with selected IDs: " + selectedTeachingIds);
        unitOfWork.begin(); // Start transaction

        try {
            // 1. Fetch the address (likely with a lock for update in a real DB scenario)
            Address address = addressRepository.findById(addressId);
            if (address == null) {
                throw new IllegalArgumentException("Address not found with ID: " + addressId);
            }
            System.out.println("Teaching Management Service: Retrieved address for update: " + address.getName());

            // 2. Fetch all Teaching objects corresponding to the selectedTeachingIds
            List<Teaching> newTeachingsForAddress = new ArrayList<>();
            if (selectedTeachingIds != null && !selectedTeachingIds.isEmpty()) {
                for (String teachingId : selectedTeachingIds) {
                    Teaching teaching = teachingRepository.findById(teachingId);
                    if (teaching == null) {
                        throw new IllegalArgumentException("Teaching not found with ID: " + teachingId);
                    }
                    newTeachingsForAddress.add(teaching);
                }
            }
            System.out.println("Teaching Management Service: Prepared new teaching list: " +
                               newTeachingsForAddress.stream().map(Teaching::getTitle).collect(Collectors.joining(", ")));

            // 3. Update the Address entity's teachings
            address.updateTeachings(newTeachingsForAddress);

            // 4. Save the updated Address
            addressRepository.save(address);

            unitOfWork.commit(); // Commit transaction on success
            System.out.println("Teaching Management Service: Teachings for address ID " + addressId + " updated successfully.");
        } catch (Exception e) {
            unitOfWork.rollback(); // Rollback transaction on error
            System.err.println("Teaching Management Service: Failed to assign/remove teachings: " + e.getMessage());
            throw e; // Re-throw the exception after rollback
        }
    }
}