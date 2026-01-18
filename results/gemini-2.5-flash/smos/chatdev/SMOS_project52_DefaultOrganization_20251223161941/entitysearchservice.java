/**
 * Service class to simulate searching for various entities.
 * In a real application, this would interact with a database or other data sources.
 */
package com.chatdev.serv;
import com.chatdev.entities.AddressEntity;
import com.chatdev.entities.ClassEntity;
import com.chatdev.entities.TeachingEntity;
import com.chatdev.entities.UserEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class EntitySearchService {
    // Mock data for demonstration purposes
    private final List<ClassEntity> allClasses;
    private final List<TeachingEntity> allTeachings;
    private final List<AddressEntity> allAddresses;
    private final List<UserEntity> allUsers;
    /**
     * Initializes the EntitySearchService with mock data.
     */
    public EntitySearchService() {
        // Initialize mock data
        allClasses = new ArrayList<>();
        allClasses.add(new ClassEntity("CS101", "Intro to Programming", "Computer Science"));
        allClasses.add(new ClassEntity("MA201", "Calculus I", "Mathematics"));
        allClasses.add(new ClassEntity("CS202", "Data Structures", "Computer Science"));
        allClasses.add(new ClassEntity("PH101", "General Physics", "Physics"));
        allTeachings = new ArrayList<>();
        allTeachings.add(new TeachingEntity("T001", "Intro to Programming", "Dr. Smith", "Fall 2023"));
        allTeachings.add(new TeachingEntity("T002", "Calculus I", "Prof. Jones", "Spring 2024"));
        allTeachings.add(new TeachingEntity("T003", "Data Structures", "Dr. Smith", "Fall 2023"));
        allTeachings.add(new TeachingEntity("T004", "Algorithms", "Prof. Doe", "Spring 2024"));
        allAddresses = new ArrayList<>();
        allAddresses.add(new AddressEntity("A100", "123 Main St", "Anytown", "12345", "USA"));
        allAddresses.add(new AddressEntity("A101", "456 Oak Ave", "Otherville", "67890", "USA"));
        allAddresses.add(new AddressEntity("A102", "789 Pine Ln", "Somewhere", "11223", "Canada"));
        allAddresses.add(new AddressEntity("A103", "10 Downing St", "London", "SW1A 2AA", "UK"));
        allUsers = new ArrayList<>();
        allUsers.add(new UserEntity("U001", "admin1", "Administrator", "admin1@example.com"));
        allUsers.add(new UserEntity("U002", "john.doe", "Student", "john.doe@example.com"));
        allUsers.add(new UserEntity("U003", "jane.smith", "Faculty", "jane.smith@example.com"));
        allUsers.add(new UserEntity("U004", "paul.jones", "Student", "paul.jones@example.com"));
        allUsers.add(new UserEntity("U005", "admin2", "Administrator", "admin2@example.com"));
    }
    /**
     * Searches through all available entities based on keywords.
     * The search is case-insensitive and checks against specific fields of entities.
     *
     * @param keywords The text to search for.
     * @return A SearchResult object containing lists of found entities.
     */
    public SearchResult searchEntities(String keywords) {
        if (keywords == null || keywords.trim().isEmpty()) {
            // If keywords are empty, return empty lists
            return new SearchResult(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        }
        String searchLower = keywords.toLowerCase();
        // Filter classes by checking relevant fields
        List<ClassEntity> foundClasses = allClasses.stream()
                .filter(entity -> entity.getClassId().toLowerCase().contains(searchLower) ||
                                   entity.getClassName().toLowerCase().contains(searchLower) ||
                                   entity.getDepartment().toLowerCase().contains(searchLower))
                .collect(Collectors.toList());
        // Filter teachings by checking relevant fields
        List<TeachingEntity> foundTeachings = allTeachings.stream()
                .filter(entity -> entity.getTeachingId().toLowerCase().contains(searchLower) ||
                                   entity.getCourseName().toLowerCase().contains(searchLower) ||
                                   entity.getInstructor().toLowerCase().contains(searchLower) ||
                                   entity.getSemester().toLowerCase().contains(searchLower))
                .collect(Collectors.toList());
        // Filter addresses by checking relevant fields
        List<AddressEntity> foundAddresses = allAddresses.stream()
                .filter(entity -> entity.getAddressId().toLowerCase().contains(searchLower) ||
                                   entity.getStreet().toLowerCase().contains(searchLower) ||
                                   entity.getCity().toLowerCase().contains(searchLower) ||
                                   entity.getZipCode().toLowerCase().contains(searchLower) ||
                                   entity.getCountry().toLowerCase().contains(searchLower))
                .collect(Collectors.toList());
        // Filter users by checking relevant fields
        List<UserEntity> foundUsers = allUsers.stream()
                .filter(entity -> entity.getUserId().toLowerCase().contains(searchLower) ||
                                   entity.getUsername().toLowerCase().contains(searchLower) ||
                                   entity.getRole().toLowerCase().contains(searchLower) ||
                                   entity.getEmail().toLowerCase().contains(searchLower))
                .collect(Collectors.toList());
        return new SearchResult(foundClasses, foundTeachings, foundAddresses, foundUsers);
    }
}