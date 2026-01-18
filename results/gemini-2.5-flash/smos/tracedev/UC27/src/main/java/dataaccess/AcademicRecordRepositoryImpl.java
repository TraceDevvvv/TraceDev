package dataaccess;

import domain.AcademicYear;
import domain.DigitalRegister;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of IAcademicRecordRepository, responsible for interacting with the DigitalArchive
 * and mapping raw data to domain objects.
 */
public class AcademicRecordRepositoryImpl implements IAcademicRecordRepository {

    private DigitalArchive dataSource;

    /**
     * Constructs an AcademicRecordRepositoryImpl with a given DigitalArchive data source.
     *
     * @param dataSource The DigitalArchive instance to access raw data.
     */
    public AcademicRecordRepositoryImpl(DigitalArchive dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * {@inheritDoc}
     * Queries the DigitalArchive for records and converts them into DigitalRegister domain objects.
     */
    @Override
    public List<DigitalRegister> findRegistersByAcademicYear(String academicYearId) {
        System.out.println("[Repository] Retrieving digital registers for academic year: " + academicYearId);
        List<Map<String, String>> recordsData = dataSource.queryRecords(academicYearId);
        // Map raw data (List<Map<String, String>>) to domain objects (List<DigitalRegister>)
        return recordsData.stream()
                .map(this::mapToDigitalRegister)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     * Queries the DigitalArchive for academic years and converts them into AcademicYear domain objects.
     */
    @Override
    public List<AcademicYear> findAllAcademicYears() {
        System.out.println("[Repository] Retrieving all academic years.");
        List<Map<String, String>> academicYearsData = dataSource.queryAcademicYears();
        // Map raw data (List<Map<String, String>>) to domain objects (List<AcademicYear>)
        return academicYearsData.stream()
                .map(this::mapToAcademicYear)
                .collect(Collectors.toList());
    }

    /**
     * Helper method to map a Map<String, String> representing a register to a DigitalRegister object.
     *
     * @param recordMap A map containing register data.
     * @return A DigitalRegister object.
     */
    private DigitalRegister mapToDigitalRegister(Map<String, String> recordMap) {
        // Assuming all necessary keys exist and are in the correct format.
        // In a real application, robust error handling or validation would be needed.
        return new DigitalRegister(
                recordMap.get("registerId"),
                recordMap.get("title"),
                recordMap.get("content"),
                recordMap.get("academicYearId"),
                recordMap.get("academicClassId")
        );
    }

    /**
     * Helper method to map a Map<String, String> representing an academic year to an AcademicYear object.
     *
     * @param yearMap A map containing academic year data.
     * @return An AcademicYear object.
     */
    private AcademicYear mapToAcademicYear(Map<String, String> yearMap) {
        // Assuming all necessary keys exist and are in the correct format.
        return new AcademicYear(
                yearMap.get("id"),
                Integer.parseInt(yearMap.get("yearNumber"))
        );
    }
}