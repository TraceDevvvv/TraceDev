// File: IRepository.java
import java.util.List;

/**
 * Generic interface for a repository that can find entities by keywords.
 *
 * @param <T> The type of entity managed by this repository, which must extend AbstractEntity.
 */
public interface IRepository<T extends AbstractEntity> {
    /**
     * Finds a list of entities based on the provided keywords.
     * Throws SMOSServerException if there's a connection issue to the server.
     *
     * @param keywords The keywords to search for.
     * @return A list of entities matching the keywords.
     * @throws SMOSServerException if the connection to the SMOS server is interrupted.
     */
    List<T> findByKeywords(String keywords) throws SMOSServerException;
}