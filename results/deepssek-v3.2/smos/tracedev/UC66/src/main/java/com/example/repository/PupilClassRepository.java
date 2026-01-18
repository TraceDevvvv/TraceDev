package com.example.repository;

import com.example.domain.PupilClass;

import java.util.List;

public interface PupilClassRepository {
    List<PupilClass> findByAcademicYear(String academicYearId);
    PupilClass findById(String classId);
}