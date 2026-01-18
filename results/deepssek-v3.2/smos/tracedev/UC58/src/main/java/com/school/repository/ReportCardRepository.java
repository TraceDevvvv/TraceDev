
package com.school.repository;

import com.school.db.SchoolDbContext;
import com.school.model.AcademicYear;
import com.school.model.Class;
import com.school.model.ReportCard;
import com.school.model.Student;

import java.util.List;

/**
 * Concrete implementation of IReportCardRepository using SchoolDbContext.
 */
public class ReportCardRepository implements IReportCardRepository {
    private SchoolDbContext _dbContext;

    public ReportCardRepository(SchoolDbContext dbContext) {
        this._dbContext = dbContext;
    }

    @Override
    public List<AcademicYear> GetAcademicYearsForProfessor(int professorId) {
        // Delegate to DbContext query method
        return _dbContext.queryAcademicYearsForProfessor(professorId);
    }

    @Override
    public List<Class> GetClassesForAcademicYear(int professorId, int academicYearId) {
        return _dbContext.queryClassesForProfessorAndAcademicYear(professorId, academicYearId);
    }

    @Override
    public List<Student> GetStudentsForClass(int classId) {
        return _dbContext.queryStudentsInClass(classId);
    }

    @Override
    public ReportCard GetReportCard(int studentId, int quarter) {
        return _dbContext.queryReportCardForStudentAndQuarter(studentId, quarter);
    }
}
