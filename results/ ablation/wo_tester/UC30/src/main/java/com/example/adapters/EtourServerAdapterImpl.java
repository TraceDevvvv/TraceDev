package com.example.adapters;

import com.example.application.interfaces.EtourServerAdapter;
import com.example.application.interfaces.ErrorRepository;
import com.example.domain.entities.Tag;

/**
 * Implementation of the ETOUR server adapter.
 */
public class EtourServerAdapterImpl implements EtourServerAdapter {
    private ErrorRepository errorRepository;

    public EtourServerAdapterImpl(ErrorRepository errorRepository) {
        this.errorRepository = errorRepository;
    }

    @Override
    public void notifyTagCreated(Tag tag) {
        try {
            // Simulate notification to external ETOUR server
            // In real scenario, this would involve an HTTP call or message queue.
            System.out.println("ETOUR server notified about tag: " + tag.getName());

            // If notification successful, update tag's notified status
            tag.setNotified(true);

        } catch (Exception e) {
            // Connection lost or notification failed
            // Log error as per sequence diagram
            errorRepository.logError("ETOUR_CONNECTION", "Failed to notify");
            // Re-throw or handle as needed
            throw new RuntimeException("ETOUR notification failed", e);
        }
    }
}