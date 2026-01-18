// TeachingManagementService.java
package com.example.teachingsystem;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service class responsible for managing the assignment and removal of teachings from addresses.
 * This class simulates the system's backend logic for the "AssignRemoveTeachings" use case.
 */
public class TeachingManagementService {

    // Simulates a database or repository for addresses and teachings
    private final Map<String, Address> addressRepository;
    private final Map<String, Teaching> teachingRepository;
    private Administrator loggedInAdministrator; // Simulates logged-in state

    /**
     * Constructs a new TeachingManagementService.
     * Initializes repositories with some sample data.
     */
    public TeachingManagementService() {
        this.addressRepository = new HashMap<>();
        this.teachingRepository = new HashMap<>();
        this.loggedInAdministrator = null; // No admin logged in initially

        // Initialize with sample teachings
        Teaching math = new Teaching("T001", "Mathematics");
        Teaching physics = new Teaching("T002", "Physics");
        Teaching chemistry = new Teaching("T003", "Chemistry");
        Teaching literature = new Teaching("T004", "Literature");
        Teaching history = new Teaching("T005", "History");

        teachingRepository.put(math.getTeachingId(), math);
        teachingRepository.put(physics.getTeachingId(), physics);
        teachingRepository.put(chemistry.getTeachingId(), chemistry);
        teachingRepository.put(literature.getTeachingId(), literature);
        teachingRepository.put(history.getTeachingId(), history);

        // Initialize with sample addresses and some pre-assigned teachings
        Address mainCampus = new Address("A001", "Main Campus");
        mainCampus.addTeaching(math);
        mainCampus.addTeaching(physics);
        addressRepository.put(mainCampus.getAddressId(), mainCampus);

        Address branchOfficeA = new Address("A002", "Branch Office A");
        branchOfficeA.addTeaching(chemistry);
        addressRepository.put(branchOfficeA.getAddressId(), branchOfficeA);

        Address downtownCenter = new Address("A003", "Downtown Center");
        addressRepository.put(downtownCenter.getAddressId(), downtownCenter);
    }

    /**
     * Simulates an administrator logging into the system.
     * This fulfills the "user is logged in to the system as an administrator" precondition.
     *
     * @param adminId The ID of the administrator.
     * @param password The password of the administrator (for simulation, any non-empty string works).
     * @return true if login is successful, false otherwise.
     */
    public boolean loginAdministrator(String adminId, String password) {
        // In a real system, this would involve authentication against a user database.
        // For this simulation, we'll just create a dummy admin if credentials are provided.
        if (adminId != null && !adminId.trim().isEmpty() && password != null && !password.trim().isEmpty()) {
            this.loggedInAdministrator = new Administrator(adminId, "AdminUser_" + adminId);
            System.out.println("Administrator '" + loggedInAdministrator.getUsername() + "' logged in successfully.");
            return true;
        }
        System.out.println("Administrator login failed. Invalid credentials.");
        return false;
    }

    /**
     * Checks if an administrator is currently logged in.
     *
     * @return true if an administrator is logged in, false otherwise.
     */
    public boolean isAdminLoggedIn() {
        return loggedInAdministrator != null;
    }

    /**
     * Simulates the "viewdettaglizzizzo" use case and displaying detailed information of an address.
     * This fulfills the "user has made the case of use 'viewdettaglizzizzo'" and
     * "user displays the detailed information of an address" preconditions.
     *
     * @param addressId The ID of the address to view.
     * @return The Address object if found, null otherwise.
     */
    public Address viewAddressDetails(String addressId) {
        if (!isAdminLoggedIn()) {
            System.out.println("Error: Administrator not logged in. Cannot view address details.");
            return null;
        }
        Address address = addressRepository.get(addressId);
        if (address == null) {
            System.out.println("Error: Address with ID '" + addressId + "' not found.");
        } else {
            System.out.println("Displaying details for: " + address.getName() + " (ID: " + address.getAddressId() + ")");
            System.out.println("Currently associated teachings: " +
                               address.getAssociatedTeachings().stream()
                                       .map(Teaching::getName)
                                       .collect(Collectors.joining(", ")));
        }
        return address;
    }

    /**
     * Retrieves all available teachings in the system.
     * This is part of displaying the form from which an admin can add/remove lessons.
     *
     * @return An unmodifiable set of all available Teaching objects.
     */
    public Set<Teaching> getAllAvailableTeachings() {
        if (!isAdminLoggedIn()) {
            System.out.println("Error: Administrator not logged in. Cannot retrieve available teachings.");
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(new HashSet<>(teachingRepository.values()));
    }

    /**
     * Displays a simulated form for assigning/removing teachings for a given address.
     * This corresponds to System Event 1: "Displays a form from which you can add and / or remove lessons at the address."
     *
     * @param addressId The ID of the address for which to display the form.
     * @return A map containing the address, its current teachings, and all available teachings, or null if preconditions fail.
     */
    public Map<String, Object> displayAssignmentForm(String addressId) {
        if (!isAdminLoggedIn()) {
            System.out.println("Error: Administrator not logged in. Cannot display form.");
            return null;
        }

        Address targetAddress = addressRepository.get(addressId);
        if (targetAddress == null) {
            System.out.println("Error: Address with ID '" + addressId + "' not found. Cannot display form.");
            return null;
        }

        System.out.println("\n--- Assignment/Removal Form for Address: " + targetAddress.getName() + " ---");
        System.out.println("Current Teachings: " + targetAddress.getAssociatedTeachings().stream()
                .map(t -> t.getName() + " (" + t.getTeachingId() + ")")
                .collect(Collectors.joining(", ")));
        System.out.println("Available Teachings: " + teachingRepository.values().stream()
                .map(t -> t.getName() + " (" + t.getTeachingId() + ")")
                .collect(Collectors.joining(", ")));
        System.out.println("----------------------------------------------------");

        Map<String, Object> formData = new HashMap<>();
        formData.put("address", targetAddress);
        formData.put("currentTeachings", targetAddress.getAssociatedTeachings());
        formData.put("allAvailableTeachings", new HashSet<>(teachingRepository.values()));
        return formData;
    }

    /**
     * Associates or removes selected teachings at the address.
     * This corresponds to System Event 4: "Associate or removes selected teachings at the address."
     * and User Event 3: "Click on the 'Send' button".
     *
     * @param addressId The ID of the address to modify.
     * @param teachingsToAddIds A set of Teaching IDs to be assigned to the address.
     * @param teachingsToRemoveIds A set of Teaching IDs to be removed from the address.
     * @return true if the operation was successful and resulted in changes, false otherwise.
     */
    public boolean assignRemoveTeachings(String addressId, Set<String> teachingsToAddIds, Set<String> teachingsToRemoveIds) {
        if (!isAdminLoggedIn()) {
            System.out.println("Operation failed: Administrator not logged in.");
            return false;
        }

        Address targetAddress = addressRepository.get(addressId);
        if (targetAddress == null) {
            System.out.println("Operation failed: Address with ID '" + addressId + "' not found.");
            return false;
        }

        boolean changesMade = false;
        Set<String> addedTeachingNames = new HashSet<>();
        Set<String> removedTeachingNames = new HashSet<>();

        // Process teachings to remove first
        if (teachingsToRemoveIds != null) {
            for (String teachingId : teachingsToRemoveIds) {
                Teaching teaching = teachingRepository.get(teachingId);
                if (teaching == null) {
                    System.out.println("Warning: Teaching with ID '" + teachingId + "' to remove not found in system. Skipping.");
                    continue;
                }
                if (targetAddress.removeTeaching(teaching)) {
                    removedTeachingNames.add(teaching.getName());
                    changesMade = true;
                } else {
                    System.out.println("Info: Teaching '" + teaching.getName() + "' was not associated with address '" + targetAddress.getName() + "'. No removal needed.");
                }
            }
        }

        // Then process teachings to add
        if (teachingsToAddIds != null) {
            for (String teachingId : teachingsToAddIds) {
                Teaching teaching = teachingRepository.get(teachingId);
                if (teaching == null) {
                    System.out.println("Warning: Teaching with ID '" + teachingId + "' to add not found in system. Skipping.");
                    continue;
                }
                if (targetAddress.addTeaching(teaching)) {
                    addedTeachingNames.add(teaching.getName());
                    changesMade = true;
                } else {
                    System.out.println("Info: Teaching '" + teaching.getName() + "' is already associated with address '" + targetAddress.getName() + "'. No addition needed.");
                }
            }
        }

        if (changesMade) {
            System.out.println("\nSuccessfully updated teachings for Address: " + targetAddress.getName());
            if (!addedTeachingNames.isEmpty()) {
                System.out.println("  Added: " + String.join(", ", addedTeachingNames));
            }
            if (!removedTeachingNames.isEmpty()) {
                System.out.println("  Removed: " + String.join(", ", removedTeachingNames));
            }
            System.out.println("Postcondition: The system has changed the teachings relating to the address.");
            return true;
        } else {
            System.out.println("\nNo changes were made to teachings for Address: " + targetAddress.getName() + ". All specified teachings were already in the desired state or not found.");
            return false;
        }
    }

    /**
     * Simulates the administrator interrupting the operation or connection to the SMOS server being interrupted.
     * This fulfills one of the postconditions.
     */
    public void interruptOperation() {
        System.out.println("\nPostcondition: The administrator interrupts the operation / connection to the SMOS server interrupted.");
        this.loggedInAdministrator = null; // Log out the admin
    }

    /**
     * Retrieves an address by its ID.
     * @param addressId The ID of the address.
     * @return The Address object, or null if not found.
     */
    public Address getAddressById(String addressId) {
        return addressRepository.get(addressId);
    }

    /**
     * Retrieves a teaching by its ID.
     * @param teachingId The ID of the teaching.
     * @return The Teaching object, or null if not found.
     */
    public Teaching getTeachingById(String teachingId) {
        return teachingRepository.get(teachingId);
    }
}