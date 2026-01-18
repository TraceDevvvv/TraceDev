```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents a user in the system.
 */
class User {
    public enum Role {
        ADMINISTRATOR,
        TEACHER,
        STUDENT
    }

    private final String userId;
    private final String username;
    private final String password;
    private final Role role;

    public User(String userId, String username, String password, Role role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
               "userId='" + userId + '\'' +
               ", username='" + username + '\'' +
               ", role=" + role +
               '}';
    }
}

/**
 * Represents an academic year.
 */
class AcademicYear {
    private final String yearId;
    private final String name; // e.g., "2023-2024"
    private final boolean isCurrent;

    public AcademicYear(String yearId, String name, boolean isCurrent) {
        this.yearId = yearId;
        this.name = name;
        this.isCurrent = isCurrent;
    }

    public String getYearId() {
        return yearId;
    }

    public String getName() {
        return name;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    @Override
    public String toString() {
        return "AcademicYear{" +
               "yearId='" + yearId + '\'' +
               ", name='" + name + '\'' +
               ", isCurrent=" + isCurrent +
               '}';
    }
}

/**
 * Represents a subject taught in school.
 */
class Subject {
    private final String subjectId;
    private final String name;

    public Subject(String subjectId, String name) {
        this.subjectId = subjectId;
        this.name = name;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Subject{" +
               "subjectId='" + subjectId + '\'' +
               ", name='" + name + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(subjectId, subject.subjectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectId);
    }
}

/**
 * Represents a grade obtained by a student in a specific subject.
 */
class Grade {
    private final Subject subject;
    private double score;

    public Grade(Subject subject, double score) {
        this.subject = subject;
        this.score = score;
    }

    public Subject getSubject() {
        return subject;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Grade{" +
               "subject=" + subject.getName() +
               ", score=" + score +
               '}';
    }
}

/**
 * Represents a student's report card, containing grades for various subjects.
 */
class ReportCard {
    private final String reportCardId;
    private final String studentId;
    private final String academicYearId;
    // Map to store grades, where key is Subject and value is Grade object
    private final Map<String, Grade> grades; // Key is subjectId

    public ReportCard(String reportCardId, String studentId, String academicYearId) {
        this.reportCardId = reportCardId;
        this.studentId = studentId;
        this.academicYearId = academicYearId;
        this.grades = new HashMap<>();
    }

    public String getReportCardId() {
        return reportCardId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getAcademicYearId() {
        return academicYearId;
    }

    public Map<String, Grade> getGrades() {
        return grades;
    }

    /**
     * Adds or updates a grade for a specific subject.
     * @param subject The subject for which the grade is being added/updated.
     * @param score The score obtained.
     */
    public void addOrUpdateGrade(Subject subject, double score) {
        grades.put(subject.getSubjectId(), new Grade(subject, score));
    }

    /**
     * Retrieves a grade for a specific subject.
     * @param subjectId The ID of the subject.
     * @return An Optional containing the Grade if found, otherwise empty.
     */
    public Optional<Grade> getGradeBySubjectId(String subjectId) {
        return Optional.ofNullable(grades.get(subjectId));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ReportCard ID: ").append(reportCardId).append("\n");
        sb.append("Student ID: ").append(studentId).append("\n");
        sb.append("Academic Year ID: ").append(academicYearId).append("\n");
        sb.append("Grades:\n");
        if (grades.isEmpty()) {
            sb.append("  No grades entered yet.\n");
        } else {
            grades.values().forEach(grade -> sb.append("  - ").append(grade.getSubject().getName()).append(": ").append(grade.getScore()).append("\n"));
        }
        return sb.toString();
    }
}

/**
 * Represents a student enrolled in the school.
 */
class Student {
    private final String studentId;
    private final String firstName;
    private final String lastName;
    private final String classId; // The class the student belongs to

    public Student(String studentId, String firstName, String lastName, String classId) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.classId = classId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getClassId() {
        return classId;
    }

    @Override
    public String toString() {
        return "Student{" +
               "studentId='" + studentId + '\'' +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               '}';
    }
}

/**
 * Represents a school class (e.g., "10A").
 */
class SchoolClass {
    private final String classId;
    private final String className;
    private final String academicYearId;
    private final List<Student> students;

    public SchoolClass(String classId, String className, String academicYearId) {
        this.classId = classId;
        this.className = className;
        this.academicYearId = academicYearId;
        this.students = new ArrayList<>();
    }

    public String getClassId() {
        return classId;
    }

    public String getClassName() {
        return className;
    }

    public String getAcademicYearId() {
        return academicYearId;
    }

    public List<Student> getStudents() {
        return students;
    }

    /**
     * Adds a student to this class.
     * @param student The student to add.
     */
    public void addStudent(Student student) {
        this.students.add(student);
    }

    @Override
    public String toString() {
        return "SchoolClass{" +
               "classId='" + classId + '\'' +
               ", className='" + className + '\'' +
               ", academicYearId='" + academicYearId + '\'' +
               '}';
    }
}

/**
 * Service for user authentication.
 */
class AuthService {
    private final Map<String, User> users; // username -> User

    public AuthService() {
        this.users = new HashMap<>();
        initializeData();
    }

    /**
     * Initializes dummy user data.
     */
    private void initializeData() {
        users.put("admin", new User("U001", "admin", "adminpass", User.Role.ADMINISTRATOR));
        users.put("teacher", new User("U002", "teacher", "teacherpass", User.Role.TEACHER));
        users.put("student", new User("U003", "student", "studentpass", User.Role.STUDENT));
    }

    /**
     * Attempts to log in a user.
     * @param username The username.
     * @param password The password.
     * @return The User object if login is successful, null otherwise.
     */
    public User login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful for user: " + username);
            return user;
        }
        System.out.println("Invalid username or password.");
        return null;
    }

    /**
     * Checks if the given user has administrator role.
     * @param user The user to check.
     * @return true if the user is an administrator, false otherwise.
     */
    public boolean isAdministrator(User user) {
        return user != null && user.getRole() == User.Role.ADMINISTRATOR;
    }
}

/**
 * Service for managing academic years, classes, students, and subjects.
 */
class SchoolService {
    private final Map<String, AcademicYear> academicYears; // yearId -> AcademicYear
    private final Map<String, SchoolClass> classes;       // classId -> SchoolClass
    private final Map<String, Student> students;          // studentId -> Student
    private final Map<String, Subject> subjects;          // subjectId -> Subject

    public SchoolService() {
        this.academicYears = new HashMap<>();
        this.classes = new HashMap<>();
        this.students = new HashMap<>();
        this.subjects = new HashMap<>();
        initializeData();
    }

    /**
     * Initializes dummy school data.
     */
    private void initializeData() {
        // Academic Years
        AcademicYear currentYear = new AcademicYear("AY2023", "2023-2024", true);
        AcademicYear previousYear = new AcademicYear("AY2022", "2022-2023", false);
        academicYears.put(currentYear.getYearId(), currentYear);
        academicYears.put(previousYear.getYearId(), previousYear);

        // Subjects
        Subject math = new Subject("SUB001", "Mathematics");
        Subject