package com.example.application.port.out;

import com.example.domain.ConventionRequest;

/**
 * Output port for notifying an agency about a convention request.
 */
public interface AgencyNotificationPort {
    boolean notifyAgency(ConventionRequest request);
}