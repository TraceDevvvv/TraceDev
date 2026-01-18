package com.example.reportcard.application;

import com.example.reportcard.dataaccess.IAcademicYearRepository;
import com.example.reportcard.dataaccess.IClassRepository;
import com.example.reportcard.dataaccess.IReportCardRepository;
import com.example.reportcard.dataaccess.IStudentRepository;
import com.example.reportcard.domain.AcademicYear;
import com.example.reportcard.domain.Class;
import com.example.reportcard.domain.Month;
import com.example.reportcard.domain.ReportCard;
import com.example.reportcard.domain.Student;

import java.util.List;

/**
 * The ReportCardService handles business logic related to report cards.
 * It acts as an intermediary between the presentation layer and the data access layer.
 */
public class ReportCardService {
    private IAcademicYearRepository academicYearRepository;
    private IClassRepository classRepository;
    private IStudentRepository studentRepository;
    private IReportCardRepository reportCardRepository;

    /**
     * Constructs a ReportCardService with necessary repositories.
     * This demonstrates dependency injection.
     *
     * @param yearRepo The repository for academic years.
     * @param classRepo The repository for classes.
     * @param studentRepo The repository for students.
     * @param reportRepo The repository for report cards.
     */
    public ReportCardService(IAcademicYearRepository yearRepo,
                             IClassRepository classRepo,
                             IStudentRepository studentRepo,
                             IReportCardRepository reportRepo) {
        this.academicYearRepository = yearRepo;
        this.classRepository = classRepo;
        this.studentRepository = studentRepo;
        this.reportCardRepository = reportRepo;
    }

    /**
     * Retrieves all available academic years.
     * @return A list of AcademicYear objects.
     */
    public List<AcademicYear> getAcademicYears() {
        System.out.println("Service: Getting all academic years.");
        List<AcademicYear> academicYearsList = academicYearRepository.findAll();
        return academicYearsList;
    }

    /**
     * Retrieves classes for a specific academic year.
     * @param yearId The ID of the academic year.
     * @return A list of Class objects.
     */
    public List<Class> getClassesByAcademicYear(String yearId) {
        System.out.println("Service: Getting classes for academic year: " + yearId);
        List<Class> classesList = classRepository.findByAcademicYear(yearId);
        return classesList;
    }

    /**
     * Retrieves students belonging to a specific class.
     * @param classId The ID of the class.
     * @return A list of Student objects.
     */
    public List<Student> getStudentsByClass(String classId) {
        System.out.println("Service: Getting students for class: " + classId);
        List<Student> studentsList = studentRepository.findByClass(classId);
        return studentsList;
    }

    /**
     * Generates a report card for a student for a given academic period.
     * @param studentId The ID of the student.
     * @param academicYearId The ID of the academic year.
     * @param months The list of months for the report period.
     * @return A ReportCard object, or null if no report card is found.
     * @throws RuntimeException if there's a problem generating the report (e.g., data access error).
     */
    public ReportCard generateReportCard(String studentId, String academicYearId, List<Month> months) {
        System.out.println("Service: Generating report card for student " + studentId +
                           " for year " + academicYearId + " and months " + months);
        // The repository will handle fetching and constructing the ReportCard object.
        return reportCardRepository.findByStudentAndPeriod(studentId, academicYearId, months);
    }
}