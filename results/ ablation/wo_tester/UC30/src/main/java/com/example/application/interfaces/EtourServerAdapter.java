package com.example.application.interfaces;

import com.example.domain.entities.Tag;

/**
 * Adapter interface for notifying external ETOUR server about tag creation.
 */
public interface EtourServerAdapter {
    /**
     * Notifies the ETOUR server that a tag has been created.
     */
    void notifyTagCreated(Tag tag);
}