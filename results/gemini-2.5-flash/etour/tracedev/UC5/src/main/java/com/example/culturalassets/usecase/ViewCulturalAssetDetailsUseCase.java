package com.example.culturalassets.usecase;

import com.example.culturalassets.domain.CulturalAsset;
import com.example.culturalassets.dto.CulturalAssetDetailsDTO;
import com.example.culturalassets.repository.ICulturalAssetRepository;
import com.example.culturalassets.exception.ConnectionFailedException;
import java.util.Date;

/**
 * Use case for viewing details of a specific cultural asset.
 * This class orchestrates the retrieval of data from the repository
 * and its transformation into a presentation-friendly DTO.
 */
public class ViewCulturalAssetDetailsUseCase {
    private ICulturalAssetRepository culturalAssetRepository;

    /**
     * Constructs a ViewCulturalAssetDetailsUseCase with a required repository.
     * @param culturalAssetRepository The repository used to fetch cultural asset data.
     */
    public ViewCulturalAssetDetailsUseCase(ICulturalAssetRepository culturalAssetRepository) {
        this.culturalAssetRepository = culturalAssetRepository;
    }

    /**
     * Executes the use case to retrieve and format cultural asset details.
     * @param assetId The unique identifier of the cultural asset to view.
     * @return A CulturalAssetDetailsDTO containing the asset's information.
     * @throws Exception If any error occurs during data retrieval or transformation,
     *                   including ConnectionFailedException from the repository.
     */
    public CulturalAssetDetailsDTO execute(String assetId) throws Exception {
        System.out.println("UseCase: Executing ViewCulturalAssetDetails for asset ID: " + assetId);
        try {
            // 1. Retrieve CulturalAsset from the repository
            CulturalAsset culturalAssetObject = culturalAssetRepository.findById(assetId);

            if (culturalAssetObject == null) {
                throw new Exception("Cultural Asset with ID " + assetId + " not found.");
            }

            // 2. Transform the domain object into a DTO for the presentation layer
            // The sequence diagram specifies a currentTimestamp for lastUpdated.
            Date currentTimestamp = new Date();
            CulturalAssetDetailsDTO culturalAssetDetails = CulturalAssetDetailsDTO.createFrom(culturalAssetObject, currentTimestamp);

            System.out.println("UseCase: Successfully retrieved and prepared DTO for asset ID: " + assetId);
            return culturalAssetDetails;

        } catch (ConnectionFailedException e) {
            // Catch the specific connection error and re-throw as a generic Exception
            // or a more specific application-level exception if needed.
            // For this scenario, the SD implies catching a generic Exception at Controller.
            System.err.println("UseCase: Error during data retrieval - ConnectionFailedException: " + e.getMessage());
            throw e; // Re-throw to be handled by the controller
        } catch (Exception e) {
            // Catch any other unexpected errors
            System.err.println("UseCase: An unexpected error occurred: " + e.getMessage());
            throw e; // Re-throw to be handled by the controller
        }
    }
}