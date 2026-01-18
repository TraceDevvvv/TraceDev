// File: SearchResultDTO.java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Data Transfer Object (DTO) to encapsulate search results from various entity types.
 * // Added to satisfy requirement {'Flow of Events': '4. System presents classes, teachings, addresses, and users found within the active list.'} and resolve CD-G2/SC-4.
 */
public class SearchResultDTO {
    // + foundClasses : List<ClassEntity>
    public List<ClassEntity> foundClasses;
    // + foundTeachings : List<TeachingEntity>
    public List<TeachingEntity> foundTeachings;
    // + foundAddresses : List<AddressEntity>
    public List<AddressEntity> foundAddresses;
    // + foundUsers : List<UserEntity>
    public List<UserEntity> foundUsers;

    /**
     * Constructs a SearchResultDTO with the aggregated lists of found entities.
     *
     * @param foundClasses    List of ClassEntity objects found.
     * @param foundTeachings  List of TeachingEntity objects found.
     * @param foundAddresses  List of AddressEntity objects found.
     * @param foundUsers      List of UserEntity objects found.
     */
    public SearchResultDTO(List<ClassEntity> foundClasses, List<TeachingEntity> foundTeachings,
                           List<AddressEntity> foundAddresses, List<UserEntity> foundUsers) {
        // Use Collections.unmodifiableList to ensure the lists are not modified externally after creation
        this.foundClasses = (foundClasses != null) ? Collections.unmodifiableList(new ArrayList<>(foundClasses)) : Collections.emptyList();
        this.foundTeachings = (foundTeachings != null) ? Collections.unmodifiableList(new ArrayList<>(foundTeachings)) : Collections.emptyList();
        this.foundAddresses = (foundAddresses != null) ? Collections.unmodifiableList(new ArrayList<>(foundAddresses)) : Collections.emptyList();
        this.foundUsers = (foundUsers != null) ? Collections.unmodifiableList(new ArrayList<>(foundUsers)) : Collections.emptyList();
    }

    /**
     * Aggregates all found entities into a single list of AbstractEntity.
     *
     * @return A list containing all found entities, regardless of their specific type.
     */
    public List<AbstractEntity> getAllFoundEntities() {
        List<AbstractEntity> allEntities = new ArrayList<>();
        allEntities.addAll(foundClasses);
        allEntities.addAll(foundTeachings);
        allEntities.addAll(foundAddresses);
        allEntities.addAll(foundUsers);
        return Collections.unmodifiableList(allEntities);
    }

    /**
     * Provides a string representation of the SearchResultDTO.
     *
     * @return A string detailing the count of each type of found entity.
     */
    @Override
    public String toString() {
        return "SearchResultDTO{\n" +
               "  foundClasses=" + foundClasses.size() + " entities,\n" +
               "  foundTeachings=" + foundTeachings.size() + " entities,\n" +
               "  foundAddresses=" + foundAddresses.size() + " entities,\n" +
               "  foundUsers=" + foundUsers.size() + " entities\n" +
               "}";
    }
}