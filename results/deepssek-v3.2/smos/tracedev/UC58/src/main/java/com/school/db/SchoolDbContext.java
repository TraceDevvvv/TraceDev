
package com.school.db;

import com.school.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Simulated database context. In a real application, this would be an ORM context (e.g., JPA EntityManager).
 */
public class SchoolDbContext {
    // Simulated in-memory storage
    private List<AcademicYear> academicYears = new ArrayList<>();
    private List<com.school.model.Class> classes = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    private List<ReportCard> reportCards = new ArrayList<>();
    private List<Professor> professors = new ArrayList<>();

    public SchoolDbContext() {
        // Seed some sample data for demonstration
        initializeData();
    }

    private void initializeData() {
        // AcademicYears
        academicYears.add(new AcademicYear(1, "2023-2024"));
        academicYears.add(new AcademicYear(2, "2024-2025"));

        // Classes
        classes.add(new com.school.model.Class(101, "Mathematics 10A", 1));
        classes.add(new com.school.model.Class(102, "Physics 10B", 1));
        classes.add(new com.school.model.Class(103, "Chemistry 11A", 2));

        // Students
        students.add(new Student(1001, "Alice Johnson", 101));
        students.add(new Student(1002, "Bob Smith", 101));
        students.add(new Student(1003, "Charlie Brown", 102));
        students.add(new Student(1004, "Diana Prince", 103));

        // ReportCards
        // Assuming a simple map for grades
        reportCards.add(new ReportCard(5001, 1001, 1, java.util.Map.of("Math", "A", "Physics", "B"), "Good performance."));
        reportCards.add(new ReportCard(5002, 1002, 1, java.util.Map.of("Math", "B+", "Physics", "A-"), "Needs improvement in Math."));

        // Professors
        professors.add(new Professor(1, "Dr. Smith", true));
        professors.add(new Professor(2, "Prof. Davis", false));
    }

    // Simulated DbSet getters
    public List<AcademicYear> getAcademicYears() {
        return academicYears;
    }

    public List<com.school.model.Class> getClasses() {
        return classes;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<ReportCard> getReportCards() {
        return reportCards;
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public int saveChanges() {
        // Simulate saving changes, returning number of affected records.
        // In a real implementation, this would persist changes to a database.
        return 0;
    }

    // Helper methods for querying (simulating LINQ/JPQL)
    public List<AcademicYear> queryAcademicYearsForProfessor(int professorId) {
        // In a real scenario, we would join with Professor->Class->AcademicYear.
        // For simplicity, we assume the professor teaches classes in these academic years.
        // We return all academic years for demonstration.
        return new ArrayList<>(academicYears);
    }

    public List<com.school.model.Class> queryClassesForProfessorAndAcademicYear(int professorId, int academicYearId) {
        // Simulate: professor teaches classes in given academic year.
        List<com.school.model.Class> result = new ArrayList<>();
        for (com.school.model.Class c : classes) {
            if (c.getAcademicYearId() == academicYearId) {
                // Assume professor teaches this class if class id is odd (just for demo)
                if (c.getId() % 2 == 1) {
                    result.add(c);
                }
            }
        }
        return result;
    }

    public List<Student> queryStudentsInClass(int classId) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getClassId() == classId) {
                result.add(s);
            }
        }
        return result;
    }

    public ReportCard queryReportCardForStudentAndQuarter(int studentId, int quarter) {
        for (ReportCard rc : reportCards) {
            if (rc.getStudentId() == studentId && rc.getQuarter() == quarter) {
                return rc;
            }
        }
        return null; // not found
    }
}
