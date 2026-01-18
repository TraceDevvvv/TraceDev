package dataaccess;

import domain.AcademicYear;
import domain.DigitalRegister;

import java.util.List;

/**
 * Interface for data access operations related to academic records.
 * Defines the contract for retrieving digital registers and academic years.
 */
public interface IAcademicRecordRepository {

    /**
     * Finds and returns a list of digital registers associated with a specific academic year.
     *
     * @param academicYearId The unique identifier of the academic year.
     * @return A list of DigitalRegister objects for the specified year.
     */
    List<DigitalRegister> findRegistersByAcademicYear(String academicYearId);

    /**
     * Finds and returns a list of all available academic years.
     *
     * @return A list of AcademicYear objects.
     */
    List<AcademicYear> findAllAcademicYears();
}