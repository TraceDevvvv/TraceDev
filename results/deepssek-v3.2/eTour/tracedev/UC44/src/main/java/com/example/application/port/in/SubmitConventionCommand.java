package com.example.application.port.in;

import com.example.domain.ConventionData;
import com.example.domain.ConventionRequest;

/**
 * Input port for submitting a convention request.
 */
public interface SubmitConventionCommand {
    ConventionRequest execute(String restaurantId, ConventionData data);
}