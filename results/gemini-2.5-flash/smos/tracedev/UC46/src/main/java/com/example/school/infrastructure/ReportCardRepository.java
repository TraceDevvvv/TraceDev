package com.example.school.infrastructure;

import com.example.school.domain.ReportCard;
import com.example.school.domain.Grade;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Concrete implementation of IReportCardRepository using a DatabaseContext.
 */
public class ReportCardRepository implements IReportCardRepository {
    private DatabaseContext dbContext;

    /**
     * Constructs a ReportCardRepository with a given DatabaseContext.
     * @param dbContext The database context to use for persistence.
     */
    public ReportCardRepository(DatabaseContext dbContext) {
        this.dbContext = dbContext;
    }

    /**
     * Saves a report card to the persistence store.
     * Simulates an INSERT operation into the database.
     * @param reportCard The ReportCard object to save.
     * @return true if the save operation was successful, false otherwise.
     */
    @Override
    public boolean save(ReportCard reportCard) {
        if (reportCard == null) {
            System.err.println("ReportCardRepository: Cannot save null report card.");
            return false;
        }
        if (!reportCard.isValid()) {
            System.err.println("ReportCardRepository: Cannot save invalid report card.");
            return false;
        }

        // Simulate INSERT INTO ReportCards
        String reportCardSql = "INSERT INTO ReportCards (id, studentId, academicYearId, term) VALUES (...)";
        dbContext.executeQuery(reportCardSql); // Log the query

        Map<String, Object> rcRow = new HashMap<>();
        rcRow.put("id", reportCard.getId());
        rcRow.put("studentId", reportCard.getStudentId());
        rcRow.put("academicYearId", reportCard.getAcademicYearId());
        rcRow.put("term", reportCard.getTerm());
        dbContext.reportCardsTable.add(rcRow);
        System.out.println("ReportCardRepository: Saved ReportCard with ID: " + reportCard.getId());

        // Simulate INSERT INTO Grades for each grade in the report card
        for (Grade grade : reportCard.getGrades()) {
            String gradeSql = "INSERT INTO Grades (id, subjectId, score, reportCardId) VALUES (...)";
            dbContext.executeQuery(gradeSql); // Log the query

            Map<String, Object> gradeRow = new HashMap<>();
            gradeRow.put("id", grade.getId());
            gradeRow.put("subjectId", grade.getSubjectId());
            gradeRow.put("score", grade.getScore());
            gradeRow.put("reportCardId", reportCard.getId()); // Link grade to report card
            dbContext.gradesTable.add(gradeRow);
            System.out.println("ReportCardRepository: Saved Grade (Subject: " + grade.getSubjectId() + ", Score: " + grade.getScore() + ") for ReportCard ID: " + reportCard.getId());
        }

        return true; // Simulate successful save
    }
}