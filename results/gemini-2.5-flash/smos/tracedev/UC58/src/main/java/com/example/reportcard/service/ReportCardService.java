package com.example.reportcard.service;

import com.example.reportcard.domain.AcademicYear;
import com.example.reportcard.domain.Class;
import com.example.reportcard.domain.ReportCard;
import com.example.reportcard.domain.Student;
import com.example.reportcard.dto.AcademicYearDTO;
import com.example.reportcard.dto.ClassDTO;
import com.example.reportcard.dto.ReportCardDTO;
import com.example.reportcard.dto.StudentDTO;
import com.example.reportcard.exception.NetworkException;
import com.example.reportcard.repository.IAcademicYearRepository;
import com.example.reportcard.repository.IClassRepository;
import com.example.reportcard.repository.IReportCardRepository;
import com.example.reportcard.repository.IStudentRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Application service for managing report card related business logic.
 * Orchestrates calls to repositories and performs DTO mapping.
 */
public class ReportCardService {
    private final IAcademicYearRepository academicYearRepository;
    private final IClassRepository classRepository;
    private final IStudentRepository studentRepository;
    private final IReportCardRepository reportCardRepository;
    private final INetworkConnectivityService networkConnectivityService;

    public ReportCardService(IAcademicYearRepository academicYearRepository,
                             IClassRepository classRepository,
                             IStudentRepository studentRepository,
                             IReportCardRepository reportCardRepository,
                             INetworkConnectivityService networkConnectivityService) {
        this.academicYearRepository = academicYearRepository;
        this.classRepository = classRepository;
        this.studentRepository = studentRepository;
        this.reportCardRepository = reportCardRepository;
        this.networkConnectivityService = networkConnectivityService;
    }

    /**
     * Checks network connectivity before proceeding with data operations.
     * @throws NetworkException if the network is down.
     */
    public void checkNetworkConnectivity() throws NetworkException {
        networkConnectivityService.checkConnection();
    }

    /**
     * Retrieves a list of academic years for a given professor.
     * @param professorId The ID of the professor.
     * @return A list of AcademicYearDTOs.
     */
    public List<AcademicYearDTO> getProfessorAcademicYears(String professorId) {
        // No network check here, assumed to be done by the caller (Controller)
        List<AcademicYear> academicYears = academicYearRepository.findYearsByProfessorId(professorId);
        return mapAcademicYearsToDTOs(academicYears); // Traces m11
    }

    /**
     * Retrieves a list of classes for a specific academic year and professor.
     * @param academicYearId The ID of the academic year.
     * @param professorId The ID of the professor.
     * @return A list of ClassDTOs.
     */
    public List<ClassDTO> getClassesForAcademicYearAndProfessor(String academicYearId, String professorId) {
        // No network check here, assumed to be done by the caller (Controller)
        List<Class> classes = classRepository.findClassesByAcademicYearAndProfessor(academicYearId, professorId);
        return mapClassesToDTOs(classes); // Traces m30
    }

    /**
     * Retrieves a list of students for a specific class.
     * @param classId The ID of the class.
     * @return A list of StudentDTOs.
     */
    public List<StudentDTO> getStudentsForClass(String classId) {
        // No network check here, assumed to be done by the caller (Controller)
        List<Student> students = studentRepository.findStudentsByClassId(classId);
        return mapStudentsToDTOs(students); // Traces m47
    }

    /**
     * Retrieves a student's report card for a specific academic year and quarter.
     * This method orchestrates calls to multiple repositories to build the complete DTO.
     * @param studentId The ID of the student.
     * @param academicYearId The ID of the academic year.
     * @param quarter The quarter (e.g., "Q1", "Q2").
     * @return A ReportCardDTO containing the student's report card details.
     */
    public ReportCardDTO retrieveStudentReportCard(String studentId, String academicYearId, String quarter) {
        // No network check here, assumed to be done by the caller (Controller)
        ReportCard reportCard = reportCardRepository.findByStudentYearAndQuarter(studentId, academicYearId, quarter);
        if (reportCard == null) {
            // Handle case where report card is not found
            System.out.println("[ReportCardService] No report card found for student " + studentId +
                               ", year " + academicYearId + ", quarter " + quarter);
            return null; // Or throw a specific exception
        }

        Student student = studentRepository.findById(studentId);
        AcademicYear academicYear = academicYearRepository.findById(academicYearId);

        // Map entities to ReportCardDTO // Traces m75
        return mapToDTO(reportCard, student, academicYear);
    }

    // Private helper methods for DTO mapping to explicitly trace sequence diagram messages

    private List<AcademicYearDTO> mapAcademicYearsToDTOs(List<AcademicYear> academicYears) { // Traces m11
        return academicYears.stream()
                .map(year -> new AcademicYearDTO(year.getId(), year.getYear()))
                .collect(Collectors.toList());
    }

    private List<ClassDTO> mapClassesToDTOs(List<Class> classes) { // Traces m30
        return classes.stream()
                .map(c -> new ClassDTO(c.getId(), c.getName()))
                .collect(Collectors.toList());
    }

    private List<StudentDTO> mapStudentsToDTOs(List<Student> students) { // Traces m47
        return students.stream()
                .map(s -> new StudentDTO(s.getId(), s.getName()))
                .collect(Collectors.toList());
    }

    private ReportCardDTO mapToDTO(ReportCard reportCard, Student student, AcademicYear academicYear) { // Traces m75
        String studentName = (student != null) ? student.getName() : "Unknown Student";
        String academicYearName = (academicYear != null) ? academicYear.getYear() : "Unknown Year";

        return new ReportCardDTO(
                studentName,
                academicYearName,
                reportCard.getQuarter(),
                reportCard.getGrades()
        );
    }
}