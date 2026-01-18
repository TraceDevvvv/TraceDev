package com.example;

/**
 * Repository interface for ClassRegister.
 */
public interface IClassRegisterRepository {
    ClassRegister findRegisterById(String registerId) throws ConnectionException;
}