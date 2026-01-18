package infrastructure.repository;

import infrastructure.dao.AcademicYearDAO;
import java.util.Arrays;
import java.util.List;

/**
 * Concrete implementation of IAcademicYearRepository.
 * This class simulates interaction with a database to retrieve academic year data.
 */
public class AcademicYearRepository implements IAcademicYearRepository {

    // Dummy data to simulate database records
    private final List<AcademicYearDAO> academicYears = Arrays.asList(
        new AcademicYearDAO("AY001", "2022-2023"),
        new AcademicYearDAO("AY002", "2023-2024"),
        new AcademicYearDAO("AY003", "2024-2025")
    );

    /**
     * Finds all academic years.
     * @return A list of all AcademicYearDAO objects.
     */
    @Override
    public List<AcademicYearDAO> findAll() {
        System.out.println("Database: SELECT year_id, year FROM academic_years"); // Simulate DB call
        return academicYears;
    }
}