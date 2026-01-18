package com.example;

import java.util.List;

/**
 * Interface defining the contract for retrieving favorite bookmarks.
 */
public interface IFavoritesRepository {

    /**
     * Finds and returns a list of bookmarks associated with a specific tourist ID.
     *
     * @param touristId The ID of the tourist whose favorites are to be retrieved.
     * @return A list of Bookmark objects.
     * @throws ConnectionError If there is an issue establishing or maintaining a connection to the data source.
     * @throws FavoritesRetrievalError If there is an issue parsing the retrieved data or other retrieval problems.
     */
    List<Bookmark> findFavoritesByTouristId(String touristId) throws ConnectionError, FavoritesRetrievalError;
}