package com.example.service;

import com.example.domain.AcademicYear;
import com.example.domain.PupilClass;
import com.example.domain.Student;
import com.example.dto.ReportCardDTO;
import com.example.repository.AcademicYearRepository;
import com.example.repository.PupilClassRepository;
import com.example.repository.ReportCardRepository;
import com.example.repository.StudentRepository;

import java.util.List;

public class ReportCardServiceImpl implements ReportCardService {
    private final AcademicYearRepository academicYearRepository;
    private final PupilClassRepository pupilClassRepository;
    private final StudentRepository studentRepository;
    private final ReportCardRepository reportCardRepository;

    public ReportCardServiceImpl(AcademicYearRepository academicYearRepository,
                                 PupilClassRepository pupilClassRepository,
                                 StudentRepository studentRepository,
                                 ReportCardRepository reportCardRepository) {
        this.academicYearRepository = academicYearRepository;
        this.pupilClassRepository = pupilClassRepository;
        this.studentRepository = studentRepository;
        this.reportCardRepository = reportCardRepository;
    }

    @Override
    public List<AcademicYear> getAvailableAcademicYears() {
        return academicYearRepository.findAllActive();
    }

    @Override
    public List<PupilClass> getClassesForYear(String academicYearId) {
        return pupilClassRepository.findByAcademicYear(academicYearId);
    }

    @Override
    public List<Student> getStudentsInClass(String classId) {
        return studentRepository.findByClass(classId);
    }

    @Override
    public ReportCardDTO getReportCard(String studentId, String semesterId) {
        com.example.domain.ReportCard reportCard = reportCardRepository.findByStudentAndSemester(studentId, semesterId);
        if (reportCard == null) {
            throw new RuntimeException("Report card not found for studentId=" + studentId + ", semesterId=" + semesterId);
        }
        return reportCard.generateReportDTO();
    }
}