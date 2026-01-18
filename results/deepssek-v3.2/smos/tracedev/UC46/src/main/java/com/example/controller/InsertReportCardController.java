package com.example.controller;

import com.example.dto.ClassDTO;
import com.example.dto.InsertReportCardCommand;
import com.example.dto.StudentDTO;
import com.example.entity.ClassEntity;
import com.example.entity.ReportCardEntity;
import com.example.entity.StudentEntity;
import com.example.repository.ClassRepository;
import com.example.repository.ReportCardRepository;
import com.example.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for inserting report cards.
 */
public class InsertReportCardController {
    private ClassRepository classRepository;
    private StudentRepository studentRepository;
    private ReportCardRepository reportCardRepository;

    public InsertReportCardController(ClassRepository classRepository,
                                      StudentRepository studentRepository,
                                      ReportCardRepository reportCardRepository) {
        this.classRepository = classRepository;
        this.studentRepository = studentRepository;
        this.reportCardRepository = reportCardRepository;
    }

    /**
     * Gets classes for a given academic year.
     * @param year the academic year
     * @return list of ClassDTO objects
     */
    public List<ClassDTO> getClassesByAcademicYear(int year) {
        List<ClassEntity> entities = classRepository.findAllByAcademicYear(year);
        return entities.stream()
                .map(entity -> new ClassDTO(entity.getId(), entity.getName(), entity.getAcademicYear()))
                .collect(Collectors.toList());
    }

    /**
     * Gets students for a given class.
     * @param classId the class id
     * @return list of StudentDTO objects
     */
    public List<StudentDTO> getStudentsByClass(int classId) {
        List<StudentEntity> entities = studentRepository.findAllByClassId(classId);
        return entities.stream()
                .map(entity -> new StudentDTO(entity.getId(), entity.getFullName(), entity.getClassId()))
                .collect(Collectors.toList());
    }

    /**
     * Inserts a new report card.
     * @param command the insert command containing report card data
     * @return true if insertion successful, false otherwise
     */
    public boolean insertReportCard(InsertReportCardCommand command) {
        try {
            // Check if a report card already exists for this student and academic year
            if (reportCardRepository.existsByStudentAndAcademicYear(command.getStudentId(), command.getAcademicYear())) {
                System.err.println("Report card already exists for student " + command.getStudentId() + " in academic year " + command.getAcademicYear());
                return false;
            }

            // Create a new ReportCardEntity from the command
            ReportCardEntity entity = new ReportCardEntity(
                    command.getStudentId(),
                    command.getClassId(),
                    command.getAcademicYear(),
                    command.getGrades()
            );

            // Save the entity
            ReportCardEntity saved = reportCardRepository.save(entity);
            return saved != null;
        } catch (Exception e) {
            System.err.println("Exception occurred while inserting report card: " + e.getMessage());
            return false;
        }
    }

    /**
     * Cleanup method called when the operation is cancelled.
     */
    public void cleanup() {
        // Perform any necessary cleanup (e.g., releasing resources)
        System.out.println("InsertReportCardController cleanup performed.");
    }
}