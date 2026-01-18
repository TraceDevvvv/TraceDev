'''
Simulates database operations for the report card system
In a real application, this would connect to a real database
'''
import models.SchoolClass;
import models.Student;
import models.ReportCard;
import java.util.*;
public class DatabaseSimulator {
    private static List<SchoolClass> classes = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();
    private static List<ReportCard> reportCards = new ArrayList<>();
    public static void initialize() {
        classes.add(new SchoolClass("C1", "Grade 10A", "2023-2024", "Mr. Johnson"));
        classes.add(new SchoolClass("C2", "Grade 10B", "2023-2024", "Ms. Smith"));
        classes.add(new SchoolClass("C3", "Grade 11A", "2023-2024", "Dr. Williams"));
        classes.add(new SchoolClass("C4", "Grade 11B", "2023-2024", "Mrs. Brown"));
        students.add(new Student("S1001", "John", "Doe", "2007-05-15", "C1"));
        students.add(new Student("S1002", "Jane", "Smith", "2007-08-22", "C1"));
        students.add(new Student("S1003", "Bob", "Johnson", "2007-03-10", "C1"));
        students.add(new Student("S1004", "Alice", "Williams", "2007-11-30", "C2"));
        students.add(new Student("S1005", "Charlie", "Brown", "2007-01-25", "C2"));
        students.add(new Student("S1006", "Eva", "Davis", "2006-09-05", "C3"));
        students.add(new Student("S1007", "Frank", "Miller", "2006-07-18", "C3"));
        students.add(new Student("S1008", "Grace", "Wilson", "2006-12-12", "C4"));
    }
    public static List<SchoolClass> getClassesForCurrentYear() {
        String currentYear = "2023-2024";
        List<SchoolClass> result = new ArrayList<>();
        for (SchoolClass c : classes) {
            if (currentYear.equals(c.getAcademicYear())) {
                result.add(c);
            }
        }
        return result;
    }
    public static List<Student> getStudentsByClass(SchoolClass classObj) {
        List<Student> result = new ArrayList<>();
        if (classObj == null) {
            return result;
        }
        for (Student s : students) {
            if (classObj.getClassId().equals(s.getClassId())) {
                result.add(s);
            }
        }
        return result;
    }
    public static boolean saveReportCard(ReportCard reportCard) {
        if (Math.random() < 0.1) {
            return false;
        }
        reportCards.add(reportCard);
        System.out.println("Report card saved: " + reportCard.getReportId() +
                          " for student " + reportCard.getStudentId());
        System.out.println("Grades: " + reportCard.getGrades());
        System.out.println("Average: " + reportCard.calculateAverage());
        return true;
    }
    public static List<ReportCard> getReportCardsByStudent(String studentId) {
        List<ReportCard> result = new ArrayList<>();
        for (ReportCard rc : reportCards) {
            if (studentId.equals(rc.getStudentId())) {
                result.add(rc);
            }
        }
        return result;
    }
    public static SchoolClass getClassById(String classId) {
        for (SchoolClass c : classes) {
            if (classId.equals(c.getClassId())) {
                return c;
            }
        }
        return null;
    }
    public static Student getStudentById(String studentId) {
        for (Student s : students) {
            if (studentId.equals(s.getStudentId())) {
                return s;
            }
        }
        return null;
    }
}