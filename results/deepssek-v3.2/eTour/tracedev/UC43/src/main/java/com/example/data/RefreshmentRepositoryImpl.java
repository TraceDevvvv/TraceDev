package com.example.data;

import com.example.domain.Refreshment;
import com.example.infrastructure.ETOURServerConnection;

/**
 * Implementation of RefreshmentRepository.
 */
public class RefreshmentRepositoryImpl implements RefreshmentRepository {
    private ETOURServerConnection connection;

    public RefreshmentRepositoryImpl(ETOURServerConnection connection) {
        this.connection = connection;
    }

    @Override
    public Refreshment findById(Long id) {
        // In a real implementation, this would query a database.
        // For demo, we return a dummy refreshment.
        if (id.equals(1L)) {
            return new Refreshment(1L, "Original Refreshment", "Original description", 
                                   new java.math.BigDecimal("4.99"), true);
        }
        return null;
    }

    @Override
    public void save(Refreshment refreshment) {
        // In a real implementation, this would persist to a database.
        // Here we simulate sending data via the connection.
        boolean sent = connection.sendData(refreshment);
        if (sent) {
            System.out.println("Refreshment saved: " + refreshment.getName());
        } else {
            System.out.println("Failed to save refreshment due to connection issue.");
        }
    }
}