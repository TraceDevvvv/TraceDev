package com.system.service;

/**
 * Service to check agency status.
 */
public interface IAgencyService {
    boolean isAgencyActive(String agencyId);
}