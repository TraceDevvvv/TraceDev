```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents an Academic Year.
 * Each academic year is identified by a unique string (e.g., "2023-2024").
 */
class AcademicYear {
    private String year; // e.g., "2023-2024"

    /**
     * Constructs an AcademicYear object.
     * @param year The string representation of the academic year.
     */
    public AcademicYear(String year) {
        if (year == null || year.trim().isEmpty()) {
            throw new IllegalArgumentException("Academic year cannot be null or empty.");
        }
        this.year = year;
    }

    /**
     * Returns the string representation of the academic year.
     * @return The academic year string.
     */
    public String getYear() {
        return year;
    }

    /**
     * Overrides toString to provide a meaningful representation.
     * @return The academic year string.
     */
    @Override
    public String toString() {
        return year;
    }

    /**
     * Compares this AcademicYear to the specified object.
     * The result is true if and only if the argument is not null and is an AcademicYear object
     * that represents the same academic year as this object.
     * @param o The object to compare this AcademicYear against.
     * @return true if the given object represents an AcademicYear equivalent to this AcademicYear, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicYear that = (AcademicYear) o;
        return Objects.equals(year, that.year);
    }

    /**
     * Returns a hash code value for the object.
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(year);
    }
}

/**
 * Represents a Course offered in the system.
 * Each course has a unique ID and a name.
 */
class Course {
    private String courseId;
    private String name;

    /**
     * Constructs a Course object.
     * @param courseId The unique identifier for the course.
     * @param name The name of the course.
     */
    public Course(String courseId, String name) {
        if (courseId == null || courseId.trim().isEmpty() || name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Course ID and name cannot be null or empty.");
        }
        this.courseId = courseId;
        this.name = name;
    }

    /**
     * Returns the course ID.
     * @return The course ID.
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * Returns the course name.
     * @return The course name.
     */
    public String getName() {
        return name;
    }

    /**
     * Overrides toString to provide a meaningful representation.
     * @return A string representation of the course.
     */
    @Override
    public String toString() {
        return name + " (" + courseId + ")";
    }

    /**
     * Compares this Course to the specified object.
     * @param o The object to compare this Course against.
     * @return true if the given object represents a Course equivalent to this Course, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseId, course.courseId);
    }

    /**
     * Returns a hash code value for the object.
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(courseId);
    }
}

/**
 * Represents a Teacher in the system.
 * Each teacher has a unique ID and a name.
 */
class Teacher {
    private String teacherId;
    private String name;

    /**
     * Constructs a Teacher object.
     * @param teacherId The unique identifier for the teacher.
     * @param name The name of the teacher.
     */
    public Teacher(String teacherId, String name) {
        if (teacherId == null || teacherId.trim().isEmpty() || name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Teacher ID and name cannot be null or empty.");
        }
        this.teacherId = teacherId;
        this.name = name;
    }

    /**
     * Returns the teacher ID.
     * @return The teacher ID.
     */
    public String getTeacherId() {
        return teacherId;
    }

    /**
     * Returns the teacher's name.
     * @return The teacher's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Overrides toString to provide a meaningful representation.
     * @return A string representation of the teacher.
     */
    @Override
    public String toString() {
        return name + " (ID: " + teacherId + ")";
    }

    /**
     * Compares this Teacher to the specified object.
     * @param o The object to compare this Teacher against.
     * @return true if the given object represents a Teacher equivalent to this Teacher, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(teacherId, teacher.teacherId);
    }

    /**
     * Returns a hash code value for the object.
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(teacherId);
    }
}

/**
 * Represents a specific instance of a class being taught.
 * This links a Course, an AcademicYear, and a Teacher.
 */
class Class {
    private String classId; // Unique ID for this specific class instance (e.g., "MATH101-A-2023")
    private Course course;
    private AcademicYear academicYear;
    private Teacher teacher;
    private String section; // e.g., "A", "B"

    /**
     * Constructs a Class object.
     * @param classId A unique identifier for this class instance.
     * @param course The course being taught.
     * @param academicYear The academic year in which the class is taught.
     * @param teacher The teacher assigned to this class.
     * @param section The section identifier for the class (e.g., "A", "B").
     */
    public Class(String classId, Course course, AcademicYear academicYear, Teacher teacher, String section) {
        if (classId == null || classId.trim().isEmpty() || course == null || academicYear == null || teacher == null || section == null || section.trim().isEmpty()) {
            throw new IllegalArgumentException("All class fields must be non-null and non-empty.");
        }
        this.classId = classId;
        this.course = course;
        this.academicYear = academicYear;
        this.teacher = teacher;
        this.section = section;
    }

    /**
     * Returns the unique ID of this class instance.
     * @return The class ID.
     */
    public String getClassId() {
        return classId;
    }

    /**
     * Returns the Course associated with this class.
     * @return The Course object.
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Returns the AcademicYear associated with this class.
     * @return The AcademicYear object.
     */
    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    /**
     * Returns the Teacher assigned to this class.
     * @return The Teacher object.
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * Returns the section identifier for this class.
     * @return The section string.
     */
    public String getSection() {
        return section;
    }

    /**
     * Overrides toString to provide a meaningful representation.
     * @return A string representation of the class.
     */
    @Override
    public String toString() {
        return String.format("%s - Section %s (%s) - Teacher: %s",
                course.getName(), section, academicYear.getYear(), teacher.getName());
    }

    /**
     * Compares this Class to the specified object.
     * @param o The object to compare this Class against.
     * @return true if the given object represents a Class equivalent to this Class, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Class aClass = (Class) o;
        return Objects.equals(classId, aClass.classId);
    }

    /**
     * Returns a hash code value for the object.
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(classId);
    }
}

/**
 * Simulates the user session, holding information about the currently logged-in teacher.
 * This is a static utility class to represent a global session state.
 */
class TeacherSession {
    private static Teacher loggedInTeacher;

    /**
     * Logs in a teacher to the system.
     * @param teacher The teacher to log in.
     */
    public static void login(Teacher teacher) {
        if (teacher == null) {
            throw new IllegalArgumentException("Teacher cannot be null for login.");
        }
        loggedInTeacher = teacher;
        System.out.println("Teacher " + teacher.getName() + " logged in successfully.");
    }

    /**
     * Returns the currently logged-in teacher.
     * @return The logged-in Teacher object, or null if no teacher is logged in.
     */
    public static Teacher getLoggedInTeacher() {
        return loggedInTeacher;
    }

    /**
     * Checks if a teacher is currently logged in.
     * @return true if a teacher is logged in, false otherwise.
     */
    public static boolean isLoggedIn() {
        return loggedInTeacher != null;
    }

    /**
     * Logs out the current teacher.
     */
    public static void logout() {
        if (loggedInTeacher != null) {
            System.out.println("Teacher " + loggedInTeacher.getName() + " logged out.");
        }
        loggedInTeacher = null;
    }
}

/**
 * Simulates the SMOS (School Management and Operations System) server.
 * This class provides static methods to retrieve data, mimicking a backend service.
 * It holds sample data for demonstration purposes.
 */
class SMOSServer {
    private static List<Class> allClasses;

    /**
     * Initializes the sample data for the SMOS server.
     * This method should be called once at the application startup.
     */
    public static void initializeData() {
        allClasses = new ArrayList<>();

        // Define Teachers
        Teacher teacher1 = new Teacher("T001", "Alice Smith");
        Teacher teacher2 = new Teacher("T002", "Bob Johnson");
        Teacher teacher3 = new Teacher("T003", "Charlie Brown");

        // Define Academic Years
        AcademicYear year2022_2023 = new AcademicYear("2022-2023");
        AcademicYear year2023_2024 = new AcademicYear("2023-2024");
        AcademicYear year2024_2025 = new AcademicYear("2024-2025");

        // Define Courses
        Course math101 = new Course("MATH101", "Calculus I");
        Course phys201 = new Course("PHYS201", "Physics Fundamentals");
        Course comp301 = new Course("COMP301", "Data Structures");
        Course hist101 = new Course("HIST101", "World History");
        Course lit201 = new Course("LIT201", "English Literature");
        Course chem101 = new Course("CHEM101", "General Chemistry");

        // Populate classes
        // Teacher Alice Smith (T001)
        allClasses.add(new Class("C001", math101, year2023_2024, teacher1, "A"));
