package com.example.infrastructure;

import com.example.application.IUnitOfWork;
import java.util.Random;

/**
 * Infrastructure Layer - Ensures transactional consistency.
 * Marked as reliable per diagram.
 * Simulates commit with random success/failure to demonstrate flows.
 */
public class UnitOfWork implements IUnitOfWork {
    // Simulating DbContext
    private DbContext dbContext;

    public UnitOfWork() {
        this.dbContext = new DbContext(); // placeholder
    }

    @Override
    public boolean commit() {
        // Simulate commit operation with a chance of failure/interruption.
        Random random = new Random();
        // 70% success, 30% failure (to demonstrate both flows)
        boolean success = random.nextDouble() > 0.3;
        
        if (success) {
            System.out.println("UnitOfWork commit succeeded.");
        } else {
            System.out.println("UnitOfWork commit failed (simulated connection issue).");
        }
        return success;
    }

    @Override
    public void rollback() {
        System.out.println("UnitOfWork rollback performed.");
    }

    // Additional UoW pattern methods (per class diagram)
    public void registerNew(Object entity) {
        System.out.println("Registered new entity: " + entity);
    }

    public void registerDirty(Object entity) {
        System.out.println("Registered dirty entity: " + entity);
    }

    public void registerDeleted(Object entity) {
        System.out.println("Registered deleted entity: " + entity);
    }
}