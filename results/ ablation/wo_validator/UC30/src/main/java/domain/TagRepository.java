package domain;

import java.util.Optional;

/**
 * Repository interface for Tag persistence operations.
 */
public interface TagRepository {
    Optional<Tag> findByName(String name);
    Tag save(Tag tag);
    boolean existsByName(String name);
}