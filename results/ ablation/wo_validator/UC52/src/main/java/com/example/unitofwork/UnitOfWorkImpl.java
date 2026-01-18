package com.example.unitofwork;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Implementation of UnitOfWork using JDBC Connection.
 */
public class UnitOfWorkImpl implements UnitOfWork {
    private Connection connection;
    private boolean transactionActive = false;

    public UnitOfWorkImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void begin() {
        try {
            connection.setAutoCommit(false);
            transactionActive = true;
        } catch (SQLException e) {
            throw new RuntimeException("Error beginning transaction", e);
        }
    }

    @Override
    public void commit() {
        try {
            connection.commit();
            connection.setAutoCommit(true);
            transactionActive = false;
        } catch (SQLException e) {
            throw new RuntimeException("Error committing transaction", e);
        }
    }

    @Override
    public void rollback() {
        try {
            if (transactionActive) {
                connection.rollback();
                connection.setAutoCommit(true);
                transactionActive = false;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error rolling back transaction", e);
        }
    }
}