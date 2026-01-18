package com.example.repository;

import com.example.model.Register;
import com.example.server.SMOSServer;
import com.example.exception.ConnectionException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegisterRepositoryImpl implements RegisterRepository {
    private SMOSServer smosServer;

    public RegisterRepositoryImpl(SMOSServer smosServer) {
        this.smosServer = smosServer;
    }

    /**
     * Fetches registers by academic year, but first connects to SMOS server.
     * Throws ConnectionException if connection fails.
     */
    @Override
    public List<Register> findByAcademicYear(int academicYear) {
        // Step 1: Connect to SMOS server as per sequence diagram
        boolean connected = smosServer.connect();
        if (!connected) {
            throw new ConnectionException("Failed to connect to SMOS server", 1001);
        }

        // Simulate fetching data from server (mock implementation)
        List<Register> registers = new ArrayList<>();
        // For demonstration, generate 3 mock registers for the given academic year.
        for (int i = 1; i <= 3; i++) {
            registers.add(new Register("REG" + academicYear + "00" + i,
                    academicYear,
                    "CLASS" + i,
                    new Date()));
        }
        return registers;
    }
}