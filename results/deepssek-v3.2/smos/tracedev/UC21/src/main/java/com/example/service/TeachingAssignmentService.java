package com.example.service;

import com.example.model.*;
import com.example.repository.*;
import com.example.connector.SMOSServerConnector;
import java.util.*;

/**
 * Service layer that handles business logic for teaching assignment.
 * Based on the UML class diagram and sequence diagram interactions.
 */
public class TeachingAssignmentService {
    private AddressRepository addressRepository;
    private TeachingRepository teachingRepository;
    private AddressTeachingRepository addressTeachingRepository;
    private SMOSServerConnector connector;

    public TeachingAssignmentService(AddressRepository addressRepository,
                                     TeachingRepository teachingRepository,
                                     AddressTeachingRepository addressTeachingRepository,
                                     SMOSServerConnector connector) {
        this.addressRepository = addressRepository;
        this.teachingRepository = teachingRepository;
        this.addressTeachingRepository = addressTeachingRepository;
        this.connector = connector;
    }

    public List<Teaching> loadAddressTeachings(String addressId) {
        // Check connection first (as per sequence diagram)
        if (!connector.checkConnection()) {
            handleConnectionLoss();
            return Collections.emptyList();
        }
        // Retrieve address teaching associations
        List<AddressTeaching> associations = addressTeachingRepository.findByAddressId(addressId);
        List<Teaching> teachings = new ArrayList<>();
        for (AddressTeaching assoc : associations) {
            Teaching teaching = teachingRepository.findById(assoc.getTeachingId());
            if (teaching != null) {
                teachings.add(teaching);
            }
        }
        return teachings;
    }

    public List<Teaching> loadAvailableTeachings() {
        // Check connection
        if (!connector.checkConnection()) {
            handleConnectionLoss();
            return Collections.emptyList();
        }
        return teachingRepository.findAll();
    }

    public boolean associateTeachings(String addressId, List<String> teachingIds) {
        // Check connection
        if (!connector.checkConnection()) {
            handleConnectionLoss();
            return false;
        }
        boolean allSuccess = true;
        for (String teachingId : teachingIds) {
            AddressTeaching association = new AddressTeaching(addressId, teachingId, new Date());
            boolean saved = addressTeachingRepository.save(association);
            if (!saved) {
                allSuccess = false;
            }
        }
        return allSuccess;
    }

    public boolean removeTeachings(String addressId, List<String> teachingIds) {
        // Check connection
        if (!connector.checkConnection()) {
            handleConnectionLoss();
            return false;
        }
        boolean allSuccess = true;
        for (String teachingId : teachingIds) {
            // Find the association to delete (simplified: we assume we can construct it)
            AddressTeaching association = new AddressTeaching(addressId, teachingId, null);
            boolean deleted = addressTeachingRepository.delete(association);
            if (!deleted) {
                allSuccess = false;
            }
        }
        return allSuccess;
    }

    public void handleConnectionLoss() {
        System.out.println("Handling connection loss...");
        connector.handleConnectionError();
    }

    // Helper method to get address (used by controller)
    public Address getAddress(String addressId) {
        return addressRepository.findById(addressId);
    }

    // Getters for repositories (optional)
    public AddressRepository getAddressRepository() { return addressRepository; }
    public TeachingRepository getTeachingRepository() { return teachingRepository; }
    public AddressTeachingRepository getAddressTeachingRepository() { return addressTeachingRepository; }
    public SMOSServerConnector getConnector() { return connector; }
}