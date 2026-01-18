package com.example;

import com.example.service.CulturalHeritageAgencyManager;
import com.example.service.ICulturalHeritageAgencyManager;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Server application for RMI.
 * This class sets up and binds the CulturalHeritageAgencyManager
 * service to the RMI registry.
 */
public class RmiServer {

    public static void main(String[] args) {
        try {
            // Create the RMI registry if it's not already running
            // Specify port for the registry
            Registry registry = LocateRegistry.createRegistry(Home.RMI_PORT);
            System.out.println("RMI Registry created on port " + Home.RMI_PORT);

            // Create an instance of the remote object implementation
            ICulturalHeritageAgencyManager manager = new CulturalHeritageAgencyManager();

            // Bind the remote object to the RMI registry
            registry.rebind(Home.RMI_SERVICE_NAME, manager);
            System.out.println(Home.RMI_SERVICE_NAME + " service bound to registry.");
            System.out.println("Server ready.");

        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}