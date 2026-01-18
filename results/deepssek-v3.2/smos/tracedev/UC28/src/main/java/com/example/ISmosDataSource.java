package com.example;

/**
 * Interface for data source connecting to SMOS server.
 */
public interface ISmosDataSource {
    ClassRegisterDTO fetchRegisterData(String registerId) throws ConnectionException;
}