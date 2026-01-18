package hec.ports;

import hec.core.Tag;
import java.util.Optional;

/**
 * Interface for Tag repository.
 * Defines the contract for persisting and retrieving Tag entities.
 */
public interface TagRepository {
    /**
     * Saves a Tag.
     *
     * @param tag the Tag to save
     * @return the saved Tag
     */
    Tag save(Tag tag);

    /**
     * Checks if a Tag with the given name already exists.
     *
     * @param name the name to check
     * @return true if exists, false otherwise
     */
    boolean existsByName(String name);

    /**
     * Finds a Tag by its ID.
     *
     * @param id the Tag ID
     * @return an Optional containing the Tag if found
     */
    Optional<Tag> findById(String id);
}