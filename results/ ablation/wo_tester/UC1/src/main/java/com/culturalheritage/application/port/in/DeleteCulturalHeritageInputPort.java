package com.culturalheritage.application.port.in;

import com.culturalheritage.application.request.DeleteCulturalHeritageRequest;
import com.culturalheritage.application.response.DeleteCulturalHeritageResponse;

/**
 * Input Port for the Delete Cultural Heritage use case.
 * Implemented by the Interactor.
 */
public interface DeleteCulturalHeritageInputPort {
    DeleteCulturalHeritageResponse execute(DeleteCulturalHeritageRequest request);
}