package com.example.usecase;

import com.example.response.DeleteBannerResponse;

/**
 * Use case interface for deleting banners.
 * Follows Clean Architecture by defining the business logic contract.
 */
public interface DeleteBannerUseCase {
    /**
     * Executes the delete banner use case.
     * @param operatorId the ID of the operator performing the deletion
     * @param bannerId the ID of the banner to delete (null for retrieval only)
     * @return response containing result of the operation
     */
    DeleteBannerResponse execute(String operatorId, String bannerId);
}