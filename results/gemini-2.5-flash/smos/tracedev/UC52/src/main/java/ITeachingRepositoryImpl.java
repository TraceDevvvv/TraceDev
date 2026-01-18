// File: ITeachingRepositoryImpl.java
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Dummy implementation for ITeachingRepository.
 * Used to simulate database interactions for TeachingEntity.
 * Throws SMOSServerException if "fail_teaching" keyword is used.
 */
class ITeachingRepositoryImpl implements ITeachingRepository {
    private List<TeachingEntity> teachings;

    public ITeachingRepositoryImpl() {
        teachings = new ArrayList<>();
        teachings.add(new TeachingEntity("T001", "CS101 Fall 2023 Lecture", "Dr. Smith"));
        teachings.add(new TeachingEntity("T002", "CS201 Spring 2024 Lab", "Ms. Johnson"));
        teachings.add(new TeachingEntity("T003", "MA101 Fall 2023 Seminar", "Prof. Davies"));
    }

    @Override
    public List<TeachingEntity> findByKeywords(String keywords) throws SMOSServerException {
        // Simulate a server connection error if specific keyword is used
        if (keywords != null && keywords.toLowerCase().contains("fail_teaching")) {
            throw new SMOSServerException("Simulated connection error to Teaching Repository.");
        }

        if (keywords == null || keywords.trim().isEmpty()) {
            return new ArrayList<>(teachings); // Return all if no keywords
        }

        String lowerKeywords = keywords.toLowerCase();
        return teachings.stream()
                .filter(t -> t.name.toLowerCase().contains(lowerKeywords) ||
                             t.instructorName.toLowerCase().contains(lowerKeywords) ||
                             t.id.toLowerCase().contains(lowerKeywords))
                .collect(Collectors.toList());
    }
}