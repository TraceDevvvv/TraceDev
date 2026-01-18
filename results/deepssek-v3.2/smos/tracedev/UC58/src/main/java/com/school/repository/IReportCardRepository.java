package com.school.repository;

import com.school.model.AcademicYear;
import com.school.model.Class;
import com.school.model.ReportCard;
import com.school.model.Student;

import java.util.List;

/**
 * Repository interface for report card related data access.
 */
public interface IReportCardRepository {
    List<AcademicYear> GetAcademicYearsForProfessor(int professorId);
    List<Class> GetClassesForAcademicYear(int professorId, int academicYearId);
    List<Student> GetStudentsForClass(int classId);
    ReportCard GetReportCard(int studentId, int quarter);
}