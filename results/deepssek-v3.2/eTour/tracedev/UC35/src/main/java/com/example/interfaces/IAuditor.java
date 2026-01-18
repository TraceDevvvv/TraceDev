package com.example.interfaces;

import com.example.domain.AuditEvent;

/**
 * Auditor interface for recording audit events.
 */
public interface IAuditor {
    void audit(AuditEvent event);
}