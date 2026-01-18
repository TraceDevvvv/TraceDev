package com.example.service;

import java.util.List;
import java.util.Map;
/**
 * External ETOUR service interface.
 * Added <<reliability>> stereotype to satisfy requirement Quality Requirement.
 */
public interface ETOURService {
    List<Map<String, Object>> findSitesWithFeedback(String touristId);
}