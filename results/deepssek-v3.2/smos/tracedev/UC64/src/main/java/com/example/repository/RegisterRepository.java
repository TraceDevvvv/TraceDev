package com.example.repository;

import com.example.model.Register;
import java.util.List;

public interface RegisterRepository {
    List<Register> findByAcademicYear(int academicYear);
}