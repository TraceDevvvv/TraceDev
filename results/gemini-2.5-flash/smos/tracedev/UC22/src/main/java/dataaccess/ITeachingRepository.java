package dataaccess;

import domain.Teaching;

import java.util.List;

/**
 * Interface for the Data Access Layer, defining the contract for teaching data operations.
 */
public interface ITeachingRepository {
    /**
     * Retrieves all teaching entities from the data source.
     *
     * @return A list of Teaching domain entities. Returns null if an error occurs (e.g., connection issue).
     */
    List<Teaching> findAll();
}