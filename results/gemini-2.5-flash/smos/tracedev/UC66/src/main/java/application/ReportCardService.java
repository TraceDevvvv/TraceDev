package application;

import domain.dto.AcademicYearDTO;
import domain.dto.ClassSummaryDTO;
import domain.dto.QuadrimestreDTO;
import domain.dto.ReportCardDTO;
import domain.dto.StudentSummaryDTO;
import infrastructure.dao.AcademicYearDAO;
import infrastructure.dao.ClassDAO;
import infrastructure.dao.QuadrimestreDAO;
import infrastructure.dao.ReportCardDAO;
import infrastructure.dao.StudentDAO;
import infrastructure.repository.IAcademicYearRepository;
import infrastructure.repository.IClassRepository;
import infrastructure.repository.IQuadrimestreRepository;
import infrastructure.repository.IReportCardRepository;
import infrastructure.repository.IStudentRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Application Service for managing Report Card operations.
 * This service orchestrates interactions between the presentation layer and the persistence layer,
 * handling business logic and data mapping between DAOs and DTOs.
 */
public class ReportCardService {

    private final IAcademicYearRepository academicYearRepository;
    private final IClassRepository classRepository;
    private final IStudentRepository studentRepository;
    private final IQuadrimestreRepository quadrimestreRepository;
    private final IReportCardRepository reportCardRepository;

    /**
     * Constructs a ReportCardService with necessary repositories injected.
     * @param academicYearRepository Repository for academic year data.
     * @param classRepository Repository for class data.
     * @param studentRepository Repository for student data.
     * @param quadrimestreRepository Repository for quadrimestre data.
     * @param reportCardRepository Repository for report card data.
     */
    public ReportCardService(IAcademicYearRepository academicYearRepository,
                             IClassRepository classRepository,
                             IStudentRepository studentRepository,
                             IQuadrimestreRepository quadrimestreRepository,
                             IReportCardRepository reportCardRepository) {
        this.academicYearRepository = academicYearRepository;
        this.classRepository = classRepository;
        this.studentRepository = studentRepository;
        this.quadrimestreRepository = quadrimestreRepository;
        this.reportCardRepository = reportCardRepository;
    }

    /**
     * Retrieves all available academic years.
     * @return A list of AcademicYearDTOs.
     */
    public List<AcademicYearDTO> getAcademicYears() {
        // Calls the infrastructure layer to get DAOs
        List<AcademicYearDAO> daos = academicYearRepository.findAll();
        System.out.println("Service: Map DAOs to DTOs"); // Traces m8
        // Maps DAOs to DTOs for the presentation layer
        return daos.stream()
                   .map(dao -> new AcademicYearDTO(dao.getId(), dao.getYear()))
                   .collect(Collectors.toList());
    }

    /**
     * Retrieves classes for a specific academic year.
     * @param yearId The ID of the academic year.
     * @return A list of ClassSummaryDTOs.
     */
    public List<ClassSummaryDTO> getClassesByAcademicYear(String yearId) {
        // Calls the infrastructure layer to get DAOs
        List<ClassDAO> daos = classRepository.findByAcademicYear(yearId);
        System.out.println("Service: Map DAOs to DTOs"); // Traces m17
        // Maps DAOs to DTOs
        return daos.stream()
                   .map(dao -> new ClassSummaryDTO(dao.getId(), dao.getName()))
                   .collect(Collectors.toList());
    }

    /**
     * Retrieves students belonging to a specific class.
     * @param classId The ID of the class.
     * @return A list of StudentSummaryDTOs.
     */
    public List<StudentSummaryDTO> getStudentsByClass(String classId) {
        // Calls the infrastructure layer to get DAOs
        List<StudentDAO> daos = studentRepository.findByClass(classId);
        System.out.println("Service: Map DAOs to DTOs"); // Traces m27
        // Maps DAOs to DTOs
        return daos.stream()
                   .map(dao -> new StudentSummaryDTO(dao.getId(), dao.getName()))
                   .collect(Collectors.toList());
    }

    /**
     * Retrieves all available quadrimestres.
     * @return A list of QuadrimestreDTOs.
     */
    public List<QuadrimestreDTO> getQuadrimestres() {
        // Calls the infrastructure layer to get DAOs
        List<QuadrimestreDAO> daos = quadrimestreRepository.findAll();
        System.out.println("Service: Map DAOs to DTOs"); // Traces m37
        // Maps DAOs to DTOs
        return daos.stream()
                   .map(dao -> new QuadrimestreDTO(dao.getId(), dao.getName()))
                   .collect(Collectors.toList());
    }

    /**
     * Generates a comprehensive report card for a student and quadrimestre.
     * This method retrieves data from multiple repositories and assembles it into a single DTO.
     * Ensures data accuracy and freshness (QR-001) by fetching current details.
     * @param studentId The ID of the student.
     * @param quadrimestreId The ID of the quadrimestre.
     * @return A ReportCardDTO containing all relevant information.
     */
    public ReportCardDTO generateReportCard(String studentId, String quadrimestreId) {
        // Step 1: Get the core report card data
        ReportCardDAO reportCardDAO = reportCardRepository.findByStudentAndQuadrimestre(studentId, quadrimestreId);
        if (reportCardDAO == null) {
            System.out.println("No report card found for student " + studentId + " and quadrimestre " + quadrimestreId);
            return null; // Or throw an exception
        }

        // Step 2: Get student details
        StudentDAO studentDAO = studentRepository.findById(studentId);
        if (studentDAO == null) {
            System.out.println("Student not found for ID: " + studentId);
            return null;
        }

        // Step 3: Get class details
        ClassDAO classDAO = classRepository.findById(studentDAO.getClassId());
        if (classDAO == null) {
            System.out.println("Class not found for ID: " + studentDAO.getClassId());
            return null;
        }

        // Step 4: Get academic year details
        AcademicYearDAO academicYearDAO = academicYearRepository.findAll().stream()
                                                            .filter(ay -> ay.getId().equals(classDAO.getAcademicYearId()))
                                                            .findFirst()
                                                            .orElse(null);
        if (academicYearDAO == null) {
            System.out.println("Academic year not found for ID: " + classDAO.getAcademicYearId());
            return null;
        }

        // Step 5: Get quadrimestre details
        QuadrimestreDAO quadrimestreDAO = quadrimestreRepository.findById(quadrimestreId);
        if (quadrimestreDAO == null) {
            System.out.println("Quadrimestre not found for ID: " + quadrimestreId);
            return null;
        }

        // Map gradesData string to Map<String, String>
        // Assumption: gradesData is a comma-separated string of "Subject:Grade" pairs, e.g., "Math:A,Science:B+"
        Map<String, String> grades = new HashMap<>();
        if (reportCardDAO.getGradesData() != null && !reportCardDAO.getGradesData().isEmpty()) {
            Arrays.stream(reportCardDAO.getGradesData().split(","))
                  .forEach(gradeEntry -> {
                      String[] parts = gradeEntry.split(":");
                      if (parts.length == 2) {
                          grades.put(parts[0].trim(), parts[1].trim());
                      }
                  });
        }
        System.out.println("Service: Map ReportCardDAO to ReportCardDTO.\nEnsures data accuracy."); // Traces m50

        // Assemble the ReportCardDTO
        return new ReportCardDTO(
            studentDAO.getName(),
            classDAO.getName(),
            academicYearDAO.getYear(),
            quadrimestreDAO.getName(),
            grades,
            reportCardDAO.getComments()
        );
    }
}