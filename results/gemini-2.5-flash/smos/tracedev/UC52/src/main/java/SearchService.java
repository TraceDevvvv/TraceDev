// File: SearchService.java
import java.util.ArrayList;
import java.util.List;

/**
 * Service class responsible for orchestrating the search for various entities
 * across different repositories. It acts as a central point for search operations.
 */
public class SearchService {
    // - classRepository : IClassRepository
    private IClassRepository classRepository;
    // - teachingRepository : ITeachingRepository
    private ITeachingRepository teachingRepository;
    // - addressRepository : IAddressRepository
    private IAddressRepository addressRepository;
    // - userRepository : IUserRepository
    private IUserRepository userRepository;

    /**
     * Constructor for SearchService, injecting repository dependencies.
     * This allows for flexible and testable code (e.g., mock repositories for testing).
     *
     * @param classRepository    The repository for Class entities.
     * @param teachingRepository The repository for Teaching entities.
     * @param addressRepository  The repository for Address entities.
     * @param userRepository     The repository for User entities.
     */
    public SearchService(IClassRepository classRepository,
                         ITeachingRepository teachingRepository,
                         IAddressRepository addressRepository,
                         IUserRepository userRepository) {
        this.classRepository = classRepository;
        this.teachingRepository = teachingRepository;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    /**
     * Performs a comprehensive search for entities based on the provided criteria.
     * It queries all relevant repositories and aggregates the results into a SearchResultDTO.
     * If any repository throws an SMOSServerException, it propagates this exception upwards.
     *
     * @param criteria The SearchCriteriaDTO containing the search keywords.
     * @return A SearchResultDTO containing lists of found Class, Teaching, Address, and User entities.
     * @throws SMOSServerException if any underlying repository encounters a server connection issue.
     */
    public SearchResultDTO searchEntities(SearchCriteriaDTO criteria) throws SMOSServerException {
        List<ClassEntity> foundClasses = new ArrayList<>();
        List<TeachingEntity> foundTeachings = new ArrayList<>();
        List<AddressEntity> foundAddresses = new ArrayList<>();
        List<UserEntity> foundUsers = new ArrayList<>();

        // Perform search on ClassRepository
        // The sequence diagram indicates handling of SMOSServerException from each repository.
        // If an exception occurs, it's propagated immediately.
        foundClasses = classRepository.findByKeywords(criteria.keywords);

        // Perform search on TeachingRepository
        foundTeachings = teachingRepository.findByKeywords(criteria.keywords);

        // Perform search on AddressRepository
        foundAddresses = addressRepository.findByKeywords(criteria.keywords);

        // Perform search on UserRepository
        foundUsers = userRepository.findByKeywords(criteria.keywords);

        // Aggregate all found entities into a SearchResultDTO.
        SearchResultDTO searchResult = new SearchResultDTO(foundClasses, foundTeachings, foundAddresses, foundUsers);
        return searchResult;
    }
}