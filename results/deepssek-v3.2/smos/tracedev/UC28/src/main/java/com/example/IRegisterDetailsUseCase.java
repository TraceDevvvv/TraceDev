package com.example;

/**
 * Use case interface for getting register details.
 */
public interface IRegisterDetailsUseCase {
    RegisterDetailsDTO getRegisterDetails(String registerId) throws ConnectionException;
}