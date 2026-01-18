package com.example.diagram;

import java.util.List;

/**
 * Repository interface for Archive (database) operations.
 * This class corresponds to the "DB" participant in the sequence diagram.
 */
public interface ArchiveRepository {
    /**
     * Query registers for a given academic year.
     * This method corresponds to the "query registers for year" message in the sequence diagram.
     * @param year the academic year.
     * @return list of Register data (as raw data).
     */
    List<Register> queryRegistersForYear(String year);
}