package com.example.school;

import java.util.List;

/**
 * Repository interface specifically for DisciplinaryNote entities.
 * Extends IRepository with DisciplinaryNote as the entity type and String as the ID type.
 */
public interface IDisciplinaryNoteRepository extends IRepository<DisciplinaryNote, String> {
    /**
     * Finds all disciplinary note records for a specific class.
     * @param classId The unique identifier of the class.
     * @return A list of disciplinary note records for the class.
     * @throws SMOSConnectionException if there's a connection issue.
     */
    List<DisciplinaryNote> findByClassId(String classId) throws SMOSConnectionException;
}