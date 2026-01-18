package com.example.domain;

import com.example.dto.GradeDTO;
import com.example.dto.ReportCardDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a report card for a student for a given semester.
 * Contains a list of grades and can calculate the average.
 */
public class ReportCard {
    private final String reportId;
    private final Student student;
    private final Semester semester;
    private final PupilClass pupilClass;
    private final List<Grade> grades;

    public ReportCard(String reportId, Student student, Semester semester, PupilClass pupilClass, List<Grade> grades) {
        this.reportId = Objects.requireNonNull(reportId);
        this.student = Objects.requireNonNull(student);
        this.semester = Objects.requireNonNull(semester);
        this.pupilClass = Objects.requireNonNull(pupilClass);
        this.grades = Objects.requireNonNull(grades);
    }

    public String getReportId() {
        return reportId;
    }

    public Student getStudent() {
        return student;
    }

    public Semester getSemester() {
        return semester;
    }

    public PupilClass getPupilClass() {
        return pupilClass;
    }

    public List<Grade> getGrades() {
        return new ArrayList<>(grades);
    }

    /**
     * Calculates the average of all grades.
     * If there are no grades, returns 0.0.
     * @return the arithmetic mean of grade values
     */
    public double calculateAverage() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (Grade g : grades) {
            sum += g.getValue();
        }
        return sum / grades.size();
    }

    /**
     * Internal method to convert domain Grade objects to GradeDTOs.
     * This is used as a helper for generateReportDTO().
     * @return list of GradeDTOs
     */
    private List<GradeDTO> formatGrades() {
        List<GradeDTO> dtos = new ArrayList<>();
        for (Grade grade : grades) {
            // In a real system, you might have additional logic (like comments)
            GradeDTO dto = new GradeDTO(
                    grade.getSubject(),
                    grade.getFormattedGrade(),
                    "" // no comments by default
            );
            dtos.add(dto);
        }
        return dtos;
    }

    /**
     * Creates a ReportCardDTO suitable for transmission to the view layer.
     * @return a fully populated ReportCardDTO
     */
    public ReportCardDTO generateReportDTO() {
        List<GradeDTO> gradeDTOs = formatGrades();
        double average = calculateAverage();
        return new ReportCardDTO(
                student.getFullName(),
                pupilClass.getClassName(),
                semester.getName(),
                gradeDTOs,
                average
        );
    }

    @Override
    public String toString() {
        return "ReportCard{" +
                "reportId='" + reportId + '\'' +
                ", student=" + student.getFullName() +
                ", average=" + calculateAverage() +
                '}';
    }
}