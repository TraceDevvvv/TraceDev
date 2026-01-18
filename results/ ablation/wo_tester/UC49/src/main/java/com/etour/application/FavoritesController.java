package com.etour.application;

import com.etour.infrastructure.FavoriteRepository;
import com.etour.domain.Favorite;
import com.etour.dto.FavoriteDTO;
import com.etour.domain.ConnectionException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for the View Favorites use case (implements UC_ViewFavorites).
 * Response time must be ≤ 2 seconds (as per quality requirement).
 */
public class FavoritesController {
    private FavoriteRepository favoriteRepository;
    private AuthenticationService authService;

    public FavoritesController(FavoriteRepository favoriteRepository, AuthenticationService authService) {
        this.favoriteRepository = favoriteRepository;
        this.authService = authService;
    }

    /**
     * Displays favorites for a given tourist.
     * Implements the flow from the sequence diagram.
     * @param touristId the ID of the tourist
     * @return list of FavoriteDTOs
     * @throws ConnectionException if connection to server is interrupted
     */
    public List<FavoriteDTO> displayFavorites(String touristId) throws Exception {
        // Validate session (as per sequence diagram)
        if (!authService.validateSession(touristId)) {
            throw new SecurityException("Invalid session for tourist: " + touristId);
        }

        // Fetch favorites from repository (may throw ConnectionException)
        List<Favorite> favorites = favoriteRepository.findAllByTouristId(touristId);

        // Convert to DTOs - corresponds to message m8: Controller -> Controller "Convert to List<FavoriteDTO>"
        List<FavoriteDTO> favoriteDTOs = convertToFavoriteDTOList(favorites);

        return favoriteDTOs;
    }

    // This method corresponds to sequence diagram message m8: Controller -> Controller "Convert to List<FavoriteDTO>"
    private List<FavoriteDTO> convertToFavoriteDTOList(List<Favorite> favorites) {
        return favorites.stream()
                .map(FavoriteDTO::new)
                .collect(Collectors.toList());
    }

    // This method could be used to send error message to UI (lost message m12 in sequence diagram)
    public void sendErrorMessage(String message) {
        // In a real scenario, this would pass the error to the UI layer.
        // For now, we rely on exception propagation.
    }
}