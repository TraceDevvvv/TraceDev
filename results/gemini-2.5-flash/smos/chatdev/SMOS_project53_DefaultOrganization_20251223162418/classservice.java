'''
Simulates fetching course data from a backend database.
In a real-world application, this would connect to a relational database,
NoSQL database, or an external API.
(Reflects the data type change from 'Class' to 'Course').
'''
import java.util.ArrayList;
import java.util.List;
public class ClassService {
    // A static list to simulate course data from a database.
    private static final List<Course> COURSES = new ArrayList<>(); // Renamed from CLASSES to COURSES
    static {
        // Populate with some dummy course data.
        COURSES.add(new Course("CS101", "Introduction to Programming",
                "Covers fundamental programming concepts using Java.", "Mon/Wed 10:00-11:30 AM", "Dr. Jane Doe"));
        COURSES.add(new Course("MA201", "Calculus I",
                "First course in calculus, covering limits, derivatives, and integrals.", "Tue/Thu 1:00-2:30 PM", "Prof. John Smith"));
        COURSES.add(new Course("PH105", "General Physics",
                "An introductory course to classical mechanics and thermodynamics.", "Mon/Wed/Fri 9:00-9:50 AM", "Dr. Emily White"));
        COURSES.add(new Course("HI320", "World History since 1500",
                "A survey of major global historical developments from the Early Modern period to the present.", "Tue/Thu 11:00-12:15 PM", "Prof. Robert Green"));
        COURSES.add(new Course("EN101", "Composition and Rhetoric",
                "Develops critical thinking, reading, and writing skills.", "Mon/Wed 1:00-2:30 PM", "Ms. Alice Brown"));
    }
    /**
     * Retrieves a list of all available courses (referred to as classes in user context).
     * This method simulates a database query.
     *
     * @return A list of Course objects.
     */
    public static List<Course> getAllCourses() { // Method name changed to reflect Course type
        // Returns a new ArrayList to prevent external modifications to the static list.
        return new ArrayList<>(COURSES); // Returns COURSES
    }
}