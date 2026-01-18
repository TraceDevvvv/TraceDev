// File: IClassRepositoryImpl.java
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Dummy implementation for IClassRepository.
 * Used to simulate database interactions for ClassEntity.
 * Throws SMOSServerException if "fail_class" keyword is used.
 */
class IClassRepositoryImpl implements IClassRepository {
    private List<ClassEntity> classes;

    public IClassRepositoryImpl() {
        classes = new ArrayList<>();
        classes.add(new ClassEntity("C001", "Introduction to Programming", "CS101"));
        classes.add(new ClassEntity("C002", "Data Structures", "CS201"));
        classes.add(new ClassEntity("C003", "Algorithms", "CS301"));
        classes.add(new ClassEntity("M001", "Calculus I", "MA101"));
    }

    @Override
    public List<ClassEntity> findByKeywords(String keywords) throws SMOSServerException {
        // Simulate a server connection error if specific keyword is used
        if (keywords != null && keywords.toLowerCase().contains("fail_class")) {
            throw new SMOSServerException("Simulated connection error to Class Repository.");
        }

        if (keywords == null || keywords.trim().isEmpty()) {
            return new ArrayList<>(classes); // Return all if no keywords
        }

        String lowerKeywords = keywords.toLowerCase();
        return classes.stream()
                .filter(c -> c.name.toLowerCase().contains(lowerKeywords) ||
                             c.courseCode.toLowerCase().contains(lowerKeywords) ||
                             c.id.toLowerCase().contains(lowerKeywords))
                .collect(Collectors.toList());
    }
}