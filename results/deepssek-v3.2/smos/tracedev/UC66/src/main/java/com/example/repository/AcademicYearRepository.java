package com.example.repository;

import com.example.domain.AcademicYear;

import java.util.List;

public interface AcademicYearRepository {
    List<AcademicYear> findAllActive();
    AcademicYear findById(String yearId);
}