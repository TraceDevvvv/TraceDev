/**
 * A data transfer object to hold the results of an entity search,
 * categorised by entity type.
 */
package com.chatdev.serv;
import com.chatdev.entities.AddressEntity;
import com.chatdev.entities.ClassEntity;
import com.chatdev.entities.TeachingEntity;
import com.chatdev.entities.UserEntity;
import java.util.List;
import java.util.Collections;
public class SearchResult {
    private final List<ClassEntity> classes;
    private final List<TeachingEntity> teachings;
    private final List<AddressEntity> addresses;
    private final List<UserEntity> users;
    /**
     * Constructor to initialize search results.
     * @param classes List of found ClassEntity objects.
     * @param teachings List of found TeachingEntity objects.
     * @param addresses List of found AddressEntity objects.
     * @param users List of found UserEntity objects.
     */
    public SearchResult(List<ClassEntity> classes, List<TeachingEntity> teachings, List<AddressEntity> addresses, List<UserEntity> users) {
        this.classes = classes != null ? classes : Collections.emptyList();
        this.teachings = teachings != null ? teachings : Collections.emptyList();
        this.addresses = addresses != null ? addresses : Collections.emptyList();
        this.users = users != null ? users : Collections.emptyList();
    }
    // Getters for each list of entity type
    public List<ClassEntity> getClasses() {
        return classes;
    }
    public List<TeachingEntity> getTeachings() {
        return teachings;
    }
    public List<AddressEntity> getAddresses() {
        return addresses;
    }
    public List<UserEntity> getUsers() {
        return users;
    }
}