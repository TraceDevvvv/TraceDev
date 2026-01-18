
package com.example.repositories;

import com.example.entities.Registry;
import com.example.entities.RegistryEntry;
import com.example.entities.StudentEntry;
import com.example.exceptions.SMOSConnectionException;
import com.example.serv.RegistryDetailsService;
import java.util.Date;

/**
 * Implementation of RegistryRepository that connects to SMOS server.
 */
public class SMOSRegistryRepository implements RegistryRepository {
    private ConnectionStatus connectionStatus = ConnectionStatus.CONNECTED;

    @Override
    public Registry findByAcademicYearAndClassName(String academicYear, String className) {
        // Simulate fetching from SMOS server
        callSMOSAPI();
        if (connectionStatus == ConnectionStatus.DISCONNECTED) {
            // Return empty registry if disconnected
            return createDummyRegistry(academicYear, className);
        }
        return createDummyRegistry(academicYear, className);
    }

    @Override
    public void save(Registry registry) throws SMOSConnectionException {
        callSMOSAPI();
        if (connectionStatus == ConnectionStatus.DISCONNECTED) {
            throw new SMOSConnectionException("SMOS001", "Connection interrupted");
        }
        // Simulate successful save
        System.out.println("Registry saved to SMOS server");
    }

    private void callSMOSAPI() {
        // Simulate API call that may fail
        // In this simulation, connection status remains CONNECTED
        // For testing exception, we could randomly set to DISCONNECTED
    }

    private Registry createDummyRegistry(String academicYear, String className) {
        Registry registry = new Registry(academicYear, className, new java.util.ArrayList<>());
        RegistryEntry entry = new RegistryEntry(new Date(), new java.util.ArrayList<>());
        entry.addStudentEntry(new StudentEntry("STU001", "PRESENT", false, "Sick", ""));
        entry.addStudentEntry(new StudentEntry("STU002", "ABSENT", true, "", "Late arrival"));
        registry.addRegistryEntry(entry);
        return registry;
    }

    // For testing: allow setting connection status
    public void setConnectionStatus(ConnectionStatus status) {
        this.connectionStatus = status;
    }

    // Method corresponding to sequence diagram message
    public void errorConnectionInterrupted(RegistryDetailsService service) {
        // Implementation for error connection interrupted message
    }
    
    // Inner enum to resolve compilation errors
    private enum ConnectionStatus {
        CONNECTED,
        DISCONNECTED
    }
}
