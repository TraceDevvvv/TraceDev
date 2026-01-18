package com.example.client;

import com.example.external.ExternalStudentData;

/**
 * Interface for SMOS server client.
 */
public interface SmosServerClient {
    ExternalStudentData fetchData(String studentId);
    void disconnect();
}