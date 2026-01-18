package dataaccess;

import dtos.SmosTeachingData;

import java.util.ArrayList;
import java.util.List;

/**
 * Proxy for interacting with the external SMOS server.
 * Simulates fetching teaching data from an external system.
 */
public class SmosServerProxy {

    /**
     * Fetches teaching data from the simulated SMOS server.
     *
     * @return A list of SmosTeachingData objects.
     */
    public List<SmosTeachingData> fetchTeachingsFromSmos() {
        System.out.println("SmosServerProxy: Making external call to SMOS server (simulated)...");
        // note right of Smos: External call to SMOS server.
        // Simulate a delay for external call
        try {
            Thread.sleep(500); // 0.5 second delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Simulate returning some data
        List<SmosTeachingData> data = new ArrayList<>();
        data.add(new SmosTeachingData("SMOS001", "CS101", "Introduction to Programming", "Dr. Alice Smith", "Fundamental concepts of computer programming."));
        data.add(new SmosTeachingData("SMOS002", "MA203", "Calculus III", "Prof. Bob Johnson", "Advanced topics in multivariable calculus."));
        data.add(new SmosTeachingData("SMOS003", "PH305", "Quantum Mechanics", "Dr. Carol White", "Principles and applications of quantum theory."));

        System.out.println("SmosServerProxy: Successfully fetched " + data.size() + " entries from SMOS.");
        // Smos --> Repo : List<SmosTeachingData>
        return data;
    }
}