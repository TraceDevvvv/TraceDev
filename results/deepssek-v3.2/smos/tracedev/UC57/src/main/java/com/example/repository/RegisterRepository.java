package com.example.repository;

import com.example.model.SmosRegisterData;
import com.example.exception.ConnectionException;

/**
 * Repository interface for fetching register data.
 */
public interface RegisterRepository {
    SmosRegisterData fetchRegisterData(String classId) throws ConnectionException;
}