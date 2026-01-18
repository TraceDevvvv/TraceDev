package com.example;

import java.util.List;

/**
 * Service layer for managing and retrieving user's favorite bookmarks.
 * This class orchestrates data retrieval from the repository and applies business logic if any.
 */
public class FavoritesService {
    private IFavoritesRepository favoritesRepository;

    /**
     * Constructs a FavoritesService with a specific repository implementation.
     * @param favoritesRepository The repository to use for fetching favorites data.
     */
    public FavoritesService(IFavoritesRepository favoritesRepository) {
        this.favoritesRepository = favoritesRepository;
    }

    /**
     * Retrieves the personal favorite bookmarks for a given tourist.
     * This method handles potential connection errors by wrapping them into FavoritesRetrievalError,
     * as per the sequence diagram.
     *
     * @param touristId The ID of the tourist.
     * @return A list of Bookmark objects.
     * @throws FavoritesRetrievalError If there's any problem retrieving or processing the favorites.
     */
    public List<Bookmark> getPersonalFavorites(String touristId) throws FavoritesRetrievalError {
        System.out.println("FavoritesService: Attempting to get personal favorites for tourist: " + touristId);
        try {
            // Delegate the call to the repository
            return favoritesRepository.findFavoritesByTouristId(touristId);
        } catch (ConnectionError e) {
            // As per sequence diagram, catch ConnectionError and throw FavoritesRetrievalError
            // with the original ConnectionError as the cause (R7).
            System.err.println("FavoritesService: Caught ConnectionError, re-throwing as FavoritesRetrievalError: " + e.getMessage());
            throw new FavoritesRetrievalError("Failed to connect to retrieve favorites.", e);
        } catch (FavoritesRetrievalError e) {
            // If the repository threw a FavoritesRetrievalError (e.g., due to parsing), re-throw it directly.
            System.err.println("FavoritesService: Caught FavoritesRetrievalError from repository: " + e.getMessage());
            throw e;
        }
    }
}