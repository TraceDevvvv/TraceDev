package com.example.repository;

import com.example.domain.ReportCard;

public interface ReportCardRepository {
    ReportCard findByStudentAndSemester(String studentId, String semesterId);
}